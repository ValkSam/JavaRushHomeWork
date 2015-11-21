package com.javarush.test.level22.lesson09.task03;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fIn = new BufferedReader(new FileReader(reader.readLine()));
        ArrayList<String> words = new ArrayList<>();
        String line;
        while ((line = fIn.readLine()) != null){
            words.addAll(Arrays.asList(line.split(" ")));
        }
        StringBuilder result = getLine(words.toArray(new String[words.size()]));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        Arrays.sort(words); //это существенно ускоряет расчет. Например, заметно для строки
        //s1s g1h hh df fg f1f as sd s2s f2f g2h h1g h2g s1s g1h hh dft fg tf1f gas sd s2s f2f g2h h1g h2g
        StringBuilder result = new StringBuilder();
        if (words.length == 1) return result.append(words[0]);
        for (int i = 0; i<words.length; i++){
            ArrayList<String> res = new ArrayList<String>();
            res.add(words[i]);
            res = getSegment(i, res, words.clone());

            if (res != null) {
                result.append(res.get(0));
                for (int j = 1; j<res.size(); j++){
                    result.append(" ").append(res.get(j));
                }
                return result;
            }
        }
        return result;
    }

    private static ArrayList<String> getSegment(int linkPos, ArrayList<String> currentSeg, String[] words){
        String currWord = words[linkPos].toUpperCase();
        if ("".equals(currWord)) return null;
        String lastLetter = currWord.substring(currWord.length()-1);
        words[linkPos] = "";
        for (int i = 0; i<words.length; i++){
            if (words[i] != "") {
                if (words[i].toUpperCase().startsWith(lastLetter)){
                    ArrayList<String> newSeg = new ArrayList<String>(currentSeg);
                    newSeg.add(words[i]); //words[i] - опорное слово
                    if (newSeg.size()==words.length) return newSeg; //Нашли последнее слово - цепочка сложилась
                    String[] savedWords = words.clone(); //внутри getSegment words будет изменяться. Сохраняем состояние перед входом
                    ArrayList<String> result = getSegment(i, newSeg, words);
                    if (result != null) return result; //если всплыли с результатом - возвращаем его наверх
                    words = savedWords.clone(); //если заныривание не дало результата, то это может из-за того,
                    // что ошибочно выбрано опорное слово (например, ситуация "фы ыв ыы").
                    // Восстанавливаем words в состояние до погружения и ищем другое. опорное слово
                }
            }
        }
        return null;
    }
}
