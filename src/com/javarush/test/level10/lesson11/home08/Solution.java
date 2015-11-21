package com.javarush.test.level10.lesson11.home08;


import java.util.ArrayList;

/* Массив списков строк
Создать массив, элементами которого будут списки строк. Заполнить массив любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<String>[] arrayOfStringList =  createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList()
    {
        /*
        return new ArrayList[] {    new ArrayList<String>(Arrays.asList("aaa","sss")),
                                    new ArrayList<String>(Arrays.asList("zzz","xxx")),
                                    new ArrayList<String>(Arrays.asList("qqq","www"))};
        */
        /*
        ArrayList<String>[] result = new ArrayList[3];
        result[0] = new ArrayList(Arrays.asList("aaa","sss"));
        result[1] = new ArrayList(Arrays.asList("zzz","xxx"));
        result[2] = new ArrayList(Arrays.asList("qqq","www"));
        return result;
        */
        ArrayList<String>[] result = new ArrayList[3];
        result[0] = new ArrayList();
        result[0].add("aaa");
        result[0].add("sss");
        result[1] = new ArrayList();
        result[1].add("zzz");
        result[1].add("xxx");
        result[2] = new ArrayList();
        result[2].add("qqq");
        result[2].add("www");
        return result;

    }

    public static void printList(ArrayList<String>[] arrayOfStringList)
    {
        for (ArrayList<String> list: arrayOfStringList)
        {
            for (String s : list)
            {
                System.out.println(s);
            }
        }
    }
}