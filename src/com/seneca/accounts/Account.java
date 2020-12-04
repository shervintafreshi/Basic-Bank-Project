
/*******************************
 * @author: Shervin Tafreshipour
 * Student ID: 155199169
 * Email: stafreshipour@myseneca.ca
 *******************************/

package com.seneca.accounts;
import java.math.BigDecimal;

public class Account {

	// Account properties

	private String accountName;
	private String accountNumber;
	private BigDecimal accountBalance;
	
	// zero-argument constructor
	
	public Account() {
		
		accountName = "";
		accountNumber = "";
		accountBalance = new BigDecimal(0.00);
	}
	
	// 3-argument constructor
	
	public Account(String name, String number, double balance) {
		setFullName(name);
		setAccountNumber(number);
		setAccountBalance(balance);
	}
	
	// overrided toString method

	public String toString() {
		StringBuffer str = new StringBuffer(
		      "Name           : " + getLastName() + ", " + getFirstName() +  "\n"
			+ "Number         : " + accountNumber + "\n"
			+ "Current Balance: $" + String.format("%.2f",accountBalance) + "\n"
		);
		return str.toString();
	}
	
	// overrided equals method
	
	public boolean equals(Account acctObj) {

		if(acctObj == null)
			return false;

		BigDecimal tempBalance2 = new BigDecimal(acctObj.getBalance());
		
		if (accountBalance.equals(tempBalance2) && this.accountName == acctObj.getFullName()
		    && this.accountNumber == acctObj.getAccountNumber())
		      return true;
		else 
			  return false;
	}

	// overrided hashCode method

	public int hashCode() {
		return accountName.hashCode() * accountNumber.hashCode() * accountBalance.hashCode();
	}

	public boolean withdraw(double amount) {

		BigDecimal tempAmount = new BigDecimal(amount);

		if (amount > 0.00 && (this.accountBalance.subtract(tempAmount)).signum() > 0.00) {

			accountBalance = accountBalance.subtract(tempAmount);
            return true;
		}

		return false;
	}

	public void deposit(double amount) {

		BigDecimal tempAmount = new BigDecimal(amount);

		if(amount > 0.00) {
			accountBalance = accountBalance.add(tempAmount);
		}
	}

	// getters for Account properties
	
	public String getFullName() {
		return accountName;
	}

	public String getFirstName() {
		if (accountName.isBlank())
		    return accountName;

        return accountName.substring(0,accountName.indexOf(" "));
	}

	public String getLastName() {
		if (accountName.isBlank())
            return accountName;

		return accountName.substring(accountName.indexOf(" ") + 1);
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public double getBalance() {
		return accountBalance.doubleValue();
	}

	// setters for Account properties

	private void setFullName(String name) {
		if(name != null)
			accountName = name;
		else
			accountName = "";
	}

	private void setAccountNumber(String number) {
		if(number != null)
			accountNumber = number;
		else
			accountNumber = "";
	}

	private void setAccountBalance(double balance) {

		if(balance >= 0.00)
			accountBalance = new BigDecimal(balance);
		else
			accountBalance = new BigDecimal(0.00);
	}
	
}
