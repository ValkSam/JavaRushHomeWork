package com.javarush.test.level26.lesson15.big01;

import java.util.*;

/**
 * Created by Valk on 26.04.15.
 */
public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> currencyManipulatorMap = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode (String currencyCode)  {
        CurrencyManipulator result = currencyManipulatorMap.get(currencyCode);

        if (result != null) return result;

        result = new CurrencyManipulator(currencyCode);
        currencyManipulatorMap.put(currencyCode, result);
        return result;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return currencyManipulatorMap.values();
    }

    private CurrencyManipulatorFactory() {
    }
}
