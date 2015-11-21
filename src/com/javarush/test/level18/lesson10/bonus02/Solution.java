package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        if (args.length < 4) return;
        if ("-c".equals(args[0])) {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String fName = in.readLine();
            BufferedReader fi = new BufferedReader(new FileReader(fName));
            String line;
            ArrayList<String> aList = new ArrayList<String>();
            Integer maxId = 0;
            Integer currId;
            while ((line = fi.readLine()) != null) {
                aList.add(line);
                currId = Integer.valueOf(line.substring(0, 8).trim());
                if (currId > maxId) maxId = currId;
            }
            in.close();
            fi.close();

            String id = String.valueOf(maxId+1);
            id += repeatString(" ", 8 - id.length());
            String quantity = args[args.length - 1];
            quantity += repeatString(" ", 4 - quantity.length());
            String price = args[args.length - 2];
            price += repeatString(" ", 8 - price.length());
            String name = args[1];
            for (int i = 2; i < args.length - 2; i++) name += " " + args[i];
            name += repeatString(" ", 30 - name.length());
            name = name.substring(0, 30);
            PrintWriter fo = new PrintWriter(fName);
            for (String s : aList) fo.println(s);
            fo.println(id + name + price + quantity);
            fo.close();
        }
    }
    private static String repeatString (String s, int n){
        StringBuilder result = new StringBuilder();
        for (int i = 0 ; i<n; i++) result.append(s);
        return result.toString();
    }
}
