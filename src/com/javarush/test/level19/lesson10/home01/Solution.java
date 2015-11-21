package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception
    {
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
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

        for (String[] pair : resultList){
            System.out.println(pair[0]+" "+pair[1]);
        }
    }
}
