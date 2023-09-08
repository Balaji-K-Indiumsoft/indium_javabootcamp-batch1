package com.indium.bankingapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.indium.bankingapp.model.Account;

public class AccountServiceImpTreeMap implements AccountService {

    private Map<String, Account> accountsMap = new TreeMap<>();

    @Override
    public void addAccount(Account account) {
        accountsMap.put(account.getAccountNumber(), account);
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountsMap.get(accountNumber);
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accountsMap.values());
    }

    @Override
    public void updateAccount(String accountNumber, String newAccountHolderName) {
        Account account = accountsMap.get(accountNumber);
        if (account != null) {
            account.setAccountHolderName(newAccountHolderName);
        }
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        Account account = accountsMap.get(accountNumber);
        if (account != null && amount > 0) {
            account.deposit(amount);
        }
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        Account account = accountsMap.get(accountNumber);
        if (account != null && amount > 0) {
            account.withdraw(amount);
        }
    }

    @Override
    public void deleteAccount(String accountNumber) {
        accountsMap.remove(accountNumber);
    }
}
