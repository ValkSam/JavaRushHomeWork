package com.javarush.test.level18.lesson10.home08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Не забудьте закрыть все потоки
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws Exception{
        ArrayList<String> nameList = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String name = reader.readLine();
            if ("exit".equals(name)) break;
            nameList.add(name);
        }
        reader.close();
        for (String name : nameList) new ReadThread(name).start();
    }

    public static class ReadThread extends Thread {
        private String fileName;
        public ReadThread(String fileName) {
            this.fileName = fileName;
        }
        @Override
        public void run(){
            try {
                FileInputStream fin = new FileInputStream(this.fileName);
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                Integer curr;
                Integer max = 0;
                Integer bt = null;
                while ((curr = fin.read()) != -1){
                    map.put(curr, map.get(curr) == null? 1:map.get(curr)+1);
                    if (map.get(curr) > max) {
                        max = map.get(curr);
                        bt = curr;
                    }
                }
                fin.close();

                synchronized (resultMap) {
                    resultMap.put(fileName, bt);
                }
            }catch (Exception e){}

        }
    }
}
