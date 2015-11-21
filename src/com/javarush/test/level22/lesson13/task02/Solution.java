package com.javarush.test.level22.lesson13.task02;

import java.io.*;
import java.io.IOException;

/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        args = new String[2];
        args[0] = "d:\\q.txt";
        args[1] = "d:\\qq.txt";
        try(
                BufferedReader fin = new BufferedReader(new FileReader(args[0]));
                PrintWriter fout = new PrintWriter(args[1]);
                )
        {
            String line;
            while((line = fin.readLine()) != null) {
                line = new String(line.getBytes("Windows-1251"),"UTF-8");
                fout.println(line);
            }
        }
    }
}
