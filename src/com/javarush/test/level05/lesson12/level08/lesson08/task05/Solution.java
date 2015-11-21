package com.javarush.test.level08.lesson08.task05;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
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
        map.put("Иванчук","Боря");
        map.put("Сидорчук","Женя");
        map.put("Петровник","Вася");
        map.put("Ивановник","Вася");
        map.put("Сидоровник","Вася");
        map.put("Васечкин","Петя");
        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        String[] arr = map.values().toArray(new String[map.size()]);
        Arrays.sort(arr);
        boolean d = false;
        String currStr = "";
        for (String str : arr) {
            if (! str.equals(currStr)){
                currStr = str;
                d = false;
            }
            else if (!d) {
                while (map.values().remove(str)){};
                d = true;
            }
        }
    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {
        removeTheFirstNameDuplicates(createMap());
    }
}
