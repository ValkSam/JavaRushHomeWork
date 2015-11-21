package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки ввода-вывода
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String file1 = in.readLine();
            String file2 = in.readLine();
            in.close();
            FileInputStream inf_1 = new FileInputStream(file1);
            FileOutputStream outf_2 = new FileOutputStream(file2);
            byte[] buf = new byte[inf_1.available()];
            inf_1.read(buf);
            for (int i = buf.length-1; i>=0; i--){
                outf_2.write(buf[i]);
            }
            inf_1.close();
            outf_2.close();
        }
        catch (Exception e){}


    }
}
