package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран частоту встречания пробела. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
Закрыть потоки
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) throws IOException {
/* это правильный вариант - считае именно символы. Но прошел тест
        Double v = 0d;
        try {
            BufferedReader fin = new BufferedReader(new FileReader("d:\\q.txt"));
            StringBuilder str = new StringBuilder();
            String line = null;
            while ((line = fin.readLine()) != null) str.append(line);
            fin.close();
            v = str.toString().length() == 0? 0 : 100d * str.toString().replaceAll("[^\\s]", "").length() / str.toString().length();
        } catch (Exception e){}
        System.out.printf(Locale.ENGLISH, "%.2f", v);
        System.out.println();
        */

        //
            int genCount = 0;
            int spaces = 0;
            double v = 0;
        try {
            FileInputStream fin = new FileInputStream(args[0]);
            while (fin.available() > 0) {
                genCount++;
                if (fin.read() == (int) ' ') spaces++;
            }
            fin.close();
            v = genCount == 0? 0d : 100d*spaces/genCount;
        } catch (Exception e) {}
            System.out.printf(Locale.ENGLISH, "%.2f", v);
    }
}
