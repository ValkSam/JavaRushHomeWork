package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
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
            String file3 = in.readLine();
            in.close();
            FileInputStream inf_1 = new FileInputStream(file1);
            FileOutputStream outf_2 = new FileOutputStream(file2);
            FileOutputStream outf_3 = new FileOutputStream(file3);
            Integer fileSize = inf_1.available();
            Integer firstHalf = fileSize/2 + fileSize%2;
            byte[] buf2 = new byte[firstHalf];
            byte[] buf3 = new byte[fileSize - firstHalf];
            inf_1.read(buf2);
            inf_1.read(buf3);
            outf_2.write(buf2);
            outf_3.write(buf3);
            inf_1.close();
            outf_2.close();
            outf_3.close();
        }
        catch (Exception e){}

    }
}
