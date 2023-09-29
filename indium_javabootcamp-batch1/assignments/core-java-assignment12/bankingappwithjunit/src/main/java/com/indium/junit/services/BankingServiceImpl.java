package com.indium.junit.services;


import com.indium.junit.db.BankAccountDAO;
import com.indium.junit.modal.BankAccount;

import java.util.List;

public class BankingServiceImpl implements BankingService {

    private BankAccountDAO accountDAO;

    public BankingServiceImpl() {
        this.accountDAO = new BankAccountDAO();
    }

    @Override
    public void addAccount(BankAccount account, String accountType) {
        accountDAO.saveToDatabase(account.getAccountNumber(), account.getAccountHolderName(), account.getBalance(), accountType);
    }

    @Override
    public BankAccount getAccount(String accountNumber) {
        return accountDAO.getFromDatabase(accountNumber);
    }

    @Override
    public void updateAccount(String accountNumber, String newAccountHolderName) {
        BankAccount account = accountDAO.getFromDatabase(accountNumber);
        if (account != null) {
            account.setAccountHolderName(newAccountHolderName);
            accountDAO.updateInDatabase(account.getAccountNumber(), account.getAccountHolderName(), account.getBalance(), account.getAccountType());
        }
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        BankAccount account = accountDAO.getFromDatabase(accountNumber);
        if (account != null && amount > 0) {
            account.deposit(amount);
            accountDAO.updateInDatabase(account.getAccountNumber(), account.getAccountHolderName(), account.getBalance(), account.getAccountType());
        }
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        BankAccount account = accountDAO.getFromDatabase(accountNumber);
        if (account != null && amount > 0) {
            account.withdraw(amount);
            accountDAO.updateInDatabase(account.getAccountNumber(), account.getAccountHolderName(), account.getBalance(), account.getAccountType());
        }
    }

    @Override
    public void deleteAccount(String accountNumber) {
        accountDAO.deleteFromDatabase(accountNumber);
    }

    @Override
    public BankAccount viewAccount(String accountNumber) {
        return accountDAO.getFromDatabase(accountNumber);
    }

    @Override
    public List<BankAccount> viewAllAccounts() {
        return accountDAO.getAllFromDatabase();
    }
}
