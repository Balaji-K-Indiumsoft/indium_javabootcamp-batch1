package com.indium.bankingapp.service;

import com.indium.bankingapp.model.Account;


import java.util.LinkedList;
import java.util.List;

public class AccountServiceImpLinkedList implements AccountService {

    private List<Account> accounts = new LinkedList<>();
    @Override
    public void addAccount(Account account) {
    accounts.add(account);
    }

    @Override
    public Account getAccount(String accountNumber) {
        for(Account account: accounts){
            if(account.getAccountNumber().equals(accountNumber)){
                return  account;
            }
        }
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accounts;
    }

    @Override
    public void updateAccount(String accountNumber, String newAccountHolderName) {
        for (Account account: accounts) {
            if(account.getAccountNumber().equals(accountNumber)){
                account.setAccountHolderName(newAccountHolderName);
                return;
            }

        }
    }

    @Override
    public void deposit(String accountNumber, double amount) {
    Account account = getAccount(accountNumber);
    if(account != null && amount > 0){
        account.deposit(amount);
    }
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        Account account =getAccount(accountNumber);
        if(account !=null && amount>0){
            account.withdraw(amount);
        }

    }

    @Override
    public void deleteAccount(String accountNumber) {
    for(int i=0; i<accounts.size(); i++){
        Account account = accounts.get(i);
        if(account.getAccountNumber().equals(accountNumber)){
            accounts.remove(i);
            return;
        }
    }
    }
}
