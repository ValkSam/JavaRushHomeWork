package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Valk on 26.04.15.
 */
class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"deposit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        String[] twoDigit = ConsoleHelper.getValidTwoDigits(currencyCode);
        try {
            Integer nominal = Integer.valueOf(twoDigit[0]);
            Integer count = Integer.valueOf(twoDigit[1]);
            if ((nominal<=0)||count<=0) throw new NumberFormatException();
            cm.addAmount(nominal, count);
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"),nominal*count,currencyCode));
        } catch (NumberFormatException e){
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
