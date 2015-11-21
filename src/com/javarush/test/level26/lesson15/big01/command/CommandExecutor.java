package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.Operation;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.*;

/**
 * Created by Valk on 26.04.15.
 */
public class CommandExecutor {
    private CommandExecutor() {
    }

    private static Map<Operation, Command> commands = new HashMap<Operation, Command>(){{
        put(Operation.LOGIN, new LoginCommand());
        put(Operation.INFO, new InfoCommand());
        put(Operation.DEPOSIT, new DepositCommand());
        put(Operation.WITHDRAW, new WithdrawCommand());
        put(Operation.EXIT, new ExitCommand());
    }};

    public static final void execute(Operation operation)  throws InterruptOperationException {
        Command command = commands.get(operation);
        if (command != null) command.execute();
    }


}
