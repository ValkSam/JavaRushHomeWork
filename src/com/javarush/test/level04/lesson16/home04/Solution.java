package com.javarush.test.level04.lesson16.home04;

/* Меня зовут 'Вася'...
Ввести с клавиатуры строку name.
Ввести с клавиатуры дату рождения (три числа): y, m, d.
Вывести на экран текст:
«Меня зовут name
Я родился d.m.y»
Пример:
Меня зовут Вася
Я родился 15.2.1988
*/

import java.util.Scanner;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        int y = Integer.valueOf(in.nextLine());
        int m = Integer.valueOf(in.nextLine());
        int d = Integer.valueOf(in.nextLine());
        System.out.println("Меня зовут " + name);
        System.out.println("Я родился " + d+"."+m+"."+y);
    }
}
