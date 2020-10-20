package ca.ciccc;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Assignment 3
 */
public class BankAccount {
    private String id;
    private String name;
    private double balance;
    private boolean allowNegativeBalance;
    private int transactionCount = 0;
    private String transactionText = "";

    public BankAccount() { }

    public BankAccount(double balance) {
        this.id = "test account";
        this.name = "test";
        this.balance = balance;
        this.allowNegativeBalance = true;
    }

    public BankAccount(String id, String name, double balance, boolean allowNegativeBalance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.allowNegativeBalance = allowNegativeBalance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isAllowNegativeBalance() {
        return allowNegativeBalance;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public String getTransactionText() {
        return transactionText;
    }

    public void deposit(double amount) {
        if (amount >= 0) {
            balance += amount;
            transactionText += "deposit of $" + amount + "\n";
        }
        transactionCount++;
    }

    public void withdraw(double amount) {
        if (allowNegativeBalance || balance >= amount) {
            balance -= amount;
            transactionText += "withdrawal of $" + amount + "\n";
        }
        transactionCount++;
    }

    /**
     * The transactionFee method accepts a fee amount ({@code double}) as a parameter, and applies that fee
     * to the user's past transactions. The fee is applied once for the first transaction, twice for
     * the second transaction, three times for the third, and so on. These fees are subtracted out
     * from the user's overall balance. If the user's balance is large enough to afford all of the
     * fees with greater than $0.00 remaining, the method will return {@code true}. If the balance cannot
     * afford all of the fees or has no money left, the balance is left as 0.0 and method returns
     * {@code false}.
     *
     * For example, given the following ca.ciccc.BankAccount object.
     *
     * ca.ciccc.BankAccount savings = new ca.ciccc.BankAccount("Jimmy");
     * savings.deposit(10.00);
     * savings.deposit(50.00);
     * savings.deposit(10.00);
     * savings.deposit(70.00);
     *
     * savings.transactionFee(5.00);
     *
     * The account would be deducted $5 + $10 + $15 + $20 for the four transactions, leaving a final
     * balance of $90.00. The method would return true.
     *
     * savings.transactionFee(10.00);
     *
     * Then the account would be deducted $10 + $20 + $30 + $40 for the four transactions, leaving
     * a final balance of $0.00. The method would return false.
     *
     * @param fee
     * @return true if there's enough balance, otherwise false
     */
    public boolean transactionFee(double fee) {
        // TODO 2: Your code goes here.
        for (int i = 1; i <= transactionCount; i++) {
            if (i >= 3) {
                fee = fee / (i - 1);
            }
            fee *= i;
            balance -= fee;
        }

        if (balance > 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * The transfer method moves money from `this` bank account to another account. The method
     * accepts two parameters: the amount of money to transfer and the other ca.ciccc.BankAccount to accept
     * the money.
     * There is a $5.00 fee for transferring money, so this much must be deducted from the current
     * account's balance before any transfer.
     *
     * If `this` account object does not have enough money to make full transfer, then transfer
     * whatever money is left after the $5.00 fee is deducted. If this account has under $5.00 or amount is 0 or less,
     * no transfer should occur and neither account's state should be modified.
     *
     * If any amount of money is transferred, return {@code true}. Otherwise {@code false}.
     *
     * @param amount
     * @param other
     * @return true if transferred any amount of money, otherwise false.
     * I used following URL as a reference when I was wondering how I should put deposit and withdraw function.
     * https://stackoverflow.com/questions/24564790/bank-account-transferto-method
     */
    public boolean transfer(double amount, BankAccount other) {
        // TODO 3: Your code goes here.
        if (balance <= 5.00 || amount <= 0) {
            return false;
        }
        if (balance - 5.00 >= amount) {
            withdraw(amount);
            other.deposit(amount);
            balance -= 5.00;
            return true;
        } else {
            /**
             * set leftover variable because an error occurred when I use "balance - 5.00"
             */
            double leftBalance = balance - 5.00;
            withdraw(leftBalance);
            other.deposit(leftBalance);
            balance -= 5.00;
            return true;
        }
    }

    /**
     * Your {@code toString()} method should return a string that contains the account's name
     * and balance separated by a comma and space. For example, if an account object has name
     * "Derrick" and a balance of 20.55, this method should return "Derrick, $20.55"
     * There are some special cases you should handle. If the balance is negative, put the -sign
     * before the dollar sign. Also, always display the cents as a two-digit number.
     * For example, if the same object had a balance of -17.5, your method should return
     * "Derrick, -$17.50"
     *
     * @return string representation of ca.ciccc.BankAccount
     */
    @Override
    public String toString() {
        // TODO 1: Your code goes here.
        /**
         * set following variable to convert negative balance to positive one
         */
        double negToPos = balance * -1;
        BigDecimal negToPosSrc = BigDecimal.valueOf(negToPos);
        BigDecimal negToPosVal = negToPosSrc.setScale(2, RoundingMode.DOWN);
        /**
         * set following variable for only positive balance
         */
        BigDecimal val = BigDecimal.valueOf(balance);
        BigDecimal floorVal = val.setScale(2, RoundingMode.DOWN);

        if (balance < 0) {
            return name + ", -$" + negToPosVal;
        } else {
            return name + ", $" + floorVal;
        }
    }
}
