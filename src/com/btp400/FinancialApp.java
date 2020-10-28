
/*******************************
 * @author: Shervin Tafreshipour
 * Student ID: 155199169
 * Email: stafreshipour@myseneca.ca
 *
 *
 * @author: Parsa Jalilifar
 * Student ID: 133308189
 * Email: pjalilifar@myseneca.ca
 *******************************/

package com.btp400;

import com.seneca.accounts.Account;
import com.seneca.accounts.Chequing;
import com.seneca.accounts.GIC;
import com.seneca.accounts.Taxable;
import com.seneca.business.Bank;
import java.util.Scanner;

public class FinancialApp {

    // main method whichs launches the financial app

    public static void main(String[] args) {

        int choice;
        Bank senecaBank = new Bank();
        LoadBank(senecaBank);

        while (true) {

          displayMenu(senecaBank.getBankName());
          choice = menuChoice(1,7);

          switch(choice) {
              case 1:
                  openAccount(senecaBank);
                  break;
              case 2:
                  closeAccount(senecaBank);
                  break;
              case 3:
                  depositMoney(senecaBank);
                  break;
              case 4:
                  withdrawMoney(senecaBank);
                  break;
              case 5:
                  displayAccounts(senecaBank);
                  break;
              case 6:
                  taxStatement(senecaBank);
                  break;
          }

          if (choice == 7) break;
        }

        System.out.println("Thank you for using the " + senecaBank.getBankName() + "Bank App");
    }

    // method to load accounts into bank

    static void LoadBank(Bank bank){

        Chequing account1 = new Chequing("John Doe", "E4W77RT2F", 4500.00, 0.15, 10);
        Chequing account2 = new Chequing("Mary Ryan", "D7Q21GQ8V", 3500.00, 0.10, 8);
        GIC account3 = new GIC("John Doe", "C81QE56H2", 6000.00, 2, 1.50);
        GIC account4 = new GIC("Mary Ryan", "T4W91ZS14", 15000.00, 4, 1.75);

        bank.addAccount(account1);
        bank.addAccount(account2);
        bank.addAccount(account3);
        bank.addAccount(account4);
    }

    // method which displays menu options

    static void displayMenu(String bankName) {
        System.out.println("Welcome to" + bankName + "Bank!");
        System.out.print(
                "1. Open an account. \n" +
                "2. Close an account. \n" +
                "3. Deposit money. \n" +
                "4. Withdraw money. \n" +
                "5. Display accounts. \n" +
                "6. Display a tax statement. \n" +
                "7. Exit \n\n"
        );
    }

    // method which handles user options

    static int menuChoice(int lowerBound, int upperBound) {

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your choice>");
        int number = input.nextInt();

        while (number < lowerBound || number > upperBound){
            System.out.println("Invalid Option!, Please enter your choice>");
            number = input.nextInt();
        }

        return number;
    }

    // method which sanitizes user input

    static boolean verifyInput(String input, int semiNumber, String[] args){

        int counter = 0;
        boolean match = false;

       if (input.isBlank()) {
           return false;
       }

      if (args != null) {
          for (String arg : args) {
              if (input.equals(arg)) {
                  match = true;
                  break;
              }
          }

          if (!match) {
              return false;
          }
      }

      for (int i = 0; i < input.length(); i++){

            if (input.charAt(i) == ';') {
                ++counter;
            }
      }

      if(counter != semiNumber)
            return false;

      return true;
    }

    // method to open an Account

    static void openAccount(Bank bank) {

        Account newAccount = null;
        Scanner input = new Scanner(System.in);
        String[] args = {"CHQ", "GIC"};
        String choice = null;
        String fields = null;

        System.out.println("Please enter the account type (CHQ/GIC)>");
        choice = input.nextLine();

        while (!verifyInput(choice, 0, args)) {
            System.out.println("Invalid Input!, Please enter the account type (CHQ/GIC)>");
            choice = input.nextLine();
        }

        if(choice.equals("CHQ")) {

            System.out.println("Please enter the account information at one line");
            System.out.println("(e.g. John M. Doe;A1234;1000.00;1.5;2)");
            fields = input.nextLine();

            while (!verifyInput(fields, 4, null)) {
                System.out.println("Invalid Input!, Please enter the account information at one line");
                System.out.println("(e.g. John M. Doe;A1234;1000.00;1.5;2)");
                fields = input.nextLine();
            }

            openCHQ(bank, newAccount, fields);
        }

        if(choice.equals("GIC")) {

            System.out.println("Please enter the account information at one line");
            System.out.println("(e.g. Mary R. Doe;A1234;1000.00;5;1.4)");
            fields = input.nextLine();

            while (!verifyInput(fields, 4, null)) {
                System.out.println("Invalid Input!, Please enter the account information at one line");
                System.out.println("(e.g. Mary R. Doe;A1234;1000.00;5;1.4)");
                fields = input.nextLine();
            }
            openGIC(bank, newAccount, fields);
        }
    }

    // method to open a chequing account

    static void openCHQ(Bank bank,Account newAccount, String fields) {
        String str = fields.substring(0,fields.indexOf(';'));
        String fullName = str;

        str = fields.substring(fields.indexOf(';') + 1);
        String accountNumber = str.substring(0,str.indexOf(';'));

        str = str.substring(str.indexOf(';') + 1);
        double accountBalance = Double.valueOf(str.substring(0, str.indexOf(';')));

        str = str.substring(str.indexOf(';') + 1);
        double serviceCharge = Double.valueOf(str.substring(0, str.indexOf(';')));

        str = str.substring(str.indexOf(';') + 1);
        int maxTransactions = Integer.valueOf(str);

        newAccount = new Chequing(fullName,accountNumber,accountBalance,serviceCharge,maxTransactions);
        bank.addAccount(newAccount);
    }

    // method to open a GIC account

    static void openGIC(Bank bank, Account newAccount, String fields) {
        String str = fields.substring(0,fields.indexOf(';'));
        String fullName = str;

        str = fields.substring(fields.indexOf(';') + 1);
        String accountNumber = str.substring(0,str.indexOf(';'));

        str = str.substring(str.indexOf(';') + 1);
        double accountBalance = Double.valueOf(str.substring(0, str.indexOf(';')));

        str = str.substring(str.indexOf(';') + 1);
        int investmentPeriod = Integer.valueOf(str.substring(0, str.indexOf(';')));

        str = str.substring(str.indexOf(';') + 1);
        double interestRate = Double.valueOf(str);

        newAccount = new GIC(fullName, accountNumber,accountBalance,investmentPeriod, interestRate);
        bank.addAccount(newAccount);
    }

    // method to close an account

    static void closeAccount(Bank bank) {

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the account number>");
        String number = input.nextLine();

        while(!verifyInput(number,0,null)){
            System.out.println("Invalid Input!, Please enter the account number>");
            number = input.nextLine();
        }

        bank.removeAccount(number);
    }

    // method to deposit money into account

    static void depositMoney(Bank bank){

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the account number and deposit amount>");
        System.out.println("eg: D4E456VWQ;400.00");
        String fields = input.nextLine();

        while(!verifyInput(fields,1,null)){
            System.out.println("Invalid Input!, Please enter the account number and deposit amount>");
            System.out.println("eg: D4E456VWQ;400.00");
            fields = input.nextLine();
        }

        String accountNumber = fields.substring(0,fields.indexOf(';'));
        double depositAmount = Double.valueOf(fields.substring(fields.indexOf(';') + 1));

        Account[] allAccounts = bank.getAllAccounts();

        for (Account account: allAccounts) {

            if(account.getAccountNumber().equals(accountNumber)) {
                account.deposit(depositAmount);
            }
        }
    }

    // method to withdraw money from account

    static void withdrawMoney(Bank bank){

        Scanner input = new Scanner(System.in);
        System.out.println("please enter the account number and withdraw amount>");
        System.out.println("eg: H7T90SV1E;750.00");
        String fields = input.nextLine();

        while(!verifyInput(fields,1,null)){
            System.out.println("Invalid Input!, Please enter the account number and deposit amount>");
            System.out.println("eg: D4E456VWQ;400.00");
            fields = input.nextLine();
        }

        String accountNumber = fields.substring(0,fields.indexOf(';'));
        double withdrawAmount = Double.valueOf(fields.substring(fields.indexOf(';') + 1));

        Account[] allAccounts = bank.getAllAccounts();

        for (Account account: allAccounts) {

            if(account.getAccountNumber().equals(accountNumber)) {
                account.withdraw(withdrawAmount);
            }
        }
    }

    // method to display accounts by user preference

    static void displayAccounts(Bank bank) {

        System.out.println("1. Display all accounts with the same account name.");
        System.out.println("2. Display all accounts with the same final balance.");
        System.out.println("3. Display all accounts opened at the bank.");

        switch(menuChoice(1,3)) {
            case 1:
                   accountsByName(bank);
                   break;
            case 2:
                   accountsByFinalBalance(bank);
                   break;
            case 3:
                   accountsAll(bank);
                   break;
        }
    }


    // display accounts with certain name

    static void accountsByName(Bank bank) {

        Scanner input = new Scanner(System.in);
        System.out.println("please enter the account name>");
        String accountName = input.nextLine();

        while(!verifyInput(accountName,0,null)){
            System.out.println("Invalid Input!, Please enter the account name>");
            accountName = input.nextLine();
        }

        for(Account account : bank.searchByAccountName(accountName)) {
            System.out.println(account);
            System.out.println('\n');
        }
    }

    // display accounts by final balance

    static void accountsByFinalBalance(Bank bank) {

        Scanner input = new Scanner(System.in);
        System.out.println("please enter the account balance>");
        Double accountBalance = input.nextDouble();

        while(!verifyInput(accountBalance.toString(),0,null)){
            System.out.println("Invalid Input!, Please enter the account name>");
            accountBalance = input.nextDouble();
        }

        for(Account account : bank.searchByBalance(accountBalance)) {
            System.out.println(account);
            System.out.println('\n');
        }
    }

    // display all accounts

    static void accountsAll(Bank bank){

        for(Account account : bank.getAllAccounts()) {
           System.out.println(account);
           System.out.println('\n');
        }
    }

    // display tax statement for all accounts with certain name

    static void taxStatement(Bank bank) {

        Scanner input = new Scanner(System.in);
        System.out.println("please enter the account name>");
        String accountName = input.nextLine();

        while(!verifyInput(accountName,0,null)){
            System.out.println("Invalid Input!, Please enter the account name>");
            accountName = input.nextLine();
        }

        String str = new String();
        int i = -1;

        for(Account account : bank.searchByAccountName(accountName)) {

            if (i == -1){
                System.out.println("Name: " + account.getLastName() + ", " + account.getFirstName());
                System.out.println("Tax Rate: " + Taxable.taxRate + "%");
                ++i;
            }

            if (account instanceof GIC) {
                ++i;
                System.out.println("\n[" + i + "]");
                str = ((GIC) account).createTaxStatement();
                str = str.substring(str.indexOf('\n') + 1);
                System.out.println(str);
            }
        }
    }

}
