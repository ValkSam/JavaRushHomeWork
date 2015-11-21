package myTests;

//с рекурсией
// package com.javarush.test.level19.lesson10.bonus03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Valk on 14.02.15.
 */
public class notRecurPars {

    public static void main (String[] args) throws java.lang.Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader fi = new BufferedReader(new FileReader(reader.readLine()));
        BufferedReader fi = new BufferedReader(new FileReader("d:\\q.txt"));
        StringBuilder lines = new StringBuilder();
        while (fi.ready()) {
            String s = fi.readLine();
            lines.append(s).append("\r\n");
        }
        fi.close();
        reader.close();

        String str = lines.toString();
        String tag = "span";

        //
        Matcher paramTagMatch = Pattern.compile("<" + tag + "\\r\\n([\\S&&[^>]])").matcher(str);
        while (paramTagMatch.find()) {
            str = paramTagMatch.replaceFirst("<" + tag + " "+paramTagMatch.group(1));
            paramTagMatch = Pattern.compile("<" + tag + "\\r\\n([\\S&&[^>]])").matcher(str);
        }
        str = str.replaceAll("\r\n", "");
        //

        Matcher openTagMatch  = Pattern.compile("(<\\s*/?\\s*"+tag+"(\\s*>|\\s+))").matcher(str);
        Matcher closeTagMatch = Pattern.compile("(<\\s*/\\s*"+tag+"\\s*>)").matcher(str);

        TreeSet<Integer[]> list = new TreeSet<Integer[]>();
        ArrayList<Integer[]> markList = new ArrayList<Integer[]>();
        int level = 0;
        while (openTagMatch.find()) {
            int pos = openTagMatch.start();
            if (openTagMatch.group().replaceAll(" ","").charAt(1) != '/'){
                level++;
                markList.add(new Integer[] {pos, level, 0});
            }
            else {
                markList.add(new Integer[] {pos, level, 1});
                level--;
            }
        }

        for (int i = 0 ; i < markList.size(); i++){
            System.out.printf("%4d", markList.get(i)[0]);
        }
        System.out.println();
        for (int i = 0 ; i < markList.size(); i++){
            System.out.printf("%4d", markList.get(i)[1]);
        }
        System.out.println();
        for (int i = 0 ; i < markList.size(); i++){
            System.out.printf("%4d", markList.get(i)[2]);
        }
        System.out.println();

        for (int i = 0 ; i < markList.size(); i++){
            if (markList.get(i)[2] == 0) {
                for (int j = i+1 ; j < markList.size(); j++){
                    if ((markList.get(j)[1] == markList.get(i)[1]) & (markList.get(j)[2] == 1)){
                        closeTagMatch.reset();
                        closeTagMatch.region(markList.get(j)[0], str.length());
                        closeTagMatch.find();
                        String subStr = str.substring(markList.get(i)[0], markList.get(j)[0]) + closeTagMatch.group();
                        System.out.println(subStr);
                        break;
                    }
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