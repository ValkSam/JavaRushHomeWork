package com.javarush.test.level04.lesson02.task01;

/* Реализовать метод setName
Реализовать метод setName, чтобы с его помощью можно было устанавливать значение переменной private String name равное переданному параметру String name.
*/

public class Cat {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public static void main (String[] args) throws java.lang.Exception{
        Cat cat1 = new Cat();
        cat1.setName("Mashka");
        System.out.println(cat1.name);
    }
}

