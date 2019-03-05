import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * You can test all tests at once.
 * Make sure you pass all test cases before submitting your assignment.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestBankAccountToString.class,
        TestBankAccountTransactionFee.class,
        TestBankAccountTransfer.class
})
public class TestAll { }
