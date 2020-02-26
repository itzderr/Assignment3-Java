import ca.ciccc.BankAccount;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestBankAccountTransfer{

    @Parameterized.Parameters(name = "{index}: @ca.ciccc.BankAccount.transfer()")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {100, 0, 20, 20, 75, true},
                {10, 0, 20, 5, 0, true},
                {0, 0, 0, 0, 0, false},
                {50, 0, 50, 45, 0, true},
                {100, 0, 10, 10, 85, true},
                {3, 0, 3, 0, 3, false},
        });
    }
    @Parameterized.Parameter(0) public double balance1;
    @Parameterized.Parameter(1) public double balance2;
    @Parameterized.Parameter(2) public double transferAmount;
    @Parameterized.Parameter(3) public double expectedAmount;
    @Parameterized.Parameter(4) public double expectedRemaining;
    @Parameterized.Parameter(5) public boolean expectedReturn;
    private static final double DELTA = 0.001;
    @Test public void testTransferReturn() {
        BankAccount account1 = new BankAccount(balance1);
        BankAccount account2 = new BankAccount(balance2);
        Assert.assertEquals(expectedReturn, account1.transfer(transferAmount, account2));
    }
    @Test
    public void testTransferAccount1() {
        BankAccount account1 = new BankAccount(balance1);
        BankAccount account2 = new BankAccount(balance2);
        account1.transfer(transferAmount, account2);
        Assert.assertEquals(expectedRemaining, account1.getBalance(), DELTA);
    }
    @Test
    public void testTransferAccount2() {
        BankAccount account1 = new BankAccount(balance1);
        BankAccount account2 = new BankAccount(balance2);
        account1.transfer(transferAmount, account2);
        Assert.assertEquals(expectedAmount, account2.getBalance(), DELTA);
    }
}
