package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) return;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String fName = in.readLine();
        BufferedReader fi = new BufferedReader(new FileReader(fName));
        if ("-u".equals(args[0])) {
            if (args.length < 5) return;
            ArrayList<String> aList = new ArrayList<String>();
            String id = args[1];
            id += repeatString(" ", 8 - id.length());
            String currId;
            String line;
            while ((line = fi.readLine()) != null) {
                currId = line.substring(0, 8);
                if (currId.equals(id)) {
                    String quantity = args[args.length - 1];
                    quantity += repeatString(" ", 4 - quantity.length());
                    String price = args[args.length - 2];
                    price += repeatString(" ", 8 - price.length());
                    String name = args[2];
                    for (int i = 3; i < args.length - 2; i++) name += " " + args[i];
                    name += repeatString(" ", 30 - name.length());
                    name = name.substring(0, 30);
                    line = id + name + price + quantity;
                }
                aList.add(line);
            }
            in.close();
            fi.close();

            PrintWriter fo = new PrintWriter(fName);
            for (String s : aList) fo.println(s);
            fo.close();
        }
        if ("-d".equals(args[0])) {
            ArrayList<String> aList = new ArrayList<String>();
            String id = args[1];
            id += repeatString(" ", 8 - id.length());
            String currId;
            String line;
            while ((line = fi.readLine()) != null) {
                currId = line.substring(0, 8);
                if (! currId.equals(id)) {
                    aList.add(line);
                }
            }
            in.close();
            fi.close();

            PrintWriter fo = new PrintWriter(fName);
            for (String s : aList) fo.println(s);
            fo.close();
        }
    }
    private static String repeatString (String s, int n){
        StringBuilder result = new StringBuilder();
        for (int i = 0 ; i<n; i++) result.append(s);
        return result.toString();
    }
}
