package com.javarush.test.level19.lesson05.task05;

/* Пунктуация
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Удалить все знаки пунктуации, вывести во второй файл.
http://ru.wikipedia.org/wiki/%D0%9F%D1%83%D0%BD%D0%BA%D1%82%D1%83%D0%B0%D1%86%D0%B8%D1%8F
Закрыть потоки ввода-вывода.
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов.
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
        String buf = str.toString().replaceAll("\\p{Punct}","");
        fo.write(buf,0,buf.length());
        fi.close();
        fo.close();
    }
}
