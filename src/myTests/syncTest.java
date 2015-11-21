package myTests;

import com.javarush.test.level17.lesson10.home07.MyBean;

/**
 * Created by Valk on 24.01.15.
 */
public class syncTest {
        private static Person person;
        public static void main(String[] args) {
            /*//вариант 1
            Person diana = new Person("Diana");
            Person igor = new Person("Igor");
            diana.start();
            igor.start();
            */
            /*//вариант 2
            Person chel = new Person("Некто");
            new Thread(chel).start();
            new Thread(chel).start();
            */
            /*//вариант 3
            Person chel1 = new Person("Некто1");
            Person chel2 = new Person("Некто2");
            new Thread(chel1).start();
            new Thread(chel2).start();
            */
            //вариант 4
            person = new Person("persona ");
            new MyThread(person).start();
            new MyThread(person).start();
            //*/

        }
    /*// вариант 4 ...
    public static class MyThread extends Thread{
        Person person;
        public MyThread(Person person){this.person = person;}
        public void run(){
            person.go();
        }
    }
    public static class Person { //Человек

        public Integer counter = 0;

        public Person(String name) {
        }

        String getName() {
            return Thread.currentThread().getName();
        }

        public void go() { //вариант 4: ОДИН оъект в разных потоках
            //должно быть так:
//Thread-1 Taking an Iron counter= 100000
// counter= 200000
//Thread-1's ironing the T-shirt counter= 300000
//Thread-1 Returning the Iron counter= 400000
//Thread-0 Taking an Iron counter= 500000
// counter= 600000
//Thread-0's ironing the T-shirt counter= 700000
//Thread-0 Returning the Iron counter= 800000
//
//на самом деле, если нет синхронизации (public synchronized void go() ):
//Thread-0 Taking an Iron counter= 112702
//Thread-1 Taking an Iron counter= 165668
// counter= 265668
// counter= 436806
//Thread-0's ironing the T-shirt counter= 536806
//Thread-1's ironing the T-shirt counter= 565668
//Thread-0 Returning the Iron counter= 665668
//Thread-1 Returning the Iron counter= 765668
// т.е. не только не соблюдается последовательность, но и общая переменная объекта живет как-то странно
            for (int i = 1; i<=100000; i++) counter++;
            Iron iron = takeIron();
            try {
                Thread.sleep((long) (Math.random() * 300));
            } catch (InterruptedException e) {
            }
            for (int i = 1; i<=100000; i++) counter++;
            Clothes clothes = takeClothes();
            for (int i = 1; i<=100000; i++) counter++;
            ironing(iron, clothes);
            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {
            }
            for (int i = 1; i<=100000; i++) counter++;
            returnIron();
        }

        protected Iron takeIron() {
            System.out.println(getName() + " Taking an Iron"+" counter= "+counter);
            return new Iron();
        }

        protected Iron returnIron() {
            System.out.println(getName() + " Returning the Iron"+" counter= "+counter);
            return new Iron();
        }

        protected Clothes takeClothes() {
            System.out.println(" counter= "+counter);
            return new Clothes("T-shirt");
        }

        protected void ironing(Iron iron, Clothes clothes) {
            System.out.println(getName() + "'s ironing the " + clothes.name+" counter= "+counter);
        }
    }
    *///...вариант 4

    /*// вариант 2 ...
    public static class Person implements Runnable { //Человек

        public Person(String name) {
        }

        String getName() {
            return Thread.currentThread().getName();
        }

        @Override
        public void run() {
            go();
        }

        public synchronized void go() { //вариант 3 не синхронизируется
            //synchronized (Integer.class) { //вариант 3 синхронизируется
                Iron iron = takeIron();
                try {
                    Thread.sleep((long) (Math.random() * 300));
                } catch (InterruptedException e) {
                }
                Clothes clothes = takeClothes();
                ironing(iron, clothes);
                returnIron();
                try {
                    Thread.sleep((long) (Math.random() * 500));
                } catch (InterruptedException e) {
                }
            //}
        }

        protected Iron takeIron() {
            System.out.println(getName() + " Taking an Iron");
            return new Iron();
        }

        protected Iron returnIron() {
            System.out.println(getName() + " Returning the Iron");
            return new Iron();
        }

        protected Clothes takeClothes() {
            return new Clothes("T-shirt");
        }

        protected void ironing(Iron iron, Clothes clothes) {
            System.out.println(getName() + "'s ironing the " + clothes.name);
        }
    }
    *///...вариант 2

    /*//вариант 1...
    public static class Person extends Thread {//extends Thread { //Человек

        public Person(String name) {
            super(name);
        }

        @Override
        public void run() {
            go();
        }
        //public synchronized void go(){ //вариант 1 не синхронизируется
        public void go(){
            Integer i = 0;
            //synchronized (this) { //вариант 1.1 = вариант 1
            //synchronized (this.getClass()) { //вариант 1.2 <> вариант 1 - синхронизируется
            //synchronized (Integer.class) { //вариант 1.3  вариант 1.2 - синхронизируется
            //synchronized (i.getClass()) { //вариант 1.4  вариант 1.2 - синхронизируется
            synchronized (i) { //вариант 1.5  вариант 1.2 - синхронизируется
                Iron iron = takeIron();
                try {
                    Thread.sleep((long) (Math.random() * 300));
                } catch (InterruptedException e) {
                }
                Clothes clothes = takeClothes();
                ironing(iron, clothes);
                returnIron();
                try {
                    Thread.sleep((long) (Math.random() * 500));
                } catch (InterruptedException e) {}
            }
        }


        protected Iron takeIron() {
            System.out.println(getName() + " Taking an Iron");
            return new Iron();
        }
        protected Iron returnIron() {
            System.out.println(getName() + " Returning the Iron");
            return new Iron();
        }
        protected Clothes takeClothes() {
            return new Clothes("T-shirt");
        }
        protected void ironing(Iron iron, Clothes clothes) {
            System.out.println(getName() + "'s ironing the " + clothes.name);
        }
    }
    *///...вариант1
    /*//закомментировать для варианта 5
        public static class Iron {
        } //Утюг

        public static class Clothes {//Одежда
            String name;

            public Clothes(String name) {
                this.name = name;
            }
        }
        *///закомментировать для варианта 5
}
// вариант 5 ...
  class MyThread extends Thread{
    Person person;
    public MyThread(Person person){this.person = person;}
    public void run(){
        person.go();
    }
}
  class Person { //Человек

    public  Long counter = 0L;

    public Person(String name) {
    }

    String getName() {
        return Thread.currentThread().getName();
    }

    public void go() { //вариант 4: ОДИН оъект в разных потоках
        //должно быть так:
//Thread-1 Taking an Iron counter= 100000
// counter= 200000
//Thread-1's ironing the T-shirt counter= 300000
//Thread-1 Returning the Iron counter= 400000
//Thread-0 Taking an Iron counter= 500000
// counter= 600000
//Thread-0's ironing the T-shirt counter= 700000
//Thread-0 Returning the Iron counter= 800000
//
//на самом деле, если нет синхронизации (public synchronized void go() ):
//Thread-0 Taking an Iron counter= 112702
//Thread-1 Taking an Iron counter= 165668
// counter= 265668
// counter= 436806
//Thread-0's ironing the T-shirt counter= 536806
//Thread-1's ironing the T-shirt counter= 565668
//Thread-0 Returning the Iron counter= 665668
//Thread-1 Returning the Iron counter= 765668
// т.е. не только не соблюдается последовательность, но и общая переменная объекта живет как-то странно
        //
        //если вынести классы из под класса syncTest, сделав их не static, то можно получить так:
//        Thread-0 Taking an Iron counter= 105018
//        Thread-1 Taking an Iron counter= 46883
//        Thread-0 counter= 292582
//        Thread-1 counter= 205018
//        Thread-0's ironing the T-shirt counter= 305018
//        Thread-1's ironing the T-shirt counter= 405018
//        Thread-0 Returning the Iron counter= 505018
//        Thread-1 Returning the Iron counter= 605018
        //тут особенность в том, что counter растет только в рамках своего потока, независмо от другого потока,
        //что говорит о том, что counter живет в кеше своего потока и увеличивается в рамках каждого потока независимо

        for (int i = 1; i<=1000000; i++) counter++;
        Iron iron = takeIron();
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {
        }
        for (int i = 1; i<=1000000; i++) counter++;
        Clothes clothes = takeClothes();
        for (int i = 1; i<=1000000; i++) counter++;
        ironing(iron, clothes);
        try {
            Thread.sleep((long) (Math.random() * 900));
        } catch (InterruptedException e) {
        }
        for (int i = 1; i<=1000000; i++) counter++;
        returnIron();
    }

    protected Iron takeIron() {
        System.out.println(getName() + " Taking an Iron"+" counter= "+counter);
        return new Iron();
    }

    protected Iron returnIron() {
        System.out.println(getName() + " Returning the Iron"+" counter= "+counter);
        return new Iron();
    }

    protected Clothes takeClothes() {
        System.out.println(getName() + " counter= "+counter);
        return new Clothes("T-shirt");
    }

    protected void ironing(Iron iron, Clothes clothes) {
        System.out.println(getName() + "'s ironing the " + clothes.name+" counter= "+counter);
    }
}
 class Iron {
} //Утюг

 class Clothes {//Одежда
    String name;

    public Clothes(String name) {
        this.name = name;
    }
}
//...вариант 5
