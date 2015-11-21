package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести все строки в файл, каждую строчку с новой стороки.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OutputStream fileOut = new FileOutputStream(reader.readLine());
        StringBuilder text = new StringBuilder();
        //String eol = "";
        while (true){
            String str = reader.readLine();
            text.append(str);
            text.append("\r\n");
            if ("exit".equals(str)) break;
        }
        fileOut.write (text.toString().getBytes());
        fileOut.close();
    }
}
