package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать во второй файл
Закрыть потоки
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
*/

import java.io.*;
import java.util.Locale;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        BufferedReader fi = new BufferedReader(new FileReader(reader.readLine()));
        PrintStream fo = new PrintStream(reader.readLine());

        while (fi.ready()){
            String str = fi.readLine();
            String[] arr = str.split(" ");
            for (int i=0; i<arr.length; i++){
                fo.print(Math.round(Double.valueOf(arr[i])));
                if (i < (arr.length-1)) {fo.print(" ");}
                else fo.println();
            }
        }

        fo.close();
        fi.close();

    }
}
