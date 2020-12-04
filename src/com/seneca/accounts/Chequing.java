
package com.seneca.accounts;

import java.math.BigDecimal;

public class Chequing extends Account {

    private double[] transactions;
    private double serviceCharge;
    private int maxTransactions;
    private int numberOfTransactions = 0;

    // default constructor

    public Chequing() {
        super();
        this.serviceCharge = 0.25;
        transactions = new double[3];
    }

    // 5-arguement constructor

    public Chequing(String name, String number, double balance, double serviceCharge, int maxTransactions) {
        super(name,number, balance);
        setServiceCharge(serviceCharge);
        setNumberOfTransactions(maxTransactions);
        transactions = new double[this.maxTransactions];
    }

    // overrided equals method

    public boolean equals(Chequing account) {
        return (super.equals(account) && this.maxTransactions == account.maxTransactions &&
                this.serviceCharge == account.serviceCharge);
    }


    // overrided toString method

    public String toString() {

        String[] strArray = new String[numberOfTransactions];

        for (int i = 0; i < numberOfTransactions; i++) {
            if (transactions[i] > 0.00)
                 strArray[i] = "+" + String.format("%.2f",transactions[i]);
            else
                 strArray[i] = String.format("%.2f",transactions[i]);
        }

        StringBuffer str = new StringBuffer(super.toString());
        str.append("Account Type        : CHQ \n" +
                   "Service Charge      : $" + serviceCharge + "\n" +
                   "Total Charge        : $" + (serviceCharge * numberOfTransactions) + "\n" +
                   "List of Transactions: ");

        for (int i = 0; i < numberOfTransactions; i++){
            if ( i + 1 != numberOfTransactions)
                 str.append(strArray[i] + ", ");
            else
                 str.append(strArray[i]);
        }

        str.append("\nFinal Balance    : $" + String.format("%.2f",(this.getBalance())));

        return str.toString();
    }


    // overrided equals method

    public int hashCode() {
        return super.hashCode() * transactions.hashCode();
    }

    // overrided withdraw method

    public boolean withdraw(double amount) {

        if (amount > 0 && maxTransactions != numberOfTransactions && super.withdraw(amount) && this.getBalance() - (serviceCharge * numberOfTransactions) > 0 ) {
            transactions[numberOfTransactions] = -1 * (amount);
            ++numberOfTransactions;
            return true;
        }

        return false;
    }

    // overrided equals method

    public void deposit(double amount) {

        if(amount > 0 && maxTransactions != numberOfTransactions) {
            super.deposit(amount);
            transactions[numberOfTransactions] = amount;
            ++numberOfTransactions;
        }
    }

    // getters

    public double getBalance() {
        return (super.getBalance() - (numberOfTransactions * serviceCharge));
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public int getMaxTransactions(){
        return maxTransactions;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    // private setters

    private void setServiceCharge(double serviceCharge) {
        if (serviceCharge < 0)
             serviceCharge = 0.25;

        this.serviceCharge = serviceCharge;
    }

    private void setNumberOfTransactions(int maxTransactions) {
        if (maxTransactions < 0)
             maxTransactions = 3;

        this.maxTransactions = maxTransactions;
    }

}
