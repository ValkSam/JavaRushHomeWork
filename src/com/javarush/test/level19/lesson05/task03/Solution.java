package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки ввода-вывода.

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        FileReader fi = new FileReader(in.readLine());
        FileWriter fo = new FileWriter(in.readLine());
        in.close();
        String str = "";
        while (fi.ready()){
            str = str + (String.valueOf((char)fi.read()));
        }
        str = str.replaceAll("\\s","  ");
        Matcher matcher = Pattern.compile("(^\\d+\\s|\\s\\d+\\s|\\s\\d+$)").matcher(str);
        String buf = "";
        while (matcher.find()){
            buf = buf + matcher.group();
        }
        buf = (buf.replaceAll("  "," ")).trim();
        fo.write(buf,0,buf.length());
        fi.close();
        fo.close();
    }
}
