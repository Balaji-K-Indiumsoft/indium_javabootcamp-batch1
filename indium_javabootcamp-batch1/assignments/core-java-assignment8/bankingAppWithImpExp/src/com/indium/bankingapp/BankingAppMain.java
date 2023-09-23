package com.indium.bankingapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import com.indium.bankingapp.model.BankAccount;
import com.indium.bankingapp.service.BankingService;
import com.indium.bankingapp.service.BankingServiceImp;

import javax.swing.*;


public class BankingAppMain {
	
	private static final BankingService bankingService = new BankingServiceImp();

	
	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("\nBanking App Menu: ");
            System.out.println("1. Add Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. View Account");
            System.out.println("4. Update Account");
            System.out.println("5. Delete Account");
            System.out.println("6. Deposit");
            System.out.println("7. Withdraw");
			System.out.println("8. Print Statistics");
			System.out.println("9. Export");
			System.out.println("10. Import");
            System.out.println("11. Exit");
            
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
					printStatistics();
					break;
				case 9:
					exportAccounts();
					break;
				case 10:
					importAccounts();
					break;
            case 11:
                System.out.println("Thank you for using this bank. Goodbye!");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    
		}
		
	}
	private static void exportAccounts() {
		List<BankAccount> accountsList = (List<BankAccount>) bankingService.getAllAccounts();

		try (FileWriter writer = new FileWriter("export.txt")) {
			for (BankAccount account : accountsList) {
				String accountDetails = account.toString();
				writer.write(accountDetails + "\n");
			}
			System.out.println("Accounts exported successfully to export.txt");
		} catch (IOException e) {
			System.out.println("Error exporting accounts to file.");
		}
	}



	private static void importAccounts() {
		try (FileReader reader = new FileReader("import.txt");
			 BufferedReader bufferedReader = new BufferedReader(reader)) {

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] accountInfo = line.split(", ");
				if (accountInfo.length == 4) {
					String accountNumber = accountInfo[0];
					String accountHolderName = accountInfo[1];
					double balance = Double.parseDouble(accountInfo[2]);
					String accountType = accountInfo[3];

					BankAccount account = new BankAccount(accountNumber, accountHolderName, balance, accountType);
					bankingService.addAccount(account, accountType);
				}
			}
			System.out.println("Accounts imported successfully from import.txt");
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error importing accounts from file: " + e.getMessage());
		}
	}



	private static void printStatistics() {

		long accountsAbove1Lac = bankingService.getCountOfAccountsAboveBalance(100000);
		System.out.println("Number of accounts with a balance more than 1 lac: " + accountsAbove1Lac);


		Map<String, Long> accountTypeCounts = bankingService.getCountByAccountType();
		System.out.println("Number of accounts by account type:");
		for (Map.Entry<String, Long> entry : accountTypeCounts.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}


		Map<String, Long> sortedAccountTypeCounts = bankingService.getByAccountTypeSorted();
		System.out.println("Number of accounts by account type with sorting:");
		for (Map.Entry<String, Long> entry : sortedAccountTypeCounts.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}


		Map<String, Double> avgBalanceByAccountType = bankingService.getAverageBalanceByAccountType();
		System.out.println("Average balance by account type:");
		for (Map.Entry<String, Double> entry : avgBalanceByAccountType.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	private static void viewAccount(Scanner scanner) {
		System.out.println("Enter the Account Number: ");
		String accountNumber =scanner.next();
		
		BankAccount account = bankingService.getAccount(accountNumber);
		
		if(account != null) {
			System.out.println("Account Details: ");
			System.out.println(account);
		}else {
			System.out.println("Account not found");
		}
		
		
	}

	private static void withdraw(Scanner scanner) {
		System.out.println("Enter Account Number");
		
		String accountNumber = scanner.next();
		BankAccount account = bankingService.getAccount(accountNumber);
		
		if(account != null) {
			System.out.println("Enter withdraw amount: ");
			double withdrawAmount = scanner.nextDouble();
			
			bankingService.withdraw(accountNumber, withdrawAmount);
		}else {
			System.out.println("Account not found. ");
		}
	}

	private static void deposit(Scanner scanner) {
		System.out.println("Enter Account Number: ");
		
		String accountNumber = scanner.next();
		
		BankAccount account = bankingService.getAccount(accountNumber);
		
		if(account != null) {
			System.out.println("Enter deposit amount");
			double depositAmount = scanner.nextDouble();
			
			bankingService.deposit(accountNumber, depositAmount);
		}else {
			System.out.println("Account not found. ");
		}
		
	}

	private static void deleteAccount(Scanner scanner) {
		System.out.println("Enter Account Number: ");
		String accountNumber =scanner.next();
		
		bankingService.deleteAccount(accountNumber);
		System.out.println("Account deleted successfully. ");
		
	}

	private static void updateAccount(Scanner scanner) {
		
		System.out.println("Enter Account Number");
		String accountNumber = scanner.next();
		
		BankAccount account = bankingService.getAccount(accountNumber);
		
		if(account !=null) {
			System.out.println("Enter new account holder name");
			String newAccountHolderName = scanner.next();
			
			bankingService.updateAccount(accountNumber, newAccountHolderName);
			System.out.println("Account updated successfully");
		}else {
			System.out.println("Account not found. ");
		}
		
	}

	private static void viewAllAccounts() {
		List<BankAccount> allAccount = bankingService.getAllAccounts();

		if (allAccount.isEmpty()) {
			System.out.println("No account found.");
		} else {
			for (BankAccount account : allAccount) {
				System.out.println(account);
			}
		}
	}


	private static void addAccount(Scanner scanner) {
		System.out.println("Enter Account Number: ");
		String accountNumber = scanner.next();
		System.out.println("Enter Account Holder Name: ");
		String accountHolderName = scanner.next();
		System.out.println("Enter Initial Balance: ");
		double initialBalance = scanner.nextDouble();
		System.out.println("Enter Account Type: ");
		String accountType = scanner.next();
		
		BankAccount account = new BankAccount(accountNumber, accountHolderName, initialBalance,accountType);
		bankingService.addAccount(account, accountType);
		
		System.out.println("Account created successfully");
	}

}