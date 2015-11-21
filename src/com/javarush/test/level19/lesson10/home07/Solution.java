package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader fi = new BufferedReader(new FileReader(args[0]));
        FileWriter fo = new FileWriter(args[1]);
        String line;
        boolean f = false;
        while ((line = fi.readLine()) != null){
            String[] words = line.split(" ");
            for (String w : words){
                if (w.length() > 6) {
                    if (f) fo.write(",");
                    fo.write(w);
                    f = true;
                }
            }
        }
        fi.close();
        fo.close();
    }
}
