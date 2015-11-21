package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main (String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader fi = new BufferedReader(new FileReader(reader.readLine()));
            StringBuilder lines = new StringBuilder();
            while (fi.ready()) {
                String s = fi.readLine();
                lines.append(s).append("\r\n");
            }
            fi.close();
            reader.close();

            String str = lines.toString();

            String tag = args[0];
            scan(tag, str);
            
        } catch (Exception e){}
    }
    private static void scan (String tag, String str){
        Matcher paramTagMatch = Pattern.compile("<" + tag + "\\r\\n([\\S&&[^>]])").matcher(str);
        while (paramTagMatch.find()) {
            str = paramTagMatch.replaceFirst("<" + tag + " "+paramTagMatch.group(1));
            paramTagMatch = Pattern.compile("<" + tag + "\\r\\n([\\S&&[^>]])").matcher(str);
        }
        str = str.replaceAll("\r\n", "");

        Matcher tagMatch  = Pattern.compile("(</?"+tag+"(\\s*>|\\s+))").matcher(str);
        int startPos = -1;
        String result = "";
        int nestedPos = -1;
        int level = 0;

        while (tagMatch.find()) {
            int pos = tagMatch.start();

            if (tagMatch.group().charAt(1) != '/') {
                if (startPos == -1) startPos = pos;
                level++;
                if ((level == 2) & (nestedPos == -1)) {
                    nestedPos = pos;
                }
            } else {
                level--;
            }
            if (level == 0) {
                result = str.substring(startPos, pos) + str.substring(pos, str.indexOf('>',pos)+1);
                System.out.println(result);

                if (tagMatch.find()) {
                    startPos = tagMatch.start();
                    level++;
                }

                if (nestedPos != -1) {
                    String nestedStr = str.substring(nestedPos, pos);
                    nestedPos = -1;
                    scan(tag, nestedStr);
                }
            }
        }

    }
}

/*
Попробуйте вот это:


<tag>
text hash text</tag><span>first<br> text1</br>
</span>hh<span>second

Результат должен быть такой:


<span>first<br> text1</br></span>
<span>second</span>

        или вот ещё:


<span>first tag
</span>Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela <span inner>
midlle inner tag</span>
</span></b></span>
<span>finish tag</span>

*/
