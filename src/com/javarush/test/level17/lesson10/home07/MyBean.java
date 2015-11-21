package com.javarush.test.level17.lesson10.home07;

/**
 * Created by Valk on 25.01.15.
 */
public class MyBean implements Bean {
    private String name;
    public MyBean(String name){this.name = name;}
    public String getName(){return name;}
}
