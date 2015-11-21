package com.javarush.test.level20.lesson10.home03;

import java.io.*;

/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
*/
public class Solution implements Serializable {
    public static class A {
        protected String name = "A";
        public A(){}
        public A(String name) {
            this.name += name;
        }
    }

    public class B extends A implements Serializable {
        public B(String name) {
            super(name);
            this.name += name;
        }

        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            name = (String)s.readObject();
        }
        private void writeObject(ObjectOutputStream s) throws IOException {
            s.defaultWriteObject();
            s.writeObject(name);
        }
    }

    /*
    public static void main(String args[]) throws Exception
    {
        ByteArrayOutputStream obuf = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream (obuf);
        B b = (new Solution()).new B("bbb");
        System.out.println(b.name);
        oos.writeObject(b);

        ByteArrayInputStream ibuf = new ByteArrayInputStream(obuf.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(ibuf);
        B b1 = (B)ois.readObject();
        System.out.println(b1.name);
    }
     */

}
