package com.indium.junit.services;


import com.indium.junit.modal.BankAccount;

import java.util.List;

public interface BankingService {

    void addAccount(BankAccount account, String accountType);

    BankAccount getAccount(String accountNumber);

    void updateAccount(String accountNumber, String newAccountHolderName);

    void deposit(String accountNumber, double amount);

    void withdraw(String accountNumber, double amount);

    void deleteAccount(String accountNumber);

    BankAccount viewAccount(String accountNumber);

    List<BankAccount> viewAllAccounts();
}
