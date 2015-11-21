package com.javarush.test.level18.lesson05.task02;

/* Подсчет запятых
С консоли считать имя файла
Посчитать в файле количество символов ',', количество вывести на консоль
Закрыть потоки ввода-вывода

Подсказка: нужно сравнивать с ascii-кодом символа ','
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            FileInputStream fin = new FileInputStream(in.readLine());
            in.close();
            int curr;
            int count = 0;
            while ((curr = fin.read()) != -1){
                if (curr == (byte)',') count++;
            }
            fin.close();
            System.out.println(count);
        }
        catch (Exception e){}
    }
}
