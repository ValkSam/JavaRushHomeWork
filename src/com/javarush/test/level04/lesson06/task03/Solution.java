package com.javarush.test.level04.lesson06.task03;

/* Сортировка трех чисел
Ввести с клавиатуры три числа, и вывести их в порядке убывания.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int DIG_COUNT = 3;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList intArr = new ArrayList();
        for (int i = 0; i < DIG_COUNT; i++) {
            //System.out.print("введите "+(i+1)+"-е число: ");
            try{
                intArr.add(new Integer(reader.readLine()));
            }
            catch (NumberFormatException e){
                System.out.println("Необходимо ввести целое число. Повторите ввод.");
                i--;
            }
        }
        Collections.sort(intArr);
        {
            for (int i = intArr.size()-1; i >= 0 ; i--) {
                System.out.println(intArr.get(i));
            }
        }

    }
}
