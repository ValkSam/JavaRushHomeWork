package com.javarush.test.level25.lesson09.task03;

import java.util.ArrayList;
import java.util.Arrays;

/* Живем своим умом
В классе Solution реализуйте интерфейс UncaughtExceptionHandler, который должен:
1. прервать нить, которая бросила исключение.
2. вывести в консоль стек исключений начиная с самого вложенного.
Пример исключения: new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")))
Пример вывода:
java.lang.IllegalAccessException: GHI
java.lang.RuntimeException: DEF
java.lang.Exception: ABC
*/
public class Solution implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        ArrayList<String> throwables = new ArrayList<>();
        Throwable err = e;
        while (err != null){
            throwables.add(0,err.toString());
            err = err.getCause();
        }
        for (String el : throwables) {
            System.out.println(el);
        }
    }


}
