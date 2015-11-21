package com.javarush.test.level18.lesson03.task02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* Минимальный байт
Ввести с консоли имя файла
Найти минимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String fileName = in.readLine();
        in.close();
        FileInputStream fin = new FileInputStream(fileName);
        int min = fin.read();
        while (fin.available()>0){
            int curr = fin.read();
            if (curr < min) min = curr;
        }
        System.out.println(min);
        fin.close();
    }
}
