package com.javarush.test.level25.lesson05.home01;

/* Мониторинг состояния нити
В отдельном классе создать нить LoggingStateThread,
которая будет выводить в консоль все изменения состояния (State) переданной в конструктор нити.
Нить LoggingStateThread должна сама завершаться после остановки переданной в конструктор нити.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Thread target = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (interrupted())
                        try {
                            Thread.sleep(400); //RUNNABLE
                        } catch (InterruptedException e) {
                        }
                    break;
                }
                synchronized (this){
                    try {
                        this.wait(); //WAITING
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        LoggingStateThread loggingStateThread = new LoggingStateThread(target);

        loggingStateThread.start();
        target.start();  //NEW
        target.join(800); //RUNNABLE //WAITING
        target.interrupt(); //TERMINATED
        Thread.sleep(1500);
    }
}
