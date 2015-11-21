package com.javarush.test.level17.lesson10.home06;

/* Глажка
И снова быт...
Поставьте один synchronized, чтобы diana и igor гладили по-очереди, ведь утюг всего один!
*/

public class Solution {
    public static void main(String[] args) {
        //Person diana = new Person("Diana");
        //Person igor = new Person("Igor");
        Person chel = new Person("Некто");
        new Thread(chel).start();
        new Thread(chel).start();
    }

    public static class Person implements Runnable {//extends Thread { //Человек

        public Person(String name) {
            //super(name);
            //start();
        }

        String getName(){
            return Thread.currentThread().getName();
        }

        @Override
        public synchronized void run() {
            //synchronized (Integer.class) {
            go();
            //}
        }

        public synchronized void go(){
            Iron iron = takeIron();
            try {Thread.sleep((long) (Math.random()*300));} catch (InterruptedException e){}
            Clothes clothes = takeClothes();
            ironing(iron, clothes);
            returnIron();
            try {Thread.sleep((long) (Math.random()*500));} catch (InterruptedException e){}
        }

        protected Iron takeIron() {
            System.out.println(getName() + "Taking an Iron");
            return new Iron();
        }

        protected Iron returnIron() {
            System.out.println(getName() + "Returning the Iron");
            return new Iron();
        }

        protected Clothes takeClothes() {
            return new Clothes("T-shirt");
        }

        protected void ironing(Iron iron, Clothes clothes) {
            System.out.println(getName() + "'s ironing the " + clothes.name);
        }
    }

    public static class Iron {
    } //Утюг

    public static class Clothes {//Одежда
        String name;

        public Clothes(String name) {
            this.name = name;
        }
    }
}
