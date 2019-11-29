package com.werwe.test;

import javax.inject.Inject;

public class LoginCommand extends SingleArgCommand {

    private final Outputter outputter;
    private final Database database;

    private final UserCommandsRouter.Factory userCommandsRouterFactory;

    @Inject
    LoginCommand(Database database, Outputter outputter, UserCommandsRouter.Factory userCommandsRouterFactory) {
        this.database = database;
        this.outputter = outputter;
        this.userCommandsRouterFactory = userCommandsRouterFactory;
    }

    @Override
    protected Result handleArg(String username) {
        Database.Account account = database.getAccount(username);
        outputter.output(username + " is logged in with balance: " + account.balance());
        return Result.enterNestedCommandSet(userCommandsRouterFactory.create(account).router());
    }

}
