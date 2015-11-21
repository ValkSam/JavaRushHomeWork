package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Valk on 01.05.15.
 */
public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        while (true) {
            String[] logData = getValidLoginData();
            String cardNumber = logData[0];
            String cardPIN = logData[1];
            if ((validCreditCards.containsKey(cardNumber))&&(validCreditCards.getString(cardNumber).equals(cardPIN))) {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),cardNumber));
                break;
            }
            ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),cardNumber));
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
        }
    }

    public String[] getValidLoginData() throws InterruptOperationException {
        String cardNumber;
        String cardPIN;
        while (true) {
            cardNumber = ConsoleHelper.readString();
            cardPIN = ConsoleHelper.readString();
            if ((cardNumber.matches("\\d{12}")) && (cardPIN.matches("\\d{4}"))) {
                break;
            }
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
        }
        return new String[]{cardNumber, cardPIN};
    }
}
