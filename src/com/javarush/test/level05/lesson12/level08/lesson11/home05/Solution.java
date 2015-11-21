package com.javarush.test.level05.lesson12.level08.lesson11.home05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Мама Мыла Раму. Теперь с большой буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа заменяет в тексте первые буквы всех слов на заглавные.
Вывести результат на экран.

Пример ввода:
  мама     мыла раму.

Пример вывода:
  Мама     Мыла Раму.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        StringBuilder sb = new StringBuilder(" "+s);
        for (int i = 0; i < sb.length()-1; i++){
            if (sb.substring(i,i+1).equals(" ")) sb.setCharAt(i+1,sb.substring(i+1,i+2).toUpperCase().charAt(0));
        }
        s = sb.toString().trim();
        System.out.println(s);
    }


}
