package com.javarush.test.level30.lesson02.home01;

import java.math.BigDecimal;
import java.math.BigInteger;

/* Конвертер систем счислений
Реализуйте логику метода convertNumberToOtherNumerationSystem, который должен переводить число number.getDigit()
из одной системы счисления(numerationSystem) в другую (expectedNumerationSystem)
бросьте NumberFormatException, если переданное число некорректно, например, число "120" с системой счисления 2
Валидация для - number.getDigit() - целое не отрицательное
Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._10, "255");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 110
    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {
        if (number.getDigit().startsWith("-")) throw new NumberFormatException();
        int origRadix = number.getNumerationSystem().getNumerationSystemIntValue();
        BigInteger decimalValue = new BigInteger(number.getDigit(),origRadix);
        int newRadix = expectedNumerationSystem.getNumerationSystemIntValue();
        return new Number(expectedNumerationSystem, new BigInteger(decimalValue.toString()).toString(newRadix));
    }

}
