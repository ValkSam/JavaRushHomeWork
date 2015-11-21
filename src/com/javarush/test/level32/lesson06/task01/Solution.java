package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Каждый сгенерированный символ пароля пишите сразу в ByteArrayOutputStream.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        boolean isLower = false;
        boolean isUpper = false;
        boolean isDigit = false;
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) {
            int v = rnd.nextInt(3);
            if ((i==6)&&(!isDigit)) v = 0;
            if ((i==6)&&(!isLower)) v = 1;
            if ((i==6)&&(!isUpper)) v = 2;

            if ((i==7)&&(!isDigit)) v = 0;
            if ((i==7)&&(!isLower)) v = 1;
            if ((i==7)&&(!isUpper)) v = 2;

            byte b;
            switch (v){
                case 0: {
                    b = (byte)(rnd.nextInt(9)+48);
                    isDigit = true;
                    break;}
                case 1: {
                    b = (byte)(rnd.nextInt(25)+97);
                    isLower = true;
                    break;}
                default: {
                    b = (byte)(rnd.nextInt(25)+65);
                    isUpper = true;
                    break;}
            }
            baos.write(b);
        }
        return baos;
    }
}
