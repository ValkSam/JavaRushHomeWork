package com.javarush.test.level14.lesson08.bonus01;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    { //it's first exception
        try
        {
            float i = 1 / 0;
        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            Double i = null;
            i.toString();
        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            int[] arr = new int[0];
            int i = arr[1];
        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            int i = Integer.valueOf("");
        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            FileReader fr = new FileReader("asasa");
        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            InputStreamReader in = new InputStreamReader(((InputStream)new Object()));
        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            String str = "qqq";
            char chr = str.charAt(5);
        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            String str = new ArrayList<String>().get(1);
        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            Object[] str = new String[10];
            str[0] = new Character('*');
        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            int [] a = new int[-1];
        } catch (Exception e)
        {
            exceptions.add(e);
        }
    }
}
