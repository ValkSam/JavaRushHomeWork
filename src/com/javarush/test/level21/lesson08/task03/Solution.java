package com.javarush.test.level21.lesson08.task03;

/* Запретить клонирование
Запретите клонировать класс B
Разрешите клонировать класс C
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public A clone() throws CloneNotSupportedException {
            return (A)super.clone();
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public B clone() throws CloneNotSupportedException {
            if (this.getClass().getSimpleName().equals("B")) {
                throw new CloneNotSupportedException();
            }
            else {
                return (B)super.clone();
            }
        }
    }

    public static class C extends B{
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        public C clone() throws CloneNotSupportedException {
            return (C)super.clone();
        }
    }

    public static void main (String[] args) throws Exception {
        A a = new A(1,2);
        B b = new B(3,4, "B");
        C c = new C(5,6, "C");
        //

        try {
            A a1 = a.clone();
            System.out.println(a);
            System.out.println(a1);
        }
        catch (Exception e) {e.printStackTrace();}

        try {
            B b1 = b.clone();
            System.out.println(b);
            System.out.println(b1);
        }
        catch (Exception e) {e.printStackTrace();}

        try {
            C c1 = c.clone();
            System.out.println(c);
            System.out.println(c1);
        }
        catch (Exception e) {e.printStackTrace();}

    }
}
