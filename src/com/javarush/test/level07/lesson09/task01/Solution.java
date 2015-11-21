package com.javarush.test.level07.lesson09.task01;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Три массива
1. Введи с клавиатуры 20 чисел, сохрани их в список и рассортируй по трём другим спискам:
Число делится на 3 (x%3==0), делится на 2 (x%2==0) и все остальные.
Числа, которые делятся на 3 и на 2 одновременно, например 6, попадают в оба списка.
2. Метод printList должен выводить на экран все элементы списка  с новой строки.
3. Используя метод printList выведи эти три списка на экран. Сначала тот, который для x%3, потом тот, который для x%2, потом последний.
*/

public class Solution
{
    private final static int NUMBER_COUNT = 20;
    static ArrayList<Integer> mainList = new ArrayList<Integer>();
    static ArrayList<Integer> listFor2 = new ArrayList<Integer>();
    static ArrayList<Integer> listFor3 = new ArrayList<Integer>();
    static ArrayList<Integer> listForRest = new ArrayList<Integer>();
    public static void main(String[] args) throws Exception
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < NUMBER_COUNT; i++) {
            int x=Integer.valueOf(in.readLine());
            mainList.add(x);
            Boolean f = false;
            if (x%2==0) {listFor2.add(x); f = true;};
            if (x%3==0) {listFor3.add(x); f = true;};
            if (!f) listForRest.add(x);
        }
        printList(listFor3);
        printList(listFor2);
        printList(listForRest);
    }

    public static void printList(List<Integer> list) {
        for (int x : list) System.out.println(x);
        System.out.println();
    }
}
