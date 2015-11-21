package com.javarush.test.level14.lesson08.home09;

/**
 * Created by Valk on 10.01.15.
 */
public class Hrivna extends Money {
    public Hrivna(double amount)
    {
        super(amount);
    }
    public String getCurrencyName(){
        return "HRN";
    }
}
