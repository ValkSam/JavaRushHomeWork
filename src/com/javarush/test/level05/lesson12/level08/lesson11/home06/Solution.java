package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;
import java.util.Arrays;

public class Solution
{
    public static void main(String[] args)
    {
        Human c1 = new Human("child1",true,1,new ArrayList(Arrays.asList()));
        Human c2 = new Human("child2",true,2,new ArrayList(Arrays.asList()));
        Human c3 = new Human("child2",false,2,new ArrayList(Arrays.asList()));
        Human f = new Human("father",true,30,new ArrayList(Arrays.asList(c1,c2,c3)));
        Human m = new Human("mother",false,30,new ArrayList(Arrays.asList(c1,c2,c3)));
        Human d1 = new Human("gfather1",true,60,new ArrayList(Arrays.asList(f)));
        Human d2 = new Human("gfather2",true,60,new ArrayList(Arrays.asList(m)));
        Human b1 = new Human("gmother1",false,60,new ArrayList(Arrays.asList(f)));
        Human b2 = new Human("gmother2",false,60,new ArrayList(Arrays.asList(m)));
        //
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(f);
        System.out.println(m);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }
    public static class Human
    {
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children = new ArrayList<Human>();

        public Human(String name, boolean sex, int age, ArrayList<Human> children){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;
            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;
                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }
            return text;
        }
    }

}
