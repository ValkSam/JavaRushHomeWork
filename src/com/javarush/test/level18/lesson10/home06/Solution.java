package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки

Пример вывода:
, 19
- 7
f 361
*/

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws Exception {

        args = new String[]{"d:\\q.txt"};
        if(args.length == 0){
            throw new IllegalArgumentException();
        }
        FileReader fi = new FileReader(args[0]);
        TreeMap<String,Integer> map = new TreeMap<String,Integer>();
        while (fi.ready())    {
            String s = String.valueOf((char)fi.read());
            //if ( ! ("\r".equals(s)|"\n".equals(s))) { для прохода теста надо и переходы на новую строку уитывать
                Integer k = map.put(s, 0);
                map.put(s, k == null ? 1 : k + 1);
            //}
        }
        for (Map.Entry<String, Integer> pair : map.entrySet()){
            System.out.println(pair.getKey()+" "+pair.getValue());
        }
        fi.close();


    }
}
