package com.javarush.test.level26.lesson15.big01;

/**
 * Created by Valk on 25.04.15.
 */
public enum Operation {
    LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        if ((i < 1) || (i >= Operation.values().length)) throw new IllegalArgumentException();
        return Operation.values()[i];
    }

}
