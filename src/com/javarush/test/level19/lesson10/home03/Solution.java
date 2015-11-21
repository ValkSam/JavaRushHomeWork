package com.javarush.test.level19.lesson10.home03;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        Pattern pattern = Pattern.compile("(^.+)(\\s\\d+\\s\\d+\\s\\d+$)");

        String line;
        while ((line = in.readLine()) != null){
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            PEOPLE.add(new Person(matcher.group(1), new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH).parse(matcher.group(2))));
        }
        in.close();
    }

}
