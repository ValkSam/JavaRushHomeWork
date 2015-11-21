package com.javarush.test.level15.lesson12.bonus01;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Valk on 12.01.15.
 */
public class Plane implements Flyable {
    private int passCount;
    public void fly(){}
    public Plane(int passCount){
        this.passCount = passCount;
    }

}
