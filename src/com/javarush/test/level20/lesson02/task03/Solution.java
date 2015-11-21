package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.*;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            load(new FileInputStream(reader.readLine()));

        }
        catch (Exception e){}
    }

    public void save(OutputStream outputStream) throws Exception {
        PrintWriter out = new PrintWriter(outputStream);
        for (Map.Entry<String, String> pair : properties.entrySet()){
            out.println(encodeToASCII(pair.getKey()).replaceAll(" ", "\\\\ ") + " = " + encodeToASCII(pair.getValue()));
        }
        out.flush();
    }

    public void load(InputStream inputStream) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int valueStart;
        String key = "";
        String value = "";
        boolean nextStrToKey = false;
        boolean nextStrToValue = false;

        Solution.properties.clear();

        while ((line = in.readLine()) != null){
            line = line.replaceAll("^\\s+","");
            line = line.replaceAll("\\\\ ",(char)127+"");
            line = line.replaceAll("\\s*=\\s*","=");
            line = line.replaceAll("\\s*:\\s*",":");
            if (nextStrToValue) { //в текущей строке продолжение значения
                value = value + decodeToNative(line);
                if (value.endsWith("\\")) { //определение значения еще не закончилось
                    value = value.substring(0, value.length() - 1); //отрезать символ продолжения
                } else {                  //значение определено
                    nextStrToValue = false;
                    Solution.properties.put(key.replaceAll("^\\s+", "").replaceAll((char)127+"", " "), value.replaceAll("^\\s+","").replaceAll((char)127+"", " "));
                    key = "";
                    value = "";
                }
            }
            else { //самостоятельная строка (не продолжение предыдущей)
                if ((! nextStrToKey) && (line.startsWith("#") || line.startsWith("!"))) { //комментарий
                }
                else {
                    if (("".equals(key))||(nextStrToKey)) { //ключ еще не определен
                        //выделение ключа
                        int i1 = line.indexOf("=");
                        i1 = (i1 == -1 ? Integer.MAX_VALUE : i1);
                        int i2 = line.indexOf(":");
                        i2 = (i2 == -1 ? Integer.MAX_VALUE : i2);
                        valueStart = Math.min(i1, i2);
                        String genKey = key;
                        key = line;
                        value = "";
                        if (valueStart != Integer.MAX_VALUE) {
                            key = line.substring(0, valueStart); //получен ключ по границе = или :
                        }

                        for (int i = 1; i < key.trim().length(); i++) { //проверка наличия в ключе незащищенных пробелов
                            if (key.charAt(i) == ' ') { //есть незащищенный пробел
                                valueStart = i; //новая граница ключа
                                break;
                            }
                        }

                        if (valueStart != Integer.MAX_VALUE) {
                            key = line.substring(0, valueStart); //уточняем значение ключа
                        }

                        key = decodeToNative(key);
                        if (key.endsWith("\\")) {
                            nextStrToKey = true; //определение ключа еще не закончено
                            key = key.substring(0, key.length() - 1); //отрезать символ продолжения
                            key = genKey + key;
                        } else {
                            nextStrToKey = false; //определение ключа закончено
                            if (valueStart != Integer.MAX_VALUE) {
                                value = line.substring(valueStart + 1); //ключ определен окончательно. Выделяем значение
                                value = decodeToNative(value);
                            }
                            key = genKey + key;
                        }
                    }
                    else { //ключ уже определен
                        value = line; //ключ определен окончательно. Выделяем значение
                        value = decodeToNative(value);
                    }
                    if ((! "".equals(key)) && (! nextStrToKey)) {
                        //если ключ определен окончательно - продолжение выделения значения
                        if (value.endsWith("\\")) {
                            nextStrToValue = true; //значение имеет продолжение на след строке
                            value = value.substring(0, value.length() - 1); //отрезать символ продолжения
                        } else {
                            nextStrToValue = false; //значение определено полностью
                            Solution.properties.put(key.replaceAll("^\\s+", "").replaceAll((char)127+"", " "), value.replaceAll("^\\s+","").replaceAll((char)127+"", " "));
                            key = "";
                            value = "";
                        }
                    }
                }
            }
        }
    }

    private String decodeToNative(String str){
        String[] strU = str.split("\\\\u");
        StringBuilder result = new StringBuilder();
        result.append(strU[0]);
        for (int i = 1; i<strU.length; i++){
            result.append((char) Integer.parseInt(strU[i].substring(0,4),16) + strU[i].substring(4));
        }
        return result.toString();
    }

    private String encodeToASCII(String str){
        char[] strA = str.toCharArray();
        StringBuilder result = new StringBuilder();
        for (char ch : strA) {
            if ((int)ch <= 255){
                result.append(ch);
            }
            else {
                result.append("\\u"+String.format("%04X", (int)ch));
            }
        }
        return result.toString();
    }
}
