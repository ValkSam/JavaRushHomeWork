package com.javarush.test.level19.lesson10.home08;

/* Перевертыши
1 Считать с консоли имя файла.
2 Для каждой строки в файле:
2.1 переставить все символы в обратном порядке
2.2 вывести на экран
3 Закрыть поток

Пример тела входного файла:
я - программист.
Амиго

Пример результата:
.тсиммаргорп - я
огимА
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fi = new BufferedReader(new FileReader(in.readLine()));

        String line;
        while((line = fi.readLine()) != null){
            StringBuilder str = new StringBuilder().append(line);
            str.reverse();
            System.out.println(str.toString());
        }
        in.close();
        fi.close();

    }
}
