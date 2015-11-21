package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки
*/

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.FileReader;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader fin = new BufferedReader(new FileReader(args[0]));
            String line = null;
            StringBuffer str = new StringBuffer();
            while ((line = fin.readLine())!=null) str.append(line);
            System.out.println(str.toString().replaceAll("[^A-Za-z]", "").length());
            fin.close();
        } catch (Exception e){}
    }
}
