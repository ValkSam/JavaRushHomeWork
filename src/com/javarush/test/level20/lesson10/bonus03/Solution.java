package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endX) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        ArrayList<Word> list = (ArrayList)detectAllWords(crossword, "home", "same", "rr");
        System.out.println((Arrays.toString(list.toArray())));
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        for (String currWord : words) {
            if ((! "".equals(currWord)) && (currWord != null)) {
                for (int i = 0; i < crossword.length; i++) {
                    for (int j = 0; j < crossword[0].length; j++) {
                        if (crossword[i][j] == currWord.charAt(0)) {
                            detectWord(list, crossword, currWord, 1, i, j, 0, 0);
                        }
                    }
                }
            }
        }
        return list;
    }

    private static void detectWord (List<Word> list, int[][] crossword, String word, int letterPos, int ci, int cj, int di, int dj){
        if (letterPos == word.length()) return;
        char letter = word.charAt(letterPos);
        if ((di == 0) && (dj == 0)){
            for (int i = -1; i <= 1; i++){
                for (int j = -1; j <= 1; j++) {
                    if ((i != 0)||(j != 0)) {
                        if (inBound(crossword, ci+i, cj+j)) {
                            if (crossword[ci+i][cj+j] == letter) {
                                if (letterPos == word.length() - 1) {
                                    Word w = new Word(word);
                                    w.setStartPoint(cj, ci);
                                    w.setEndPoint(cj+j, ci+i);
                                    list.add(w);
                                } else {
                                    detectWord(list, crossword, word, letterPos + 1, ci+i, cj+j, i, j);
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            if (inBound(crossword, ci+di, cj+dj)){
                if (crossword[ci+di][cj+dj] == letter){
                    if (letterPos == word.length()-1){
                        Word w = new Word(word);
                        w.setStartPoint(cj+dj-dj*letterPos, ci+di-di*letterPos);
                        w.setEndPoint(cj+dj, ci+di);
                        list.add(w);
                    }
                    else {
                        detectWord(list, crossword, word, letterPos+1, ci+di, cj+dj, di, dj);
                    }
                }
            }
        }
    }

    private static boolean inBound(int[][] arr, int i, int j){
        return ((i>=0) && (j>=0) && (i<arr.length) && (j<arr[0].length));
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
