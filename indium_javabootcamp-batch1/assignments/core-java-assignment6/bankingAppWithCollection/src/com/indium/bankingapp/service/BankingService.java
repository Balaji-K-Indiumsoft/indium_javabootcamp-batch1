package com.indium.bankingapp.service;

import java.util.List;

import com.indium.bankingapp.model.BankAccount;



public interface BankingService {
	
	void addAccount(BankAccount account);

	BankAccount getAccount(String accountNumber);
	
	List<BankAccount> getAllAccounts();
	
	void updateAccount(String accountNumber, String newAccountHolderName);
	
	void deposit(String accountNumber, double amount);
	
	void withdraw(String accountNumber, double amount);
	
	void deleteAccount(String accountNumber);
}
