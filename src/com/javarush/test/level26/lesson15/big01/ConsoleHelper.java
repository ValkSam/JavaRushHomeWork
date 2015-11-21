package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by Valk on 25.04.15.
 */
public class ConsoleHelper {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }

    public static String readString() throws InterruptOperationException {
        String enterStr = null;
        try {
            enterStr = reader.readLine();
            if ("EXIT".equals(enterStr.toUpperCase())) {
                throw new InterruptOperationException();
            }
            return enterStr;
        } catch (IOException e) {
        }
        return enterStr;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String currencyCode;
        writeMessage(res.getString("choose.currency.code"));
        while (true) {
            currencyCode = readString();
            if (currencyCode.matches("\\p{Alpha}{3}")) break;
            writeMessage(res.getString("invalid.data"));
        }
        return currencyCode.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String twoDigit;
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        while (true) {
            twoDigit = readString();
            if (twoDigit.matches("[1-9]\\d* [1-9]\\d*")) break;
            writeMessage(res.getString("invalid.data"));
        }
        return twoDigit.split(" ");
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        writeMessage(String.format("1 - %s, 2 - %s, 3 - %s, 4 - %s",res.getString("operation.INFO"),res.getString("operation.DEPOSIT"),res.getString("operation.WITHDRAW"),res.getString("operation.EXIT")));
        while (true) {
            try {
                int operationNumber = Integer.valueOf(readString());
                return Operation.getAllowableOperationByOrdinal(operationNumber);
            } catch (IllegalArgumentException iae) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }



}
