package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Valk on 26.04.15.
 */
class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> cmList = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (cmList.size() == 0) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
            return;
        }
        int notEmptyManipulatorCount = 0;
        for (CurrencyManipulator cm : cmList) {
            if (cm.hasMoney()) {
                ConsoleHelper.writeMessage(String.format("%s - %s", cm.getCurrencyCode(), cm.getTotalAmount()));
                notEmptyManipulatorCount++;
            }
        }
        if (notEmptyManipulatorCount == 0) ConsoleHelper.writeMessage(res.getString("no.money"));

    }
}
