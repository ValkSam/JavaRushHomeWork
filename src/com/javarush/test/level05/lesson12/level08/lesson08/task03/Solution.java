package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Петров","Коля");
        map.put("Иванов","Коля");
        map.put("Сидоров","Коля");
        map.put("Петрочук","Женя");
        map.put("Иванчук","Женя");
        map.put("Сидорчук","Женя");
        map.put("Петровник","Вася");
        map.put("Ивановник","Вася");
        map.put("Сидоровник","Вася");
        map.put("Васечкин","Петя");
        return map;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        int i = 0;
        for (String str : map.values()) if (str.equals(name)) i++;
        return i;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String familiya)
    {
        int i = 0;
        for (String str : map.keySet()) if (str.equals(familiya)) i++;
        return i;
    }


}
