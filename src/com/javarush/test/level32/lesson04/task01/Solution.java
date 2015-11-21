package com.javarush.test.level32.lesson04.task01;

import java.io.*;

/* Пишем стек-трейс
Реализуйте логику метода getStackTrace, который в виде одной строки должен возвращать весь стек-трейс переданного исключения.
Используйте подходящий метод класса Throwable, который поможет записать стек-трейс в StringWriter.
*/
public class Solution {
    public static void main(String[] args) {
        String text = getStackTrace(new IndexOutOfBoundsException("fff"));
        System.out.println(text);
    }

    public static String getStackTrace(Throwable throwable) {
        CharArrayWriter caw = new CharArrayWriter();
        throwable.printStackTrace(new PrintWriter(caw));
        return caw.toString();

        //вариант сложнее:

        /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
        throwable.printStackTrace(new PrintStream(baos));
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()));
        StringBuilder sb = new StringBuilder();
        try {
            while (isr.ready()){
                sb.append((char)isr.read());
            }
        } catch (IOException e) {
        }
        return sb.toString();*/
    }
}
