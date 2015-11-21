package com.javarush.test.level20.lesson04.task04;
import java.io.*;
/* Как сериализовать static?
Сделайте так, чтобы сериализация класса ClassWithStatic была возможной
*/


/*
чтобы прошла задача надо только лишь: "implements Serializable" добавить и все
но оставил код как пример сохранения static
*/


public class Solution {
    public static class ClassWithStatic implements Serializable {
        private static final ObjectStreamField[] serialPersistentFields = {
                new ObjectStreamField("staticString", String.class) ,
                new ObjectStreamField("i", int.class) ,
                new ObjectStreamField("j", int.class) };
        public static String staticString = "it's test static string";
        public int i;
        public int j;
        private void writeObject(ObjectOutputStream s) throws IOException {
            ObjectOutputStream.PutField fields = s.putFields();
            fields.put("staticString", staticString);
            fields.put("i", i);
            fields.put("j", j);
            s.writeFields();
        }
        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
            ObjectInputStream.GetField fields = s.readFields();
            staticString = (String) fields.get("staticString", "");
            i = (int) fields.get("i", 0);
            j = (int) fields.get("j", 0);
        }
    }
    public static void main(String[] args) throws Exception{
        ClassWithStatic v1 = new ClassWithStatic();
        v1.i = 10;
        v1.j = 11;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:\\q.txt"));
        oos.writeObject(v1);
        oos.close();
        v1.staticString = "====";
        v1.i = 100;
        v1.j = 110;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:\\q.txt"));
        ClassWithStatic v2 = (ClassWithStatic)ois.readObject();
        ois.close();
        System.out.println(v2.staticString);
        System.out.println(v2.i);
        System.out.println(v2.j);
    }
}
