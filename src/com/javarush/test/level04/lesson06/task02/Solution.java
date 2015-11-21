package com.javarush.test.level04.lesson06.task02;

/* Максимум четырех чисел
Ввести с клавиатуры четыре числа, и вывести максимальное из них.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int max = Integer.MIN_VALUE;
        for(int i = 0; i<4; i++){
            try{
                max = Math.max(max, new Integer(reader.readLine()));
            }
            catch (NumberFormatException e){
                System.out.println("Необходимо ввести число !. Повторите ввод ...");
                i--;
            }
        }
        System.out.println(max);

    }
}
