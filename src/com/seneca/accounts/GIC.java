
/*******************************
 * @author: Shervin Tafreshipour
 * Student ID: 155199169
 * Email: stafreshipour@myseneca.ca
 *******************************/

package com.seneca.accounts;
import java.math.BigDecimal;
import java.math.*;

public class GIC extends Account implements Taxable{

    private BigDecimal annualInterestRate;
    private BigDecimal taxAmount;
    private BigDecimal interestIncome;
    private int investmentPeriod;

    // no-arguement constructor

    public GIC() {
     super();
     annualInterestRate = new BigDecimal(1.25);
     investmentPeriod = 1;
     calculateTax();
    }

    // 5-arguement constructor

    public GIC(String name, String number, double balance,int investmentPeriod,double annualInterestRate) {
     super(name,number,balance);
     setInvestmentPeriod(investmentPeriod);
     setAnnualInterestRate(annualInterestRate);
     calculateTax();
    }

    // overidded equals method

    public boolean equals(GIC account) {
     return (super.equals(account) && this.annualInterestRate == account.annualInterestRate && this.investmentPeriod == this.investmentPeriod );
    }

    // overrided toString method

    public String toString(){
      StringBuffer str = new StringBuffer(super.toString());
      str.append(
              "Account Type               : " + "GIC\n" +
              "Annual Interest Rate       : " + String.format("%.2f",annualInterestRate) + "%\n" +
              "Period of Investment       : " + investmentPeriod + " years\n" +
              "Interest Income at Maturity: $" + String.format("%.2f", this.interestIncome.doubleValue()) + "\n" +
              "Balance at Maturity        : $" + String.format("%.2f", this.getBalance())
      );
      return str.toString();
    }

    // overrided hashCode method

    public int hashCode() {
        return super.hashCode() * annualInterestRate.hashCode();
    }

    // overrided deposit method

    public void deposit(double amount) {}

    // overrided withdraw method

    public boolean withdraw(double amount) {return false;}

    // overrided getBalance method

    public double getBalance() {

        BigDecimal tempRate = new BigDecimal(( (annualInterestRate.doubleValue())/100  + 1));
        tempRate = tempRate.pow(investmentPeriod);
        BigDecimal tempBalance = new BigDecimal(super.getBalance());

        return (tempBalance.multiply(tempRate).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    // Calculate Tax method

    public void calculateTax() {

       BigDecimal taxRate = new BigDecimal(Taxable.taxRate / 100);
       BigDecimal matureBalance = new BigDecimal(this.getBalance());
       BigDecimal currentBalance = new BigDecimal(super.getBalance());
       BigDecimal interestIncome = matureBalance.subtract(currentBalance);
       this.interestIncome = interestIncome;
       taxAmount = interestIncome.multiply(taxRate);
    }

    // get Tax Amount method

    public double getTaxAmount() {
        return taxAmount.doubleValue();
    }

    // get create Tax statement method

    public String createTaxStatement() {
        StringBuffer str = new StringBuffer(
        "Tax rate       : " + (int)Taxable.taxRate + "%\n" +
        "Account Number : " + super.getAccountNumber() + "\n" +
        "Interest income: $" + String.format("%.2f",taxAmount.doubleValue()/(Taxable.taxRate /100)) + "\n" +
        "Amount of tax  : $" + String.format("%.2f",taxAmount) + "\n"
        );
        return str.toString();
    }

    // private setters

    private void setInvestmentPeriod(int investmentPeriod) {
        if (investmentPeriod < 0)
             investmentPeriod = 1;

        this.investmentPeriod = investmentPeriod;
    }

    private void setAnnualInterestRate(double interestRate) {
        if (interestRate < 0)
             interestRate = 1.25;


        this.annualInterestRate = new BigDecimal(interestRate);
    }

    // getters

    public double getInterestIncome() {
        return interestIncome.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double getAnnualInterestRate() {
        return annualInterestRate.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public int getInvestmentPeriod() {
        return investmentPeriod;
    }

    public double getStartingBalance() {
        return super.getBalance();
    }
}
