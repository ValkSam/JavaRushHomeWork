package com.javarush.test.level21.lesson16.big01;

import java.util.Arrays;

/**
 * Created by Valk on 15.03.15.
 */
public class Horse {
    private String name;
    private double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void move(){
        distance += speed*Math.random();
    }
    public void print(){
        char[] ch = new char[(int)Math.round(distance)];
        Arrays.fill(ch,'.');
        String str = String.valueOf(ch);
        System.out.println(str+name);
    }
}
