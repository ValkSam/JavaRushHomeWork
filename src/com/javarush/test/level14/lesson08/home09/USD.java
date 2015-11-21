package com.javarush.test.level14.lesson08.home09;

/**
 * Created by Valk on 10.01.15.
 */
public class USD extends Money {
    public USD(double amount)
    {
        super(amount);
    }
    public String getCurrencyName(){
        return "USD";
    }
}
