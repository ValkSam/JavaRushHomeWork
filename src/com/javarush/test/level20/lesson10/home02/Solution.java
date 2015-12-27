package com.javarush.test.level20.lesson10.home02;

import java.io.*;

/* Десериализация
На вход подается поток, в который записан сериализованный объект класса A либо класса B.
Десериализуйте объект в методе getOriginalObject предварительно определив, какого именно типа там объект.
Реализуйте интерфейс Serializable где необходимо.
*/
public class Solution implements Serializable {
    public A getOriginalObject(ObjectInputStream objectStream) throws Exception {
        Object obj = objectStream.readObject(); //here
        if (obj instanceof B) {
            return (B) obj;
        }
        else if (obj instanceof A) {
            return (A) obj;
        }
        else return null;
    }

    public class A implements Serializable{
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }


    public static void main(String args[]) throws Exception
    {
        ByteArrayOutputStream obuf = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream (obuf);
        B b = (new Solution()).new B();
        oos.writeObject(b);

        ByteArrayInputStream ibuf = new ByteArrayInputStream(obuf.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(ibuf);
        B b1 = (B)(new Solution()).getOriginalObject(ois);
    }

}
