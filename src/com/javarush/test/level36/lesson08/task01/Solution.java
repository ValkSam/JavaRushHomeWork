package com.javarush.test.level36.lesson08.task01;

import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
        файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
        Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
        Если файл1 содержит менее 5 различных букв, то вывести их все.
        Буквы различного регистра считаются одинаковыми.
        Регистр выводимых букв не влияет на результат.
        Закрыть потоки ввода-вывода.

        Пример 1 данных входного файла:
        zBk yaz b-kN
        Пример 1 вывода:
        abkny

        Пример 2 данных входного файла:
        caAC
        A, aB? bB
        Пример 2 вывода:
        abc

        Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //String fileName = args[0];
        String fileName = "d:/q.txt";
        TreeSet<Character> result = new TreeSet<Character>(){
            @Override
            public boolean add(Character character) {
                if (Character.isAlphabetic(character)) return super.add(Character.toLowerCase(character)) ;
                return false;
            }

            @Override
            public String toString() {
                StringBuilder s = new StringBuilder();
                Character[] chArr = new Character[this.size()];
                chArr = this.toArray(chArr);
                for (int i = 0; i < Math.min(5, chArr.length); i++) {
                    s.append(chArr[i]);
                }
                return s.toString();
            }
        };
        FileReader fr = new FileReader(fileName);
        while (fr.ready()) {
            char ch = (char)fr.read();
            result.add(ch);
        }
        fr.close();
        System.out.print(result);
    }
}
