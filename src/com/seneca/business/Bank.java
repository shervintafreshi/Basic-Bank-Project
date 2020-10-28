
/*******************************
 * @author: Shervin Tafreshipour
 * Student ID: 155199169
 * Email: stafreshipour@myseneca.ca
 *******************************/

package com.seneca.business;
import com.seneca.accounts.Account;
import com.seneca.accounts.GIC;

import java.util.ArrayList;

public class Bank {

	private ArrayList<Account> accounts;
	private String bankName;

	// zero-arguement constructor

	public Bank() {
		bankName = "Seneca@York";
		accounts = new ArrayList<Account>();
	}

	// 1-arguement constructor

	public Bank(String Name) {
		if (Name == null)
			Name = "Seneca@York";

		bankName = Name;
		accounts = new ArrayList<Account>();
	}

	// overidded toString method

	public String toString() {

		StringBuffer strValue = new StringBuffer( "*** Welcome to the Bank of " + bankName + " ***\n");
		strValue.append("It has " + accounts.size() + " accounts.\n");

		for (int i = 0; i < accounts.size(); i++) {
			strValue.append(Integer.toString(i + 1) + ". number: " + accounts.get(i).getAccountNumber() + ", name: "
					+ accounts.get(i).getFullName() + ", balance: $" + Math.round(accounts.get(i).getBalance()) + "\n");
		}

		return strValue.toString();
	}

	// overidded hashCode method

	public int hashCode() {
		return accounts.hashCode() * bankName.hashCode();
	}

	// overidded equals method

	public boolean equals(Bank B) {

		if (B instanceof Bank == false || this.bankName != B.bankName)
			return false;

		if (!this.accounts.equals(B.getAllAccounts()))
			return false;

		return true;
	}

	// add account method

	public boolean addAccount(Account account) {

		if(account == null)
			return false;

		for (Account acct : accounts) {
			if (acct.getAccountNumber() == account.getAccountNumber())
				return false;
		}

		accounts.add(account);
		return true;
	}

	// remove account method

	public Account removeAccount(String accountNumber) {

		Account removedAccount = null;

		for (int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccountNumber().equals(accountNumber)) {
				removedAccount = accounts.get(i);
				accounts.remove(i);
			}
		}

		return removedAccount;
	}

	// search by balance method

	public Account[] searchByBalance(double balance) {

		int matches = 0;
		int x = 0;

		if (balance > 0) {
			for (int i = 0; i < accounts.size(); i++) {

				if (accounts.get(i) instanceof GIC) {
					if (balance == (accounts.get(i)).getBalance())
					   matches++;

				} else {
					if (balance == accounts.get(i).getBalance())
						matches++;
				}
			}
		}

		Account[] clients = new Account[matches];

		if (matches > 0) {
			for (int i = 0; i < accounts.size(); i++) {

				if (accounts.get(i) instanceof GIC) {
					if (balance == accounts.get(i).getBalance()) {
						clients[x] = accounts.get(i);
					    x++;
				    }

				}else {
					if (balance == accounts.get(i).getBalance()) {
						clients[x] = accounts.get(i);
						x++;
					}
				}

			}
		}

		return clients;
	}

	// search by account name method

	public Account[] searchByAccountName(String accountName) {

		int matches = 0;
		int x = 0;

		for (int i = 0; i < accounts.size(); i++) {
			 if (accountName.equals(accounts.get(i).getFullName())) {
				 matches++;
			 }
		}

		Account[] clients = new Account[matches];

		if (matches > 0) {
			for (int i = 0; i < accounts.size(); i++) {
				if (accountName.equals(accounts.get(i).getFullName())) {
					clients[x] = accounts.get(i);
					x++;
				}
			}
		}

		return clients;
	}

	// get all accounts method

	public Account[] getAllAccounts() {
		Account[] accountsArray = new Account[this.accounts.size()];
		accountsArray = accounts.toArray(accountsArray);
		return accountsArray;
	}

	// get bank Name method

	public String getBankName() {
		return bankName;
	}

}
