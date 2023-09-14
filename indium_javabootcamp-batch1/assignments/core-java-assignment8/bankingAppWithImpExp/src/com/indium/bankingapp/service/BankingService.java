package com.indium.bankingapp.service;

import java.util.List;
import java.util.Map;

import com.indium.bankingapp.model.BankAccount;



public interface BankingService<T extends BankAccount> {
	
//	void addAccount(BankAccount account);
//void addAccount(T account, String accountType) throws IllegalArgumentException;
//	BankAccount getAccount(String accountNumber);
	
//	List<BankAccount> getAllAccounts();

   void addAccount(T account, String accountType) throws IllegalArgumentException;


	T getAccount(String accountNumber);

	List<T> getAccountsByAccountType(String accountType);

	void updateAccount(String accountNumber, String newAccountHolderName);

	void deposit(String accountNumber, double amount);

	void withdraw(String accountNumber, double amount);

	void deleteAccount(String accountNumber);

	long getCountOfAccountsAboveBalance(double balance);

	Map<String, Long> getCountByAccountType();

	Map<String, Long> getByAccountTypeSorted();

	Map<String, Double> getAverageBalanceByAccountType();

	List<BankAccount> getAllAccounts();


}
