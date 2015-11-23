package myTests.testSync.Test5_wait;

import java.util.ArrayList;

/**
 * Созданы два объекта, работающие в разных потоках
 * Поток А создает список
 * Поток А создает список в синхронизированном блоке, поэтому пока А не создал список, В не может начать его менять (ждет осовождения монитора)
 * Поток А после создания списка вызывает wait и дает возможность потоку В изменить список
 * Поток В меняет список и сообщает об этом потоку А, путем вызова notify
 * Поток А, тем не менее не может сразу приступить к продолжению своего кода, т.к. В некоторое время остается в синхр. блоке, т.е. владеет монитором
 * Поток В вышел из синхр. блока и поток А может продолжить: выводит обновленный список
 * Created by Valk on 13.04.15.
 */
public class Test5_wait {
    static volatile ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        new Thread(new B()).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        new Thread(new A()).start();
    }
}

class A implements Runnable {
    @Override
    public void run() {
        System.out.println(this.getClass().getSimpleName() + " в " + Thread.currentThread().getName() + " подошел к synchronized (Integer.class)");
        synchronized (Integer.class) {
            System.out.println(this.getClass().getSimpleName() + " в " + Thread.currentThread().getName() + " вошел в synchronized (Integer.class) и создает список ... ");

            {
                //заполняем список
                Test5_wait.list.add(1);
                Test5_wait.list.add(2);
                Test5_wait.list.add(3);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
            try {
                System.out.println("    " + this.getClass().getSimpleName() + " в " + Thread.currentThread().getName() + " создал список и ждет ...");
                Integer.class.wait(); //ждем, пока в в другом потоке в объекте В список изменится
                System.out.println("    " + this.getClass().getSimpleName() + " в " + Thread.currentThread().getName() + " ... работает дальше");
                System.out.println("      выводим новый список:");
                for (Integer i : Test5_wait.list) {//выводим измененный список
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(i);
                }
                System.out.println("      готово");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.getClass().getSimpleName() + " в " + Thread.currentThread().getName() + " вышел из synchronized (Integer.class)");
    }
}

class B implements Runnable {
    @Override
    public void run() {
        while(Test5_wait.list.size()==0) {} //для демонстрации влияния volatile: без volatile этот цикл будет крутиться
        // вечно, не зная, что Test5_wait.list уже не пустой
        System.out.println(this.getClass().getSimpleName() + " в " + Thread.currentThread().getName() + " подошел к synchronized (Integer.class)");
        synchronized (Integer.class) {
            System.out.println(this.getClass().getSimpleName() + " в " + Thread.currentThread().getName() + " вошел в synchronized (Integer.class)");
            System.out.println("   меняем список ... ");
            for (int i = 0; i < Test5_wait.list.size(); i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                Integer ii = Test5_wait.list.get(i);
                int jj = ii * 100;
                Test5_wait.list.remove(i);
                Test5_wait.list.add(i, jj);
                System.out.println("      значение "+ii+" заменили на "+jj);
            }
            System.out.println("   готово");
            System.out.println("    " + this.getClass().getSimpleName() + " в " + Thread.currentThread().getName() + " уведомил А, что новый список готов...");
            Integer.class.notify();
            System.out.println("   B еще некоторое время работает в синхронизированном блоке, поэтому А пока ждет ... ");
            for (int i = 0; i < 50; i++) {
                System.out.print(" . ");
            }
            System.out.println();
        }
        System.out.println(this.getClass().getSimpleName() + " в " + Thread.currentThread().getName() + " вышел из synchronized (Integer.class), теперь А может продолжать");
    }
}