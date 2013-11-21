package example1;

import java.math.BigDecimal;

public class BankAccount {
    private BigDecimal amount;

    public BankAccount(BigDecimal initial) {
        this.amount = initial;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isEmpty() {
        return amount.compareTo(BigDecimal.ZERO) <= 0;
    }

    public boolean withdraw(BigDecimal value) {
        if (!isEmpty()) {
            amount = amount.subtract(value);
            return true;
        }
        else
            return false;
    }

    public void deposit(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }
}
