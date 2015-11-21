package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть все потоки ввода-вывода
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/

import java.io.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<String> fList = new TreeSet<String>(new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                Integer v1 = Integer.valueOf(s1.substring(s1.lastIndexOf(".part")+5));
                Integer v2 = Integer.valueOf(s2.substring(s2.lastIndexOf(".part")+5));
                if (v1 > v2) return 1;
                if (v1 < v2) return -1;
                return 0;
            }
        });
        Pattern pat = Pattern.compile("(.part\\d+$)");
        Matcher matcher;
        while (true) {
            String fn = reader.readLine();
            if ("end".equals(fn)) break;
            if (! (matcher = pat.matcher(fn)).find()) return;
            String sPart = matcher.group(1);
            fList.add(fn);
        }
        reader.close();
        if (fList.size() == 0) return;
        pat = Pattern.compile("(\\d+$)");
        String resultFileName = "";
        Iterator<String> iterator = fList.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            String fName = iterator.next();
            matcher = pat.matcher(fName);
            matcher.find();
            Integer fNum = Integer.valueOf(matcher.group(1));
            if (fNum != (i+1)) return;
            if (! new File(fName).exists()) return;
            if (i == 0) {
                resultFileName = fName;
                resultFileName = resultFileName.substring(0, resultFileName.lastIndexOf(".part"));
            }
            else {
                fName = fName.substring(0, fName.lastIndexOf(".part"));
                if (! resultFileName.equals(fName)) return;
            }
        }
        FileOutputStream fo = new FileOutputStream(resultFileName);
        iterator = fList.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            try {
                FileInputStream fr = new FileInputStream(iterator.next());
                byte[] buf = new byte [fr.available()];
                fr.read(buf);
                fo.write(buf);
                fr.close();
            } catch (Exception e) {
                return;
            }
        }
        fo.close();
    }
}
