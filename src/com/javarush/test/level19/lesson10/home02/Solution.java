package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception
    {
        BufferedReader in = new BufferedReader(new FileReader (args[0]));
        TreeMap<String, Double> list = new TreeMap<String, Double>();
        String line;
        Double currValue;
        while ((line = in.readLine()) != null) {
            String name = line.substring(0, line.lastIndexOf(" "));
            String value = line.substring(line.lastIndexOf(" "));
            list.put(name, (currValue = list.get(name)) == null ? Double.valueOf(value) : currValue+Double.valueOf(value));
        }
        in.close();
        String[][] resultList =  new String[list.size()][2];
        int i = 0;
        for (Map.Entry<String, Double> pair : list.entrySet()){
            resultList[i][0] = pair.getKey();
            resultList[i][1] = pair.getValue()+"";
            i++;
        }

        Arrays.sort(resultList, new Comparator<String[]>(){
            @Override
            public int compare(String[] s1, String[] s2){
                return (-1)*Double.valueOf(s1[1]).compareTo(Double.valueOf(s2[1]));
            }
        });

        String maxValue = resultList[0][1];
        for (String[] pair : resultList){
            if (! maxValue.equals(pair[1])) return;
            System.out.println(pair[0]);
        }
    }
}
