package com.javarush.test.level19.lesson10.home04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Ищем нужные строки
Считать с консоли имя файла.
Вывести в консоль все строки из файла, которые содержат всего 2 слова из списка words
Закрыть потоки

Пример: words содержит слова А, Б, В
Строки:
В Б А Д  //3 слова из words, не подходит
Д А Д    //1 слово из words, не подходит
Д А Б Д  //2 слова - подходит, выводим
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws Exception {
        List<Pattern> pattList = new ArrayList<Pattern>();
        for(String s : words){
            pattList.add(Pattern.compile("(^"+s+"\\s)|(\\s"+s+"\\s)|(\\s"+s+"$)"));
        };

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fi = new BufferedReader(new FileReader(in.readLine()));
        String line;
        while ((line = fi.readLine()) != null) {
            String str = line.replaceAll(" ","  ");
            int count = 0;
            for (Pattern pat : pattList){
                Matcher matcher = pat.matcher(str);
                while (matcher.find()){
                    count++;
                }
                if (count > 2) break;
            }
            if (count == 2) {
                System.out.println(line);
            }
        }
        in.close();
        fi.close();
    }
}
