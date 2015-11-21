package com.javarush.test.level20.lesson07.task03;

import java.io.*;
import java.util.*;

/* Externalizable Person
Класс Person должен сериализоваться с помощью интерфейса Externalizable.
Подумайте, какие поля не нужно сериализовать.
Исправьте ошибку сериализации.
Сигнатуры методов менять нельзя.
*/
public class Solution {
    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;


        public Person() {
            super();
        }

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(mother);
            out.writeObject(father);
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeInt(age);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            mother = (Person)in.readObject();
            father = (Person)in.readObject();
            firstName = (String)in.readObject();
            lastName = (String)in.readObject();
            age = in.readInt();
            children = (List<Person>)in.readObject();
        }
        /*
        public static void main(String[] args) throws Exception {
            ByteArrayOutputStream obuf = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(obuf);
            Person a = new Person("Ivan", "Ivanov", 10);
            a.setMother(new Person());
            oos.writeObject(a);


            ByteArrayInputStream ibuf = new ByteArrayInputStream(obuf.toByteArray());
            ObjectInputStream ios = new ObjectInputStream(ibuf);
            Person b = (Person)ios.readObject();
            System.out.println(b.firstName);
            System.out.println(b.lastName);
            System.out.println(b.mother);
            System.out.println(b.father);
            System.out.println(b.age);
        }
        */
    }
}
