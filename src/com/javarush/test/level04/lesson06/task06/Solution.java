package com.javarush.test.level04.lesson06.task06;

/* И 18-ти достаточно
Ввести с клавиатуры имя и возраст. Если возраст больше 20 вывести надпись «И 18-ти достаточно».
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader (new InputStreamReader (System.in));
        String name = reader.readLine();
        int age;
        while (true) {
            try{
                age = new Integer(reader.readLine());
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("должно быть целое число");
            }
        }
        if (age > 20){
            System.out.println("И 18-ти достаточно");
        }

    }
}
