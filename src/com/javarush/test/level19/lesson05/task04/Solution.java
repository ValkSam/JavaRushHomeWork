package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки ввода-вывода.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        FileReader fi = new FileReader(in.readLine());
        FileWriter fo = new FileWriter(in.readLine());
        in.close();
        StringBuilder str = new StringBuilder();
        while (fi.ready()){
            str.append(String.valueOf((char)fi.read()));
        }
        String buf = str.toString().replaceAll("\\.","!");
        fo.write(buf,0,buf.length());
        fi.close();
        fo.close();
    }
}
