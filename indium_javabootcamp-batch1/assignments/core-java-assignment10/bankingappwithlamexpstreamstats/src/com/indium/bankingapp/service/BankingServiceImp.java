package com.indium.bankingapp.service;


import java.util.*;
import java.util.stream.Collectors;

import com.indium.bankingapp.model.BankAccount;



public class BankingServiceImp<T extends BankAccount> implements BankingService<T> {
	private List<T> accounts = new ArrayList<>();


	@Override
	public void addAccount(T account, String accountType) throws IllegalArgumentException {
		if (account != null && !accounts.contains(account)) {
			account.setAccountType(accountType);
			accounts.add(account);
		} else {
			throw new IllegalArgumentException("Account is invalid or already exists.");
		}
	}

	@Override
	public T getAccount(String accountNumber) {
		for (T account : accounts) {
			if (account.getAccountNumber().equals(accountNumber)) {
				return account;
			}
		}
		return null;
	}

	@Override
	public List<T> getAccountsByAccountType(String accountType) {
		return accounts.stream()
				.filter(account -> accountType.equals(account.getAccountType()))
				.collect(Collectors.toList());
	}

	@Override
	public void updateAccount(String accountNumber, String newAccountHolderName) {
		for (T account : accounts) {
			if (account.getAccountNumber().equals(accountNumber)) {
				account.setAccountHolderName(newAccountHolderName);
				return;
			}
		}
	}

	@Override
	public void deposit(String accountNumber, double amount) {
		T account = getAccount(accountNumber);
		if (account != null && amount > 0) {
			account.deposit(amount);
		}
	}

	@Override
	public void withdraw(String accountNumber, double amount) {
		T account = getAccount(accountNumber);
		if (account != null && amount > 0) {
			account.withdraw(amount);
		}
	}

	@Override
	public void deleteAccount(String accountNumber) {
		accounts.removeIf(account -> account.getAccountNumber().equals(accountNumber));
	}

	@Override
	public long getCountOfAccountsAboveBalance(long balance) {
		return accounts.stream()
				.filter(account -> account.getBalance() > balance)
				.count();
	}

	@Override
	public Map<String, Long> getCountByAccountType() {
		Map<String, Long> accountTypeCounts = new HashMap<>();
		for (T account : accounts) {
			String accountType = account.getAccountType();
			accountTypeCounts.put(accountType, accountTypeCounts.getOrDefault(accountType, 0L) + 1);
		}
		return accountTypeCounts;
	}

	@Override
	public Map<String, Long> getByAccountTypeSorted() {
		Map<String, Long> accountTypeCounts = getCountByAccountType();
		return accountTypeCounts.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	@Override
	public Map<String, Double> getAverageBalanceByAccountType() {
		Map<String, Double> avgBalanceByAccountType = new HashMap<>();
		Map<String, List<T>> accountsByType = accounts.stream()
				.collect(Collectors.groupingBy(account -> account.getAccountType()));

		for (Map.Entry<String, List<T>> entry : accountsByType.entrySet()) {
			List<T> accountsOfType = entry.getValue();
			double avgBalance = accountsOfType.stream()
					.mapToDouble(T::getBalance)
					.average()
					.orElse(0.0);
			avgBalanceByAccountType.put(entry.getKey(), avgBalance);
		}
		return avgBalanceByAccountType;
	}

	@Override
	public List<T> getAllAccounts() {
		return Collections.unmodifiableList(accounts);
	}
}
