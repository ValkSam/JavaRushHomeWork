package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит слова, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки
*/

import java.io.*;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader fi = new BufferedReader(new FileReader(args[0]));
        PrintWriter fo = new PrintWriter(args[1]);

        String line;
        Double currValue;
        while ((line = fi.readLine()) != null) {
            String[] strArr = line.split("\\s");
            for (String s : strArr) {
                if (s.matches(".*\\d+.*")) {
                    fo.print(s + " ");
                }
            }
        }
        fi.close();
        fo.close();
    }
}
