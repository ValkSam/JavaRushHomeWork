package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.valueOf(in.readLine());
        int b = Integer.valueOf(in.readLine());
        int nod = Math.min(a,b);
        a = Math.max(a,b);
        b = nod;
        while (a%b != 0){
            nod = a%b;
            a = b;
            b = nod;
        }
        System.out.println(nod);
    }
}
