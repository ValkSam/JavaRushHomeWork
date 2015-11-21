package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String name1 = reader.readLine();
        FileReader fi1 = new FileReader(name1);
        FileReader fi2 = new FileReader(reader.readLine());
        ArrayList<Integer> str = new ArrayList<Integer>();
        while (fi1.ready()){
            str.add(fi1.read());
        }
        fi1.close();
        FileWriter fo = new FileWriter(name1);
        while (fi2.ready()){
            fo.write(fi2.read());
        }
        for (int b : str) fo.write(b);
        fo.close();
        fi2.close();
    }
}
