package com.javarush.test.level18.lesson03.task01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* Максимальный байт
Ввести с консоли имя файла
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String fileName = in.readLine();
            in.close();
            FileInputStream fin = new FileInputStream(fileName);
            int max = fin.read();
            while (fin.available()>0){
                int curr = fin.read();
                if (curr > max) max = curr;
            }
            System.out.println(max);
            fin.close();

    }
}
