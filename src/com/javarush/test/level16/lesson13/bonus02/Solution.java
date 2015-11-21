package com.javarush.test.level16.lesson13.bonus02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Клубок
1. Создай 5 различных своих нитей c отличным от Thread типом:
1.1. нить 1 должна бесконечно выполняться;
1.2. нить 2 должна выводить "InterruptedException" при возникновении исключения InterruptedException;
1.3. нить 3 должна каждые полсекунды выводить "Ура";
1.4. нить 4 должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться;
1.5. нить 5 должна читать с консоли цифры пока не введено слово "N", а потом вывести в консоль сумму введенных цифр.
2. В статическом блоке добавь свои нити в List<Thread> threads в перечисленном порядке.
3. Нити не должны стартовать автоматически.
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<Thread>(5);

    static {
        threads.add(new Thread1());
        threads.add(new Thread2());
        threads.add(new Thread3());
        threads.add(new Thread4());
        threads.add(new Thread5());
    }

    static class Thread1 extends Thread {
        @Override
        public void run(){
            while (true) {}
        }
    }
    static class Thread2 extends Thread {
        @Override
        public void run(){
            try {
                while (true) {
                    Thread.sleep(0);
                }
            }
            catch (InterruptedException e){
                System.out.println("InterruptedException");
            }
        }
    }
    static class Thread3 extends Thread {
        @Override
        public void run(){
            try {
                while (true) {
                    System.out.println("Ура");
                    Thread.sleep(500);
                }
            }
            catch (InterruptedException e){}
        }
    }
    static class Thread4 extends Thread implements Message{
        @Override
        public void run(){
            while(true){
                if (isInterrupted()) return;
            }
        }

        public void showWarning(){
            try {
                System.out.println(this.isInterrupted()); //false
                Thread.currentThread().interrupt();       //не устанавливает isInterrupted, но выбросит Exception на sleep'е (join'e)
                System.out.println(this.isInterrupted()); //false
                interrupt();                              //не выбросит Exception на sleep'е (join'e), но устанновит isInterrupted
                System.out.println(this.isInterrupted()); //true

                //Thread.sleep(0);
                join();
            }
            catch (InterruptedException e){
                System.out.println("выброс");
            };
        }
    }
    static class Thread5 extends Thread {
        @Override
        public void run(){
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Integer sum = 0;
                while (true){
                    try {
                        String s = reader.readLine();
                        if ("N".equals(s)) {
                            System.out.println(sum);
                            reader.close();
                            return;
                        }
                        sum += Integer.valueOf(s);
                    }
                    catch (Exception e){}
                }
        }
    }
    public static void main(String[] args) throws Exception {
        threads.get(0).start();
        threads.get(1).start();
        threads.get(2).start();
        threads.get(3).start();
        threads.get(4).start();
        try {Thread.sleep(100);} catch (InterruptedException e){}
        threads.get(1).interrupt();
        ((Thread4)threads.get(3)).showWarning();
        try {Thread.sleep(1000);} catch (InterruptedException e){}
        System.out.println(threads.get(3).isAlive());
        //threads.get(0).join();
        threads.get(0).interrupt();
        threads.get(1).interrupt();
        threads.get(2).interrupt();
        threads.get(3).interrupt();
        threads.get(4).interrupt();

    }


}
