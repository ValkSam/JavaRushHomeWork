package com.javarush.test.level04.lesson06.task04;

/* Сравнить имена
Ввести с клавиатуры два имени, и если имена одинаковые вывести сообщение «Имена идентичны». Если имена разные, но их длины равны – вывести сообщение – «Длины имен равны».
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Solution
{
        public String name;
        public int age;
        public int weight;
        public int strength;

    public boolean fight (Solution anotherCat)
    {
        int rang = this.weight*2+this.strength*4-this.age;
        int rangAnother = anotherCat.weight*2+anotherCat.strength*4-anotherCat.age;
        if (rang == rangAnother){
            return new Random().nextBoolean();
        }
        else {
            return (rang>rangAnother);
        }

    }
    public static void main(String[] args) throws Exception
    {

    }
}
