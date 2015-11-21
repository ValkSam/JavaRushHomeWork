package com.javarush.test.level19.lesson10.bonus01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
[Файл 1]
строка1
строка2
строка3

[Файл 2]
строка1
строка3
строка4

[Результат - список lines]
SAME строка1
REMOVED строка2
SAME строка3
ADDED строка4
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main (String[] args) throws java.lang.Exception
    {
        ArrayList<String> file1 = new ArrayList<String>();
        ArrayList<String> file2 = new ArrayList<String>();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fi1 = new BufferedReader(new FileReader(in.readLine()));
        BufferedReader fi2 = new BufferedReader(new FileReader(in.readLine()));

        while (fi1.ready()) file1.add(fi1.readLine());
        while (fi2.ready()) file2.add(fi2.readLine());

        String currStr1;
        String currStr2;
        int i;
        Boolean lastIsSame = true;
        for (i = 0 ; i < file2.size(); i++){
            currStr2 = file2.get(i);
            if (i>=file1.size()) {
                if (! lastIsSame) break;
                lastIsSame = false;
                lines.add(new LineItem(Type.ADDED, currStr2));
            }
            else {
                currStr1 = file1.get(i);
                if (currStr2.equals(currStr1)){
                    lines.add(new LineItem(Type.SAME, currStr2));
                    lastIsSame = true;
                }
                else {
                    if ((i+1)>=(file1.size())) {
                        if (! lastIsSame) break;
                        lastIsSame = false;
                        lines.add(new LineItem(Type.ADDED, currStr2));
                        file2.remove(i);
                        i--;
                    }
                    else if (currStr2.equals(file1.get(i+1))){
                        if (! lastIsSame) break;
                        lastIsSame = false;
                        lines.add(new LineItem(Type.REMOVED, currStr1));
                        file1.remove(i);
                        i--;
                    }
                    else if (! currStr2.equals(file1.get(i+1))){
                        if (! lastIsSame) break;
                        lastIsSame = false;
                        lines.add(new LineItem(Type.ADDED, currStr2));
                        file2.remove(i);
                        i--;
                    }
                }
            }
        }

        for ( ; i < file1.size(); i++){
            currStr1 = file1.get(i);
            if (! lastIsSame) break;
            lastIsSame = false;
            lines.add(new LineItem(Type.REMOVED, currStr1));
        }

        for (LineItem s : lines){
            System.out.println(s.type+" "+s.line);
        }

    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
