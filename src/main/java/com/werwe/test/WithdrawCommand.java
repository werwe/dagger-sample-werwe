package com.werwe.test;

import javax.inject.Inject;
import java.math.BigDecimal;

public final class WithdrawCommand extends BigDecimalCommand {

    private final Database.Account account;
    private final Outputter outputter;

    @Inject
    public WithdrawCommand(Database.Account account, Outputter outputter) {
        super(outputter);
        this.account = account;
        this.outputter = outputter;
    }

    @Override
    protected void handleAmount(BigDecimal amount) {
        if (account.canWithdraw(amount)) {
            account.withdraw(amount);
        } else {
            outputter.output("yout balance is low : " + account.balance());
        }
        outputter.output("your new balance is: " + account.balance());
    }
}
