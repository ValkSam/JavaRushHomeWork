package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fi = new FileInputStream(in.readLine());
        FileOutputStream fo = new FileOutputStream((in.readLine()));
        in.close();
        while (fi.available()>1){
            fi.read();
            fo.write(fi.read());
        }
        fi.close();
        fo.close();
    }
}
