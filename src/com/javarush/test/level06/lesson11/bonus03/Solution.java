package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    private final static Integer NUMBER_ENTER_COUNT = 5;
    private static Integer[] arr = new Integer[NUMBER_ENTER_COUNT];
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i<NUMBER_ENTER_COUNT; i++){
            try{
                arr[i] = Integer.valueOf(reader.readLine());
            }
            catch (NumberFormatException e){
                System.out.println("необходимо ввести число, повторите ввод ... ");
                i--;
            }
        }
        for (int i = 1; i<arr.length; i++){
            for (int j = 0; j<arr.length-i; j++){
                if (arr[j]>arr[j+1]){
                    int tmp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        for (int i = 0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
