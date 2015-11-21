package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать содержимое третьего файла
Закрыть потоки
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.security.spec.ECField;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fo = new FileWriter(reader.readLine());
        FileReader fi1 = new FileReader(reader.readLine());
        FileReader fi2 = new FileReader(reader.readLine());
        while (fi1.ready()){
            fo.write(fi1.read());
        }
        while (fi2.ready()){
            fo.write(fi2.read());
        }
        fo.close();
        fi1.close();
        fi2.close();

    }
}
