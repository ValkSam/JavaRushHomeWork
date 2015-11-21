package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть поток ввода.
*/

import java.io.BufferedReader;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fi = new BufferedReader (new FileReader(in.readLine()));
        Pattern pattern = Pattern.compile("(^world\\s|\\sworld\\s|\\sworld$)");
        int count = 0;
        while (fi.ready()) {
            StringBuilder str = new StringBuilder();
            str.append(fi.readLine());
            Matcher matcher = pattern.matcher(str.toString().replaceAll("\\p{Punct}", " "));
            while (matcher.find()) count++;
        }
        System.out.println(count);
        fi.close();
        in.close();
    }
}
