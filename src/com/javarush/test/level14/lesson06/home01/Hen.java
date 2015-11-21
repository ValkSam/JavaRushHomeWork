package com.javarush.test.level14.lesson06.home01;

abstract class Hen implements Country {
    abstract int getCountOfEggsPerMonth();

    public String getDescription(){
        return "Я курица.";
    }
}
