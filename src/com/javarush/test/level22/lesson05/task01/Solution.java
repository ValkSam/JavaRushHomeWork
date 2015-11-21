package com.javarush.test.level22.lesson05.task01;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
*/
public class Solution {
    public static String getPartOfString(String string) {
        if (string == null) throw new TooShortStringException();
        String[] ss = string.split(" ");
        if (ss.length < 5) throw new TooShortStringException();
        return ss[1]+" "+ss[2]+" "+ss[3]+" "+ss[4];
    }

    public static class TooShortStringException extends RuntimeException{
    }

    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения"));
        System.out.println(getPartOfString("JavaRush - лучший сервис "));
        System.out.println(getPartOfString("JavaRush - лучший сервис"));
    }
}
