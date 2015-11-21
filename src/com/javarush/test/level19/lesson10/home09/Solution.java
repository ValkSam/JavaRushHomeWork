package com.javarush.test.level19.lesson10.home09;

/* Контекстная реклама
В методе main подмените объект System.out написанной вами реадер-оберткой
Ваша реадер-обертка должна выводить на консоль контекстную рекламу после каждого второго println-а
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Рекламный текст: "JavaRush - курсы Java онлайн"

Пример вывода:
first
second
JavaRush - курсы Java онлайн
third
fourth
JavaRush - курсы Java онлайн
fifth
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(buf);
        PrintStream out = System.out;
        System.setOut(newOut);

        testString.printSomething();

        System.setOut(out);

        String[] lineArr = buf.toString().split("\\n");
        int lineNum = 0;
        for (int i = 0; i<lineArr.length; i++){
            System.out.println(lineArr[i]);
            lineNum++;
            if (lineNum == 2) {
                System.out.println("JavaRush - курсы Java онлайн");
                lineNum = 0;
            }
        }

        newOut.close();

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
