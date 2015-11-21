package com.javarush.test.level05.lesson12.level08.lesson11.home09;

import java.util.Date;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(isDateOdd("JANUARY 1 2000"));
        System.out.println(isDateOdd("JANUARY 2 2020"));
        System.out.println(isDateOdd("MAY 21 2020"));
        System.out.println(isDateOdd("APRIL 22 2011"));

    }
    public static boolean isDateOdd(String date)
    {
        /*
        Date bd = new Date(date);
        bd.setDate(1);
        bd.setMonth(0);
        bd.setMinutes(0);
        bd.setHours(0);
        bd.setSeconds(0);

        int msPerDay = 24*60*60*1000;

        int days = (int)((new Date(date)).getTime() - bd.getTime())/msPerDay;
        return (days%2 != 0);
        */


        Date date1 = new Date(date);
        Date date2 = new Date(date);
        date1.setHours(0);
        date1.setMinutes(0);
        date1.setSeconds(0);
        date1.setDate(1);
        date1.setMonth(0);
        /*long msTimeDistance = date2.getTime() - date1.getTime();
        long msDay = 24 * 60 * 60 * 1000;
        int y = (int)(msTimeDistance/msDay);
        return (y % 2 == 0);
        */
        long x = (date2.getTime() - date1.getTime()+5000)/(1000*60*60*24)+1;
        long y = (date2.getTime() - date1.getTime())/(1000*60*60*24)+1;
        System.out.println(x % 2);
        System.out.println(y % 2);
        return true;

    }
}
