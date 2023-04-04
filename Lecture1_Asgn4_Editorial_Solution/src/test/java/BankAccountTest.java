import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    @Test
    public void testGetSimpleInterest() {
        BankAccount account = new BankAccount();
        account.balance = 1000;
        account.roi = 5.0;
        assertEquals(50.0, account.getSimpleInterest(1), 0.0);
        assertEquals(250.0, account.getSimpleInterest(5), 0.0);
        assertEquals(0.0, account.getSimpleInterest(0), 0.0);
    }

    @Test
    public void testGetBalanceWithInterest() {
        BankAccount account = new BankAccount();
        account.balance = 1000;
        account.roi = 5.0;
        assertEquals(1050.0, account.getBalanceWithInterest(1), 0.0);
        assertEquals(1250.0, account.getBalanceWithInterest(5), 0.0);
        assertEquals(1000.0, account.getBalanceWithInterest(0), 0.0);
    }

    
}
