package com.indium.junit;

import com.indium.junit.db.DatabaseConnection;
import com.indium.junit.modal.BankAccount;
import com.indium.junit.services.BankingService;
import com.indium.junit.services.BankingServiceImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class BankingAppMain {

    private static final BankingService bankingService = new BankingServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBanking App Menu: ");
            System.out.println("1. Add Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. View Account");
            System.out.println("4. Update Account");
            System.out.println("5. Delete Account");
            System.out.println("6. Deposit");
            System.out.println("7. Withdraw");
            System.out.println("8. Print Statistics");
            System.out.println("9. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addAccount(scanner);
                    break;
                case 2:
                    viewAllAccounts();
                    break;
                case 3:
                    viewAccount(scanner);
                    break;
                case 4:
                    updateAccount(scanner);
                    break;
                case 5:
                    deleteAccount(scanner);
                    break;
                case 6:
                    deposit(scanner);
                    break;
                case 7:
                    withdraw(scanner);
                    break;
                case 8:
                    printStatistics(scanner);
                    break;
                case 9:
                    System.out.println("Thank you for using this bank. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void addAccount(Scanner scanner) {
        System.out.println("Enter Account Number: ");
        String accountNumber = scanner.next();

        System.out.println("Enter Account Holder Name: ");
        String accountHolderName = scanner.next();

        System.out.println("Enter Initial Balance: ");
        long initialBalance = scanner.nextLong();

        System.out.println("Select Account Type:");
        System.out.println("1. Savings");
        System.out.println("2. Current");
        int accountTypeChoice = scanner.nextInt();
        String accountType = (accountTypeChoice == 1) ? "Savings" : "Current";

        bankingService.addAccount(new BankAccount(accountNumber, accountHolderName, initialBalance, accountType), accountType);

        System.out.println("Account created successfully.");
    }


    private static void viewAllAccounts() {
        List<BankAccount> accounts = bankingService.viewAllAccounts();

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (BankAccount account : accounts) {
                System.out.println(account);
            }
        }
    }


    private static void viewAccount(Scanner scanner) {
        System.out.println("Enter the Account Number: ");
        String accountNumber = scanner.next();

        BankAccount account = bankingService.getAccount(accountNumber);

        if (account != null) {
            System.out.println("Account Details: ");
            System.out.println(account);
        } else {
            System.out.println("Account not found");
        }
    }


    private static void updateAccount(Scanner scanner) {
        System.out.println("Enter Account Number");
        String accountNumber = scanner.next();

        BankAccount account = bankingService.getAccount(accountNumber);

        if (account != null) {
            System.out.println("Enter new account holder name");
            String newAccountHolderName = scanner.next();

            bankingService.updateAccount(accountNumber, newAccountHolderName);
            System.out.println("Account updated successfully");
        } else {
            System.out.println("Account not found.");
        }
    }


    private static void deleteAccount(Scanner scanner) {
        System.out.println("Enter Account Number: ");
        String accountNumber = scanner.next();

        bankingService.deleteAccount(accountNumber);
        System.out.println("Account deleted successfully.");
    }


    private static void deposit(Scanner scanner) {
        System.out.println("Enter Account Number: ");
        String accountNumber = scanner.next();

        BankAccount account = bankingService.getAccount(accountNumber);

        if (account != null) {
            System.out.println("Enter deposit amount");
            double depositAmount = scanner.nextDouble();

            bankingService.deposit(accountNumber, depositAmount);
        } else {
            System.out.println("Account not found.");
        }
    }


    private static void withdraw(Scanner scanner) {
        System.out.println("Enter Account Number");
        String accountNumber = scanner.next();

        BankAccount account = bankingService.getAccount(accountNumber);

        if (account != null) {
            System.out.println("Enter withdraw amount: ");
            double withdrawAmount = scanner.nextDouble();

            bankingService.withdraw(accountNumber, withdrawAmount);
        } else {
            System.out.println("Account not found. ");
        }
    }


    private static void printStatistics(Scanner scanner) {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String queryA = "SELECT COUNT(*) FROM BankAccounts WHERE balance > 100000";
            ResultSet resultSetA = statement.executeQuery(queryA);
            if (resultSetA.next()) {
                System.out.println("No of accounts which has balance more than 1 lac: " + resultSetA.getInt(1));
            }

            String queryB = "SELECT accountType, COUNT(*) FROM BankAccounts GROUP BY accountType";
            ResultSet resultSetB = statement.executeQuery(queryB);
            System.out.println("Account count by account type:");
            while (resultSetB.next()) {
                System.out.println(resultSetB.getString(1) + ": " + resultSetB.getInt(2));
            }

            String queryC = "SELECT accountType, COUNT(*) FROM BankAccounts GROUP BY accountType ORDER BY accountType";
            ResultSet resultSetC = statement.executeQuery(queryC);
            System.out.println("Account count by account type with sorting:");
            while (resultSetC.next()) {
                System.out.println(resultSetC.getString(1) + ": " + resultSetC.getInt(2));
            }

            String queryD = "SELECT accountType, AVG(balance) FROM BankAccounts GROUP BY accountType";
            ResultSet resultSetD = statement.executeQuery(queryD);
            System.out.println("Average balance by account type:");
            while (resultSetD.next()) {
                System.out.println(resultSetD.getString(1) + ": " + resultSetD.getDouble(2));
            }

            System.out.println("Enter account name to search: ");
            String accountName = scanner.nextLine();
            String queryE = "SELECT accountNumber FROM BankAccounts WHERE accountHolderName LIKE '%" + accountName + "%'";
            ResultSet resultSetE = statement.executeQuery(queryE);
            System.out.println("Account IDs with account name containing \"" + accountName + "\":");
            while (resultSetE.next()) {
                System.out.println(resultSetE.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
