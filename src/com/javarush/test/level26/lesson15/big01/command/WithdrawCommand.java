package com.javarush.test.level26.lesson15.big01.command;


import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * Created by Valk on 26.04.15.
 */
class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        while (true) {
            Integer value;
            while (true) {
                value = getValidWithdrawValue();
                if (cm.isAmountAvailable(value)) break;
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            }
            Integer sum = 0;
            try {
                TreeMap<Integer, Integer> givenValue = new TreeMap<Integer, Integer>(cm.withdrawAmount(value));
                sum = 0;
                for (Map.Entry<Integer, Integer> pair : givenValue.descendingMap().entrySet()) {
                    ConsoleHelper.writeMessage(String.format("\t%s - %s", pair.getKey(), pair.getValue()));
                    sum += pair.getKey() * pair.getValue();
                }
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, currencyCode));
                break;
            } catch (NotEnoughMoneyException nem) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            } catch (ConcurrentModificationException cme) {
            }
        }
    }

    public int getValidWithdrawValue() throws InterruptOperationException {
        int withdrawValue;
        ConsoleHelper.writeMessage(res.getString("specify.amount"));
        while (true) {
            String str = ConsoleHelper.readString();
            try {
                withdrawValue = Integer.valueOf(str);
                if (withdrawValue > 0) break;
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            } catch (Exception e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
        }
        return withdrawValue;
    }

}
