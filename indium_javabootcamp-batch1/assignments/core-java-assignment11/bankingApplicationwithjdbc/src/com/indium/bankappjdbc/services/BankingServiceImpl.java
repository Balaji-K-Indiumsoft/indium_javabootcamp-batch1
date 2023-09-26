package com.indium.bankappjdbc.services;

import com.indium.bankappjdbc.db.BankAccountDAO;
import com.indium.bankappjdbc.modal.BankAccount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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

    @Override
    public void importData() {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("./input/input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String accHolderName = parts[0];
                long balance = Long.parseLong(parts[1]);
                int accNumber = Integer.parseInt(parts[2]);
                String accType = parts[3];
                BankAccount newAccount = new BankAccount(String.valueOf(accNumber), accHolderName, balance, accType);
                accountDAO.create(newAccount);
                counter++;
            }
            System.out.println("Imported " + counter + " records");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void exportData() {
        int counter = 0;
        try (PrintWriter out = new PrintWriter(new FileWriter("./output/output.txt"))) {
            List<BankAccount> accounts = accountDAO.getAllFromDatabase();
            for (BankAccount account : accounts) {
                StringBuilder accountRecord = new StringBuilder();
                accountRecord.append(account.getAccountHolderName())
                        .append(",")
                        .append(account.getBalance())
                        .append(",")
                        .append(account.getAccountNumber())
                        .append(",")
                        .append(account.getAccountType())
                        .append("\n");
                out.write(accountRecord.toString());
                counter++;
            }
            System.out.println("Exported " + counter + " account details");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
