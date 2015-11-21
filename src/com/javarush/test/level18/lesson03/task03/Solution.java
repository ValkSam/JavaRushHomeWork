package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Самые частые байты
Ввести с консоли имя файла
Найти байты, которые чаше всех встречаются в файле
Вывести их на экран через пробел.
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fin = new FileInputStream (in.readLine());
        in.close();
        Integer curr;
        Integer max = 0;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        while ((curr = fin.read()) != -1){
            map.put(curr, map.get(curr) == null? 1:map.get(curr)+1);
            if (map.get(curr) > max) max = map.get(curr);
        }
        fin.close();
        for (Map.Entry<Integer, Integer> pair : map.entrySet()){
            if (pair.getValue() == max){
                System.out.print(pair.getKey()+" ");
            }
        }
    }

}
