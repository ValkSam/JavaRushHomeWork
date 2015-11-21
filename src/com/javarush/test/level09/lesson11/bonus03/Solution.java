package com.javarush.test.level09.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* Задача по алгоритмам
Задача: Пользователь вводит с клавиатуры список слов (и чисел). Слова вывести в возрастающем порядке, числа - в убывающем.
Пример ввода:
Вишня
1
Боб
3
Яблоко
2
0
Арбуз
Пример вывода:
Арбуз
3
Боб
2
Вишня
1
0
Яблоко
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<String>();
        while (true)
        {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s);
        }

        String[] array = list.toArray(new String[list.size()]);
        sort(array);

        for (String x : array)
        {
            System.out.println(x);
        }
    }

    public static void sort(String[] array)
    {
        ArrayList<String> strArr = new ArrayList<String>();
        ArrayList<Integer> numArr = new ArrayList<Integer>();
        for (String x : array){
            if (isNumber(x)){
                numArr.add(Integer.valueOf(x));
            }
            else {
                strArr.add(x);
            }
        }

        for (int i = 1; i < numArr.size(); i++) {   //только для теста. А вообще Collections.sort(numArr, Collections.reverseOrder());
                                                    // или Collections.sort(numArr); + Collections.reverse(numArr);
            for (int j = 0; j < numArr.size()-i; j++) {
                if (numArr.get(j)<numArr.get(j+1)){
                    Collections.swap(numArr,j,j+1);
                }
            }
        }

        for (int i = 1; i < strArr.size(); i++) { //только, для теста. А вообще Collections.sort(strArr);
            for (int j = 0; j < strArr.size()-i; j++) {
                if (isGreaterThen(strArr.get(j),strArr.get(j+1))){
                    Collections.swap(strArr, j, j + 1);
                }
            }
        }

        int j = 0;
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if (isNumber(array[i])){
                array[i] = Integer.toString(numArr.get(j));
                j++;
            }
            else {
                array[i] = strArr.get(k);
                k++;
            }
        }
    }

    //Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThen(String a, String b)
    {
        return a.compareTo(b) > 0;
    }


    //строка - это на самом деле число?
    public static boolean isNumber(String s)
    {
        if (s.length() == 0) return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            char c = chars[i];
            if ((i != 0 && c == '-') //есть '-' внутри строки
                    || (!Character.isDigit(c) && c != '-') ) // не цифра и не начинается с '-'
            {
                return false;
            }
        }
        return true;
    }
}
