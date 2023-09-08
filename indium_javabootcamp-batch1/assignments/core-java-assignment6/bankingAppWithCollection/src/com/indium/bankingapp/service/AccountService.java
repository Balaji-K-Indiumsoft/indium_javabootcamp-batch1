package com.indium.bankingapp.service;

import java.util.List;

import com.indium.bankingapp.model.Account;



public interface AccountService {
	
	void addAccount(Account account);

	Account getAccount(String accountNumber);
	
	List<Account> getAllAccounts();
	
	void updateAccount(String accountNumber, String newAccountHolderName);
	
	void deposit(String accountNumber, double amount);
	
	void withdraw(String accountNumber, double amount);
	
	void deleteAccount(String accountNumber);
}
