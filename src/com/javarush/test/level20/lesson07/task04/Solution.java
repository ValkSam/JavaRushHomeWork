package com.javarush.test.level20.lesson07.task04;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Externalizable Solution
Сериализуйте класс Solution.
Подумайте, какие поля не нужно сериализовать.
Объект всегда должен содержать актуальные на сегодняшний день данные.
*/
public class Solution implements Serializable{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        Solution s = new Solution(5);
        System.out.println(s);
        ByteArrayOutputStream mm = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(mm);
        oos.writeObject(s);
        oos.close();
        mm.close();
        ByteArrayInputStream bis = new ByteArrayInputStream(mm.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        Solution v = (Solution)ois.readObject();
        System.out.println(v);

    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
