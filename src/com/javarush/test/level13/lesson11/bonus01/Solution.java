package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution
{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        InputStream fileIn = new FileInputStream(in.readLine());

        StringBuilder text = new StringBuilder();
        while (fileIn.available()>0){
            text.append((char)fileIn.read());
        }
        fileIn.close();
        in.close();

        ArrayList<Integer> result = new ArrayList<Integer>();
        for (String x: text.toString().split("\r\n")){
            int d = Integer.valueOf(x);
            if (d%2 == 0) {result.add(d);}
        }
        Collections.sort(result);

        for (int x: result){
            System.out.println(x);
        }
    }
}
