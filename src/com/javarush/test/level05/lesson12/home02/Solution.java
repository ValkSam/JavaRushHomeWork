package com.javarush.test.level05.lesson12.home02;

/* Man and Woman
1. Внутри класса Solution создай public static классы Man и Woman.
2. У классов должны быть поля: name(String), age(int), address(String).
3. Создай конструкторы, в которые передаются все возможные параметры.
4. Создай по два объекта каждого класса со всеми данными используя конструктор.
5. Объекты выведи на экран в таком формате [name + " " + age + " " + address].
*/

public class Solution
{
    public static void main(String[] args)
    {
        Man man = new Man("Boris",22,"Country city street 1");
        Woman woman = new Woman("Natasha",21,"Country city street 2");

        System.out.println(man.toString());
        System.out.println(woman.toString());
    }

    public static class Man{
        private String name;
        private int age = 1;
        private String address;

        public Man(String name) {
            this.name = name;
        }

        public Man(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Man(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public Man(String name, String address) {
            this.name = name;
            this.address = address;
        }
        public String toString(){
            return this.name+" "+this.age+" "+this.address;
        }
    }
    public static class Woman{
        private String name;
        private int age = 1;
        private String address;

        public Woman(String name) {
            this.name = name;
        }

        public Woman(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Woman(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public Woman(String name, String address) {
            this.name = name;
            this.address = address;
        }
        public String toString(){
            return this.name+" "+this.age+" "+this.address;
        }
    }
}
