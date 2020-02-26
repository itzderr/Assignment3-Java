import ca.ciccc.BankAccount;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestBankAccountToString {

    @Parameterized.Parameters(name = "{index}: @ca.ciccc.BankAccount.toString()")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new BankAccount("1", "Jack", 10.00, true), "Jack, $10.00"},
                {new BankAccount("1", "Derek", 17.50, true), "Derek, $17.50"},
                {new BankAccount("1", "Rose", -5.50, true), "Rose, -$5.50"},
                {new BankAccount("1", "Nate", -7.75, true), "Nate, -$7.75"},
                {new BankAccount("1", "Ethan", 7.25, true), "Ethan, $7.25"},
        });
    }
    @Parameterized.Parameter    public BankAccount inputs;
    @Parameterized.Parameter(1) public String expected;
    @Test                       public void testToString() {
        Assert.assertEquals(expected, inputs.toString());
    }
}
