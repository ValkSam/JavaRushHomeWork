package com.javarush.test.level04.lesson06.task01;

/* Минимум двух чисел
Ввести с клавиатуры два числа, и вывести на экран минимальное из них.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int min = Integer.MAX_VALUE;
        for(int i = 0; i<2; i++){
            try{
                min = Math.min(min, new Integer(reader.readLine()));
            }
            catch (NumberFormatException e){
                System.out.println("Необходимо ввести число !. Повторите ввод ...");
                i--;
            }
        }
        System.out.println(min);
        /* данные для тестирования:
        введите 1 и 2, минимум =  1
        введите 2 и 1, минимум =  1
        введите 1 и 1, минимум =  1
        введите -1 и -2, минимум =  -2
         */
    }
}