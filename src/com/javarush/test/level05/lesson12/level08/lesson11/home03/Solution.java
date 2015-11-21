package com.javarush.test.level08.lesson11.home03;

import java.util.HashMap;
import java.util.Map;

/* Люди с одинаковыми именами и/или фамилиями
1. Создать словарь Map (<String, String>) и добавить туда 10 человек в виде «Фамилия»-«Имя».
2. Пусть среди этих 10 человек есть люди с одинаковыми именами.
3. Пусть среди этих 10 человек есть люди с одинаковыми фамилиями.
4. Вывести содержимое Map на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Map<String, String> map = createPeopleList();
        printPeopleList(map);
    }
    public static Map<String, String> createPeopleList()
    {
        Map<String, String> result = new HashMap<String, String>();
        result.put("fam1","name1");
        result.put("fam2","name1");
        result.put("fam3","name2");
        result.put("fam4","name2");
        result.put("fam5","name3");
        result.put("fam6","name3");
        result.put("fam7","name4");
        result.put("fam3","name4");
        result.put("fam4","name5");
        result.put("fam5","name5");
        return result;
    }
    public static void printPeopleList(Map<String, String> map)
    {
        for (Map.Entry<String, String> s : map.entrySet())
        {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }

}
