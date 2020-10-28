
package test.btp400.a1;
import com.seneca.business.*;
import com.seneca.accounts.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /*******************************
     * @author: Shervin Tafreshipour
     * Student ID: 155199169
     * Email: stafreshipour@myseneca.ca
     *******************************/

    // JUnit Testing for Account Class

    @Test
    public void testAccountConstructor() {
        Account testAccount = new Account("Shervin Tafreshi", "EA3DFCEQV", 400.00);
        assertEquals("Shervin Tafreshi", testAccount.getFullName());
        assertEquals("EA3DFCEQV", testAccount.getAccountNumber());
        assertEquals(400.00, testAccount.getBalance(), 0.00);
    }

    @Test
    public void testInvalidAccountConstructor() {
        Account testAccount = new Account(null,null,-1);
        assertEquals("", testAccount.getFullName());
        assertEquals("", testAccount.getAccountNumber());
        assertEquals(0.00, testAccount.getBalance(), 0.00);
    }

    /*******************************
     * @author: Shervin Tafreshipour
     * Student ID: 155199169
     * Email: stafreshipour@myseneca.ca
     *******************************/

    // JUnit Testing for Chequing Class

    @Test
    public void testChequingConstructor() {
        Chequing testAccount = new Chequing("Shervin Tafreshi", "EA3DFCEQV",400.00, 2.00, 15);
        assertEquals(2.00, testAccount.getServiceCharge(), 0.00);
        assertEquals(15, testAccount.getMaxTransactions());
    }

    @Test
    public void testInvalidChequingConstructor() {
        Chequing testAccount = new Chequing("Shervin Tafreshi", "EA3DFCEQV",400.00, -5.45, -10);
        assertEquals(0.25, testAccount.getServiceCharge(), 0.00);
        assertEquals(3, testAccount.getMaxTransactions());
    }

    @Test
    public void testMaxTransactions() {
        Chequing testAccount = new Chequing("Shervin Tafreshi", "EA3DFCEQV",400.00, 0.20, 1);
        testAccount.withdraw(100);
        assertEquals(false,testAccount.withdraw(100));
    }

    @Test
    public void testNegativeBalance() {
        Chequing testAccount = new Chequing("Shervin Tafreshi", "EA3DFCEQV",200.00, 0.15, 10);
        assertEquals(false, testAccount.withdraw(250.00));
    }

    @Test
    public void testChequingWithdrawMethod() {
        Chequing testAccount = new Chequing("Shervin Tafreshi", "EA3DFCEQV",200.00, 0.10, 8);
        testAccount.withdraw(150);
        assertEquals(49.90, testAccount.getBalance(), 0.00);
    }

    @Test
    public void testChequingDepositMethod() {
        Chequing testAccount = new Chequing("Shervin Tafreshi", "EA3DFCEQV",450.00, 0.30, 5);
        testAccount.deposit(150);
        assertEquals(599.70, testAccount.getBalance(), 0.00);
    }

    @Test
    public void testTotalServiceChargeAmount() {
        Chequing testAccount = new Chequing("Shervin Tafreshi", "EA3DFCEQV",200.00, 0.35, 6);
        testAccount.withdraw(100.50);
        testAccount.deposit(50.45);
        assertEquals(0.70, testAccount.getServiceCharge() * testAccount.getNumberOfTransactions(), 0.00);

    }

    /*******************************
     * @author: Parsa Jalilifar
     * Student ID: 133308189
     * Email: pjalilifar@myseneca.ca
     *******************************/

    // JUnit Testing for GIC Class

    @Test
    public void testGICConstructor() {
        GIC testAccount = new GIC("Shervin Tafreshi", "EA3DFCEQV", 150.00, 5,1.50);
        assertEquals(5,testAccount.getInvestmentPeriod());
        assertEquals(1.50,testAccount.getAnnualInterestRate(), 0.00);
    }

    @Test
    public void testInvalidGICConstructor() {
        GIC testAccount = new GIC("Shervin Tafreshi", "EA3DFCEQV", 150.00, -4,-2.80);
        assertEquals(1,testAccount.getInvestmentPeriod());
        assertEquals(1.25, testAccount.getAnnualInterestRate(), 0.00);
    }

    @Test
    public void testGICWithdrawMethod() {
        GIC testAccount = new GIC("Shervin Tafreshi", "EA3DFCEQV", 350.00, 7, 1.20);
        assertEquals(false,testAccount.withdraw(100.00));
    }

    @Test
    public void testGICDepositMethod() {
        GIC testAccount = new GIC("Shervin Tafreshi","EA3DFCEQV", 1000.50, 3, 1.75);
        assertEquals(1000.50, testAccount.getStartingBalance(), 0.00);
    }

    @Test
    public void testFinalBalance() {
        GIC testAccount = new GIC("Shervin Tafreshi","EA3DFCEQV", 1500.00, 3, 2.00);
        assertEquals(1591.81, testAccount.getBalance(), 0.00);
    }

    @Test
    public void testFInalBalance2() {
        GIC testAccount = new GIC("Shervin Tafreshi","EA3DFCEQV", 2100.00, 3, 2.50);
        assertEquals(2261.47, testAccount.getBalance(), 0.00);
    }

    @Test
    public void testInterestIncome() {
        GIC testAccount = new GIC("Shervin Tafreshi","EA3DFCEQV",3450.00, 4, 1.75);
        testAccount.calculateTax();
        assertEquals(247.91, testAccount.getInterestIncome(), 0.00);
    }

    @Test
    public void testInterestIncome2() {
        GIC testAccount = new GIC("Shervin Tafreshi","EA3DFCEQV",1200.25, 4, 1.45);
        testAccount.calculateTax();
        assertEquals(71.14, testAccount.getInterestIncome(), 0.00);
    }

    /*******************************
     * @author: Parsa Jalilifar
     * Student ID: 133308189
     * Email: pjalilifar@myseneca.ca
     *******************************/

    //JUnit Testing for Bank Class

    @Test
    public void testBankConstructor() {
        Bank testBank = new Bank("My Bank");
        Bank testBank2 = new Bank();
        assertEquals("My Bank",testBank.getBankName());
        assertEquals("Seneca@York", testBank2.getBankName());
    }

    @Test
    public void testInvalidBankConstructor() {
        Bank testBank = new Bank(null);
        assertEquals("Seneca@York", testBank.getBankName());
    }

    @Test
    public void testSearchByBalance() {
        Bank testBank = new Bank("My Bank");
        Account[] accounts = new Account[1];
        GIC testAccount = new GIC("Shervin Tafreshi", "R3EVCQ8Z9", 180.00,7,0.75);

        accounts[0] = testAccount;
        testBank.addAccount(testAccount);
        testBank.addAccount(new Chequing("Java Developer", "VE74A241C", 350.00, 0.30, 5));
        assertArrayEquals(accounts,testBank.searchByBalance(189.67));
    }

    @Test
    public void testSearchByAccountName() {
        Bank testBank = new Bank("My Bank");
        Account[] accounts = new Account[1];
        Chequing testAccount = new Chequing("Shervin Tafreshi", "R3EVCQ8Z9", 500.00,0.50,10);

        accounts[0] = testAccount;
        testBank.addAccount(testAccount);
        testBank.addAccount(new GIC("Java Developer", "VE74A241C", 400.00, 4, 1.50));
        assertArrayEquals(accounts,testBank.searchByAccountName("Shervin Tafreshi"));
    }

    @Test
    public void testGetAllAccounts() {
        Bank testBank = new Bank("My Bank");
        Account[] accounts = new Account[4];
        Chequing testAccount = new Chequing("Shervin Tafreshi", "R3EVCQ8Z9", 380.00, 0.45, 5);
        Chequing testAccount2 = new Chequing("Java Developer", "VE74A241C", 125.00, 0.35, 4);
        GIC testAccount3 = new GIC("Android Developer", "45EQECX23", 850.00, 3, 0.80);
        GIC testAccount4 = new GIC("Kotlin Developer", "451GHVFSD", 525.00, 4, 0.50);

        accounts[0] = testAccount;
        accounts[1] = testAccount2;
        accounts[2] = testAccount3;
        accounts[3] = testAccount4;
        testBank.addAccount(testAccount);
        testBank.addAccount(testAccount2);
        testBank.addAccount(testAccount3);
        testBank.addAccount(testAccount4);
        assertArrayEquals(accounts,testBank.getAllAccounts());
    }

}