package com.javarush.test.level08.lesson08.task04;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{
    public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Петров",new Date("JUNE 1 1980"));
        map.put("Иванов",new Date("MAY 1 1980"));
        map.put("Сидоров",new Date("MAY 1 1980"));
        map.put("Петрочук",new Date("JUNE 1 1980"));
        map.put("Иванчук",new Date("MAY 1 1980"));
        map.put("Сидорчук",new Date("JUNE 1 1980"));
        map.put("Петровник",new Date("MAY 1 1980"));
        map.put("Ивановник",new Date("JUNE 1 1980"));
        map.put("Сидоровник",new Date("MAY 1 1980"));
        map.put("Васечкин", new Date("JUNE 1 1980"));
        return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        for (Iterator<Date> iterator = map.values().iterator(); iterator.hasNext();){
            int m = ((Date)iterator.next()).getMonth();
            if ((m>=5)&&(m<=7)) iterator.remove();
        }
    }


}
