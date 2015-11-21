package com.javarush.test.level09.lesson11.home08;

import java.util.ArrayList;
import java.util.Arrays;

/* Список из массивов чисел
Создать список, элементами которого будут массивы чисел. Добавить в список пять объектов–массивов длиной 5, 2, 4, 7, 0 соответственно. Заполнить массивы любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<int[]> list = createList();
        printList(list);
    }

    public static ArrayList<int[]> createList()
    {
        ArrayList result = new ArrayList();
        result.add(new int[]{1,2,3,4,5});
        result.add(new int[]{11,22});
        result.add(new int[]{111,222,333,444});
        result.add(new int[]{1111,2222,3333,4444,5555,6666,7777});
        result.add(new int[0]);
        return result;
    }

    public static void printList(ArrayList<int[]> list)
    {
        for (int[] array: list )
        {
            for (int x: array)
            {
                System.out.println(x);
            }
        }
    }
}
