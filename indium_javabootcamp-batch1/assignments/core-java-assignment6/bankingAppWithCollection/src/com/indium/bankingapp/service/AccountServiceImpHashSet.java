package com.indium.bankingapp.service;

import com.indium.bankingapp.model.Account;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AccountServiceImpHashSet implements AccountService{

    private Set<Account> accounts = new HashSet<>();
    @Override
    public void addAccount(Account account) {
    accounts.add(account);
    }

    @Override
    public Account getAccount(String accountNumber) {
        for (Account account: accounts) {
            if (account.getAccountNumber().equals(accountNumber)){
                return account;
            }
        }
        return null;
    }

    @Override
    public Set<Account> getAllAccounts() {
        return accounts;
    }

    @Override
    public void updateAccount(String accountNumber, String newAccountHolderName) {
    for (Account account: accounts){
        if (account.getAccountNumber().equals(accountNumber)){
            account.setAccountHolderName(newAccountHolderName);
            return;
        }
    }
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
    if (account !=null && amount>0){
        account.deposit(amount);
    }
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account !=null && amount>0) {
            account.withdraw(amount);
        }
    }

    @Override
    public void deleteAccount(String accountNumber) {
        Iterator<Account> it =accounts.iterator();
        while(it.hasNext()) {
            Account account = it.next();
            if (account.getAccountNumber().equals(accountNumber)){
                it.remove();
                return;
            }
        }
        }
}
