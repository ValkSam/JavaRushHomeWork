package com.javarush.test.level17.lesson10.home08;

import java.math.BigDecimal;

public class BankAccount {
    private volatile BigDecimal balance;
    private String owner;

    public BankAccount(String owner) {
        this(BigDecimal.ZERO, owner);
    }

    public BankAccount(BigDecimal balance, String owner) {
        this.balance = balance;
        this.owner = owner;
    }

    public void deposit(BigDecimal money) {
        BigDecimal newBalance = balance.add(money);
        System.out.println("Добавляем " + money + ", на счету " + newBalance);
        balance = newBalance;
    }

    public synchronized void withdraw(BigDecimal money) throws NotEnoughMoneyException {
        BigDecimal newBalance = balance.subtract(money);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) throw new NotEnoughMoneyException();
try {Thread.sleep(500);} catch (InterruptedException e){} //эта пауза выявляет проблему, если для public void deposit(BigDecimal money)
//        не будет синхронизации. Добавление на счет вклинивается в промежуток пока обновляется баланс по данным до добавления
        balance = newBalance;
        System.out.println("Тратим " + money + ", на счету " + balance);
    }

    public void deposit(String money) {
        deposit(new BigDecimal(money));
    }

    public void withdraw(String money) throws NotEnoughMoneyException {
        withdraw(new BigDecimal(money));
    }
}
