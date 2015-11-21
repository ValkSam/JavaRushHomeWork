package com.javarush.test.level18.lesson03.task05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.TreeSet;

/* Сортировка байт
Ввести с консоли имя файла
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран
Закрыть поток ввода-вывода

Пример байт входного файла
44 83 44

Пример вывода
44 83
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fin = new FileInputStream(in.readLine());
        in.close();
        Integer curr;
        TreeSet<Integer> set = new TreeSet<Integer>();
        while ((curr = fin.read()) != -1){
            set.add(curr);
        }
        fin.close();
        for (Integer x : set){
            System.out.print(x+" ");
        }
    }
}
