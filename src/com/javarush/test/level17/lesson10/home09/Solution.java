package com.javarush.test.level17.lesson10.home09;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines
4. Если список allLines НЕ содержит каких-либо строк, которые есть в forRemoveLines, то
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
Сигнатуру метода main не менять.  Метод joinData должен вызываться в main.
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fin1 = new BufferedReader(new FileReader(in.readLine()));
        BufferedReader fin2 = new BufferedReader(new FileReader(in.readLine()));
        String str;
        while (!((str = fin1.readLine())==null)){
            allLines.add(str);
        }
        while (!((str = fin2.readLine())==null)){
            forRemoveLines.add(str);
        }
        fin1.close();
        fin2.close();
        in.close();
        new Solution().joinData();
    }

    public void joinData () throws CorruptedDataException {
        for (String x : forRemoveLines){
            if (! allLines.remove(x)) {
                allLines.clear();
                throw new CorruptedDataException();
            }
        }

    }
}
