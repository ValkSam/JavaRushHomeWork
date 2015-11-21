package com.javarush.test.level07.lesson12.home06;

/* Семья
Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human). Создай объекты и заполни их так, чтобы получилось: Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.
Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
…
*/

public class Solution
{
    public static void main(String[] args)
    {
        Human d1 = new Human("Боря", true, 60, null, null);
        Human d2 = new Human("Вася", true, 60, null, null);
        Human b1 = new Human("Аня", false, 60, null, null);
        Human b2 = new Human("Соня", false, 60, null, null);
        Human f = new Human("Гена", true, 30, d1, b1);
        Human m = new Human("Оля", false, 30, d2, b2);
        Human r1 = new Human("Таня", false, 3, f, m);
        Human r2 = new Human("Галя", false, 4, f, m);
        Human r3 = new Human("Коля", true, 5, f, m);

        System.out.println(d1);
        System.out.println(d2);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(f);
        System.out.println(m);
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
    }

    public static class Human
    {
        private String name;
        private boolean sex;
        private int age;
        private Human father;
        private Human mother;

        public Human (String name, boolean sex, int age, Human father, Human mother){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }

}
