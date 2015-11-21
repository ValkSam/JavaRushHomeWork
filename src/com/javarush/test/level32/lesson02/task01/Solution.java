package com.javarush.test.level32.lesson02.task01;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/* Запись в файл
В метод main приходят три параметра:
1) fileName - путь к файлу
2) number - число, позиция в файле
3) text - текст
Записать text в файл fileName начиная с позиции number.
Если файл слишком короткий, то записать в конец файла.
*/
public class Solution {
    public static void main(String... args) throws Exception {
        args = new String[3];
        args[0] = "d:/result.txt";
        args[1] = "10";
        args[2] = "asd";

        RandomAccessFile file = new RandomAccessFile(args[0],"rw");
        int position = Integer.valueOf(args[1]);
        String text = args[2];
        if ((file.length() > position)) {
            file.seek(position);
        } else {
            file.seek(file.length());
        }
        file.write(text.getBytes());
        file.close();
    }
}
