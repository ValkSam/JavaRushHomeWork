package com.javarush.test.level25.lesson05.task02;

/* Без дураков
1. Создай private class MyUncaughtExceptionHandler, который на перехват исключения должен подождать половину секунды,
а затем вывести на экран secretKey, имя трэда и сообщение возникшего исключения.
Используй String.format(...). Пример:
super secret key, Thread-0, it's an example
2. Разберитесь в последовательности выполняемого кода и обеспечьте логирование возникновения исключения в п.1.
3. Метод main в тестировании не участвует.
*/
public class Solution {

    public static void main(String[] args) {
        MyThread myThread = new Solution().new MyThread("super secret key");
        myThread.start();
    }

    public class MyThread extends Thread {
        private String secretKey;

        public MyThread(String secretKey) {
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            setDaemon(true); //для того, чтобы прошел тест надо просто убрать setDaemon();
            //но можно с использованием Thread thread
        }

        @Override
        public void run() {
            throw new NullPointerException("it's an example");
        }

        private class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                }
            };
            {
                thread.start(); //запускаем не демон поток, который будет завершен даже после окончания main
            }
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                try {Thread.sleep(500);} catch (InterruptedException ex){}
                System.out.println(String.format("%s, %s, %s", secretKey, t.getName(), e.getMessage()));
                thread.interrupt();
            }
        }
    }



}

