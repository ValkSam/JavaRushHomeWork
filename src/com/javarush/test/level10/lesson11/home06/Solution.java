package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        private String fam;
        private String name;
        private String otch;
        private boolean sex = true;
        private int age = 0;
        private String address;

        public Human(String fam, String name, String otch, boolean sex, int age, String address){
            this.fam = fam;
            this.name = name;
            this.otch = otch;
            this.sex = sex;
            this.age = age;
            this.address = address;
        }
        public Human(String fam, String name, String otch, boolean sex, int age){
            this.fam = fam;
            this.name = name;
            this.otch = otch;
            this.sex = sex;
            this.age = age;
        }
        public Human(String fam, String name, String otch, boolean sex){
            this.fam = fam;
            this.name = name;
            this.otch = otch;
            this.sex = sex;
        }
        public Human(String fam, String name, String otch){
            this.fam = fam;
            this.name = name;
            this.otch = otch;
        }
        public Human(String fam, String name){
            this.fam = fam;
            this.name = name;
        }
        public Human(String name){
            this.name = name;
        }
        public Human(String fam, String name, int age){
            this.fam = fam;
            this.name = name;
            this.age = age;
        }
        public Human(String fam, String name, int age, String address){
            this.fam = fam;
            this.name = name;
            this.age = age;
            this.address = address;
        }
        public Human(String fam, String name, boolean sex, int age, String address){
            this.fam = fam;
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.address = address;
        }
        public Human(String fam, String name, String otch, String address){
            this.fam = fam;
            this.name = name;
            this.otch = otch;
            this.address = address;
        }


    }
}
