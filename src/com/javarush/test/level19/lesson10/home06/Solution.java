package com.javarush.test.level19.lesson10.home06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Замена чисел
1. В статическом блоке инициализировать словарь map парами [число-слово] от 0 до 12 включительно
Например, 0 - "ноль", 1 - "один", 2 - "два"
2. Считать с консоли имя файла
3. Заменить все числа на слова используя словарь map
4. Результат вывести на экран
5. Закрыть потоки

Пример данных:
Это стоит 1 бакс, а вот это - 12 .
Переменная имеет имя file1.
110 - это число.

Пример вывода:
Это стоит один бакс, а вот это - двенадцать .
Переменная имеет имя file1.
110 - это число.
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static Map<Integer, String> dict = new HashMap<Integer, String>();
    static{
        dict.put(0, "ноль");
        dict.put(1, "один");
        dict.put(2, "два");
        dict.put(3, "три");
        dict.put(4, "четыре");
        dict.put(5, "пять");
        dict.put(6, "шесть");
        dict.put(7, "семь");
        dict.put(8, "восемь");
        dict.put(9, "девять");
        dict.put(10, "десять");
        dict.put(11, "одиннадцать");
        dict.put(12, "двенадцать");
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fi = new BufferedReader(new FileReader(in.readLine()));
        String line;
        Pattern pattern = Pattern.compile("(\\s*\\d+\\s*)");
        Pattern endPat = Pattern.compile("(\\s+$)");
        while((line = fi.readLine()) != null){
            Matcher endMatcher = endPat.matcher(line);
            String[] words = line.split(" ");
            String newStr ="";
            for (int i = 0 ; i < words.length; i++){
                String s = words[i];
                Matcher matcher = pattern.matcher(s);
                if (matcher.matches()){
                    String num = matcher.group();
                    String strNum;
                    if ((strNum = dict.get(Integer.valueOf(num.trim()))) != null){
                        s = s.replaceAll(num.trim(), strNum);
                    }
                }
                newStr = newStr+s;
                if (i<words.length-1) {newStr += " ";}
                else if (endMatcher.find()){
                    newStr+= endMatcher.group();
                }
            }
            System.out.println(newStr);
        }
        in.close();
        fi.close();
    }
}
