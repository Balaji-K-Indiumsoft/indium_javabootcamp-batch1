package com.indium.bankingapp.service;

import java.util.Set;
import java.util.TreeSet;
import com.indium.bankingapp.model.Account;

public class AccountServiceImpTreeSet implements AccountService {

    private Set<Account> accountsSet = new TreeSet<>();

    @Override
    public void addAccount(Account account) {
        accountsSet.add(account);
    }

    @Override
    public Account getAccount(String accountNumber) {
        for (Account account : accountsSet) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public Set<Account> getAllAccounts() {
        return accountsSet;
    }

    @Override
    public void updateAccount(String accountNumber, String newAccountHolderName) {
        for (Account account : accountsSet) {
            if (account.getAccountNumber().equals(accountNumber)) {
                account.setAccountHolderName(newAccountHolderName);
                return;
            }
        }
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null && amount > 0) {
            account.deposit(amount);
        }
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null && amount > 0) {
            account.withdraw(amount);
        }
    }

    @Override
    public void deleteAccount(String accountNumber) {
        Account accountToRemove = null;
        for (Account account : accountsSet) {
            if (account.getAccountNumber().equals(accountNumber)) {
                accountToRemove = account;
                break;
            }
        }
        if (accountToRemove != null) {
            accountsSet.remove(accountToRemove);
        }
    }
}
