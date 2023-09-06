package com.indium.bankingapp.service;

import java.util.ArrayList;
import java.util.List;

import com.indium.bankingapp.model.BankAccount;



public class BankingServiceImpl implements BankingService {

	// Array List
	private List<BankAccount> accounts = new ArrayList();

	// Add Account
	@Override
	public void addAccount(BankAccount account) {
		accounts.add(account);
	}

	// get one account
	@Override
	public BankAccount getAccount(String accountNumber) {
		for (BankAccount account : accounts) {
			if (account.getAccountNumber().equals(accountNumber)) {
				return account;
			}
		}
		return null;
	}

	// get all accounts
	@Override
	public List<BankAccount> getAllAccounts() {

		return accounts;
	}

	// update account
	@Override
	public void updateAccount(String accountNumber, String newAccountHolderName) {

		for (BankAccount account : accounts) {
			if (account.getAccountNumber().equals(accountNumber)) {
				account.setAccountHolderName(newAccountHolderName);
				return;
			}
		}

	}

	// deposit
	@Override
	public void deposit(String accountNumber, double amount) {
		BankAccount account = getAccount(accountNumber);
		if (account != null && amount > 0) {
			account.deposit(amount);
		}

	}

	@Override
	public void withdraw(String accountNumber, double amount) {
		BankAccount account = getAccount(accountNumber);
		if (account != null && amount > 0) {
			account.withdraw(amount);
		}

	}

	@Override
	public void deleteAccount(String accountNumber) {

		for (int i = 0; i < accounts.size(); i++) {
			BankAccount account = accounts.get(i);
			if (account.getAccountNumber().equals(accountNumber)) {
				accounts.remove(i);
				return;
			}
		}

	}

}
