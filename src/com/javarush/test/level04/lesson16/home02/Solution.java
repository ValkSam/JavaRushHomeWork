package com.javarush.test.level04.lesson16.home02;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

import java.util.Scanner;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        Scanner in = new Scanner(System.in);
        int v1 = Integer.valueOf(in.nextLine());
        int v2 = Integer.valueOf(in.nextLine());
        int v3 = Integer.valueOf(in.nextLine());
        int vMax = Math.max(v1, Math.max(v2, v3));
        int vMin = Math.min(v1, Math.min(v2, v3));
        if ((v1 != vMax)&(v1 != vMin)) {System.out.println(v1);return;}
        if ((v2 != vMax)&(v2 != vMin)) {System.out.println(v2);return;}
        if ((v3 != vMax)&(v3 != vMin)) {System.out.println(v3);return;}
        System.out.println();
    }


}
