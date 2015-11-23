package myTests.testSync.Test6_lock_livelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Valk on 22.11.15.
 * Пример LiveLock
 *
 * "Livelock"- тип взаимной блокировки, при котором несколько потоков продолжают свою работу, но попадают в зацикленность
 * при попытке получения каких-либо ресурсов. Фактической ошибки не возникает, но КПД системы падает до 0. Часто возникает
 * в результате попыток предотвращения deadlock'а.

 Пример: Метод пытается выполнить какую-либо работу, используя 2 внешних объекта.
 Сперва он получает блокировку по одному из объектов, а затем проверяет, свободен ли второй объект.
 Если объект свободен - получает блокировку по нему и выполняет работу, если занят - освобождает первый объект и ждёт,
 когда они оба освободятся. 2 потока одновременно вызывают этот метод. Поток 1 блокирует первый объект.
 Поток 2 блокирует второй объект. Оба проверяют, свободен ли второй ресурс - обнаруживают, что он занят и
 освобождают занятый ресурс. Оба потока обнаруживают, что оба ресурса свободны и начинают процесс блокировки сначала.
 *
 */
public class Livelock {
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();
    private volatile static boolean busy = false;
    private volatile static Integer llcount = 0;
    private volatile static Integer bzcount = 0;
    //
    public static void main(String[] args) {
        Thread thread1 = new Thread(new A(lock1, lock2));
        Thread thread2 = new Thread(new A(lock2, lock1)); //если порядок блокировки будет одинаковый - доля взаимных ожидаий будет меньше
        thread1.start();
        thread2.start();
        try {Thread.sleep(5000);} catch (InterruptedException e){}
        thread1.interrupt();
        thread2.interrupt();
        System.out.format("Доля livelock: %f", llcount*1f/(llcount+bzcount));
    }
    //
    static class A implements Runnable{
        private Lock lock1;
        private Lock lock2;

        public A(Lock lock1, Lock lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if (lock1.tryLock(10, TimeUnit.MILLISECONDS)) {
                        Thread.sleep(10);
                        try {
                            if (lock2.tryLock(10, TimeUnit.MILLISECONDS)) {
                                try {
                                    busy = true;
                                    Thread.sleep(300);
                                } finally {
                                    lock2.unlock();
                                    busy = false;
                                }
                            } else {
                                if (! busy) {
                                    System.out.println("livelock");
                                    llcount++;
                                } else {
                                    System.out.println("    good");
                                    bzcount++;
                                }
                            }
                        } finally {
                            lock1.unlock();
                        }
                    } else {
                        if (! busy) {
                            System.out.println("livelock");
                            llcount++;
                        } else {
                            System.out.println("    good");
                            bzcount++;
                        }
                    }
                }
            } catch (Exception e) {}
        }
    }
}
