import ca.ciccc.BankAccount;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestBankAccountTransactionFee {
    @Parameterized.Parameters(name = "{index}: @ca.ciccc.BankAccount.transactionFee()")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {5.00, true},
                {7.00, true},
                {20.00, false},
        });
    }

    @Parameterized.Parameter    public double inputs;
    @Parameterized.Parameter(1) public boolean expected;
    @Test                       public void testTransactionFee() {BankAccount account = new BankAccount();
                                                                              account.deposit(10.00);
                                                                              account.deposit(50.00);
                                                                              account.deposit(10.00);
                                                                              account.deposit(70.00);
                        Assert.assertEquals(expected, account.transactionFee(inputs));
    }
}
