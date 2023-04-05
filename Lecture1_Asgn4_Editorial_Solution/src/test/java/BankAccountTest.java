import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
    private BankAccount bankAccount;
//    @Test
//    public void testGetSimpleInterest() {
//        BankAccount account = new BankAccount();
//        account.balance = 1000;
//        account.roi = 5.0;
//        assertEquals(50.0, account.getSimpleInterest(1), 0.0);
//        assertEquals(250.0, account.getSimpleInterest(5), 0.0);
//        assertEquals(0.0, account.getSimpleInterest(0), 0.0);
//    }
//
//    @Test
//    public void testGetBalanceWithInterest() {
//        BankAccount account = new BankAccount();
//        account.balance = 1000;
//        account.roi = 5.0;
//        assertEquals(1050.0, account.getBalanceWithInterest(1), 0.0);
//        assertEquals(1250.0, account.getBalanceWithInterest(5), 0.0);
//        assertEquals(1000.0, account.getBalanceWithInterest(0), 0.0);
//    }
@BeforeEach
void setUp() {
    bankAccount = new BankAccount();

}
    @Test
    void accountNumberExists() {
        try {
            Field accountNumberField = BankAccount.class.getDeclaredField("accountNumber");
            assertEquals(accountNumberField.getType(), String.class);
        } catch (NoSuchFieldException e) {
            fail("accountNumber not found");
        }
    }

    @Test
    void balanceExists() {
        try {
            Field balanceField = BankAccount.class.getDeclaredField("balance");
            assertEquals(balanceField.getType(), int.class);
        } catch (NoSuchFieldException e) {
            fail("balance not found");
        }
    }

    @Test
    void roiExists() {
        try {
            Field roiField = BankAccount.class.getDeclaredField("roi");
            assertEquals(roiField.getType(), double.class);
        } catch (NoSuchFieldException e) {
            fail("roi not found");
        }
    }

    @Test
    void getSimpleInterestMethodExists(){
        try {
            Method getSimpleInterestMethod = bankAccount.getClass().getDeclaredMethod("getSimpleInterest", int.class);
        } catch (NoSuchMethodException e) {
            fail("get simple Interest method not found");
        }
    }

    @Test
    void getSimpleInterestMethodSignatureCheck() throws NoSuchMethodException {
        Method getSimpleInterestMethod = bankAccount.getClass().getDeclaredMethod("getSimpleInterest", int.class);
        assertEquals(getSimpleInterestMethod.toString(), "double BankAccount.getSimpleInterest(int)");
    }

    @Test
    void getBalanceWithInterestMethodExists(){
        try {
            Method getBalanceWithInterestMethod = bankAccount.getClass().getDeclaredMethod("getBalanceWithInterest", int.class);
        } catch (NoSuchMethodException e) {
            fail("get simple Interest method not found");
        }
    }

    @Test
    void getBalanceWithInterestMethodSignatureCheck() throws NoSuchMethodException {
        Method getBalanceWithInterestMethod = bankAccount.getClass().getDeclaredMethod("getBalanceWithInterest", int.class);
        assertEquals(getBalanceWithInterestMethod.toString(), "double BankAccount.getBalanceWithInterest(int)");
    }
    @Test
    void testGetSimpleInterest() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InvocationTargetException {
        BankAccount account = new BankAccount();

        // Set balance using reflection
        Field balanceField = BankAccount.class.getDeclaredField("balance");
        balanceField.setAccessible(true);
        balanceField.set(account, 1000);

        // Set roi using reflection
        Field roiField = BankAccount.class.getDeclaredField("roi");
        roiField.setAccessible(true);
        roiField.set(account, 5.0);

        // Invoke getSimpleInterest method using reflection
        Method getSimpleInterestMethod = BankAccount.class.getDeclaredMethod("getSimpleInterest", int.class);
        getSimpleInterestMethod.setAccessible(true);
        double interest1 = (double) getSimpleInterestMethod.invoke(account, 1);
        double interest5 = (double) getSimpleInterestMethod.invoke(account, 5);
        double interest0 = (double) getSimpleInterestMethod.invoke(account, 0);

        // Assert the results
        assertEquals(50.0, interest1, 0.0);
        assertEquals(250.0, interest5, 0.0);
        assertEquals(0.0, interest0, 0.0);
    }

    @Test
    void testGetBalanceWithInterest() throws NoSuchFieldException, IllegalAccessException {
        BankAccount account = new BankAccount();

        // set the balance and roi fields using reflection
        Field balanceField = account.getClass().getDeclaredField("balance");
        balanceField.setAccessible(true);
        balanceField.set(account, 1000);

        Field roiField = account.getClass().getDeclaredField("roi");
        roiField.setAccessible(true);
        roiField.set(account, 5.0);

        // call the getBalanceWithInterest() method using reflection
        try {
            Method method = account.getClass().getDeclaredMethod("getBalanceWithInterest", int.class);
            method.setAccessible(true);
            double result1 = (double) method.invoke(account, 1);
            double result2 = (double) method.invoke(account, 5);
            double result3 = (double) method.invoke(account, 0);

            // assert the results
            assertEquals(1050.0, result1, 0.0);
            assertEquals(1250.0, result2, 0.0);
            assertEquals(1000.0, result3, 0.0);

        } catch (NoSuchMethodException | InvocationTargetException e) {
            fail("getBalanceWithInterest method not found");
        }
    }


    @Test
    public void testGetSimpleInterestWithZeroBalance() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        BankAccount account = new BankAccount();

        Field balanceField = BankAccount.class.getDeclaredField("balance");
        balanceField.setAccessible(true);
        balanceField.set(account, 0);

        Field roiField = BankAccount.class.getDeclaredField("roi");
        roiField.setAccessible(true);
        roiField.set(account, 5.0);

        Method getSimpleInterestMethod = BankAccount.class.getDeclaredMethod("getSimpleInterest", int.class);
        getSimpleInterestMethod.setAccessible(true);
        double interest = (double) getSimpleInterestMethod.invoke(account, 1);

        assertEquals(0.0, interest, 0.0);
    }


    @Test
    public void testGetBalanceWithInterestWithZeroROI() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        BankAccount account = new BankAccount();
        Field balanceField = BankAccount.class.getDeclaredField("balance");
        balanceField.setAccessible(true);
        balanceField.set(account, 1000);

        Field roiField = BankAccount.class.getDeclaredField("roi");
        roiField.setAccessible(true);
        roiField.set(account, 0.0);

        Method getBalanceWithInterestMethod = BankAccount.class.getDeclaredMethod("getBalanceWithInterest", int.class);
        getBalanceWithInterestMethod.setAccessible(true);
        double balanceWithInterest = (double) getBalanceWithInterestMethod.invoke(account, 1);

        assertEquals(1000.0, balanceWithInterest, 0.0);
    }

    @Test
    public void testGetBalanceWithInterestWithNegativeTime() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        BankAccount account = new BankAccount();
        Field balanceField = BankAccount.class.getDeclaredField("balance");
        balanceField.setAccessible(true);
        balanceField.set(account, 1000);

        Field roiField = BankAccount.class.getDeclaredField("roi");
        roiField.setAccessible(true);
        roiField.set(account, 5.0);

        Method getBalanceWithInterestMethod = BankAccount.class.getDeclaredMethod("getBalanceWithInterest", int.class);
        getBalanceWithInterestMethod.setAccessible(true);
        double balanceWithInterest = (double) getBalanceWithInterestMethod.invoke(account, -1);

        assertEquals(950.0, balanceWithInterest, 0.0);
    }

}
