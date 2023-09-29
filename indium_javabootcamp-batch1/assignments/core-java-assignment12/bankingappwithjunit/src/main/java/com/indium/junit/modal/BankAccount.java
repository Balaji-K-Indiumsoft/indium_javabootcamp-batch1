package com.indium.junit.modal;

import com.indium.junit.db.BankAccountDAO;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private long balance;
    private String accountType;

    public BankAccount(String accountNumber, String accountHolderName, long balance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    @Override
    public String toString() {
        return "BankAccount Details\nAccount Number: " + accountNumber + ", Account Holder Name: " + accountHolderName
                + ", Balance: " + balance + ", Account Type: " + accountType;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            BankAccountDAO accountDAO = new BankAccountDAO();
            accountDAO.updateInDatabase(accountNumber, accountHolderName, balance, accountType);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Deposit failed.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            BankAccountDAO accountDAO = new BankAccountDAO();
            accountDAO.updateInDatabase(accountNumber, accountHolderName, balance, accountType);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Withdrawal failed.");
        }
    }

}