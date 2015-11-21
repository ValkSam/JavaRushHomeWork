package com.javarush.test.level05.lesson12.home04;

/* Вывести на экран сегодняшнюю дату
Вывести на экран текущую дату в аналогичном виде "21 02 2014".
*/


import sun.util.calendar.BaseCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Solution
{
    public static void main(String[] args)
    {
        /*
        Calendar today = new GregorianCalendar();
        String dateFormat = today.get(Calendar.DAY_OF_MONTH)+" "+today.get(Calendar.MONTH)+" "+today.get(Calendar.YEAR);
        System.out.println(dateFormat);
        */

        /*
        Date today = new Date();
        String dateFormat = today.getDay()+" "+today.getMonth()+" "+(today.getYear()+1900);
        System.out.println(dateFormat);
        System.out.println(today);
        */
        System.out.println((new SimpleDateFormat("dd MM yyyy")).format(new Date()));



    }
}
