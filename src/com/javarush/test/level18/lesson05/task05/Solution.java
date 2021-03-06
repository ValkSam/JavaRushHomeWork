package com.javarush.test.level18.lesson05.task05;

/* DownloadException
1 Считывать с консоли имена файлов.
2 Если файл меньше 1000 байт, то:
2.1 Закрыть все потоки ввода-вывода
2.2 выбросить исключение DownloadException
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                if (new File(in.readLine()).length() < 1000) {
                    in.close();
                    throw new DownloadException();
                }
            }
    }

    public static class DownloadException extends Exception{

    }
}
