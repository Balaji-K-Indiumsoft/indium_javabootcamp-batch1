package com.indium.bankingapp;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.indium.bankingapp.model.BankAccount;
import com.indium.bankingapp.service.BankingService;
import com.indium.bankingapp.service.BankingServiceImp;



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
					printStatistics();
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

	private static void printStatistics() {

		System.out.println("Banking App Statistics: ");
		System.out.println("1. No of accounts which has balance more than 1 lac");
		System.out.println("2. Show no of account by account type");
		System.out.println("3. Show no of accounts by account type with sorting");
		System.out.println("4. Show avg balance by account type");
		System.out.println("5. List account ids whose account name contains given name");

		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice){
			case 1:
				printAccountsAboveBalance(100000);
				break;
			case 2:
				printAccountCountByType();
				break;
			case 3:
				printAccountCountByTypeSorted();
				break;
			case 4:
				printAverageBalanceByType();
				break;
			case 5:
				System.out.println("Enter account name to search: ");
				String accountName = scanner.nextLine();
				printAccountIdsByAccountName(accountName);
				break;
			default:
				System.err.println("Invalid choice. ");
		}
	}

	private static void printAccountIdsByAccountName(String accountName) {
		if (accountName == null || accountName.trim().isEmpty()) {
			System.out.println("Account name is empty or null. Please provide a valid account name.");
			return;
		}

		List<BankAccount> allAccounts = bankingService.getAllAccounts();
		System.out.println("Account IDs with account name containing \"" + accountName + "\":");
		allAccounts.stream()
				.filter(account -> account.getAccountHolderName().toLowerCase().contains(accountName.toLowerCase()))
				.map(BankAccount::getAccountNumber)
				.forEach(System.out::println);
	}

	private static void printAverageBalanceByType() {
		Map<String, Double> avgBalanceByAccountType = bankingService.getAverageBalanceByAccountType();
		System.out.println("Average balance by account type:");
		for (Map.Entry<String, Double> entry : avgBalanceByAccountType.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	private static void printAccountCountByTypeSorted() {
		Map<String, Long> sortedAccountTypeCounts = bankingService.getByAccountTypeSorted();
		System.out.println("Number of accounts by account type with sorting:");
		for (Map.Entry<String, Long> entry : sortedAccountTypeCounts.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	private static void printAccountCountByType() {
		Map<String, Long> accountTypeCounts = bankingService.getCountByAccountType();
		System.out.println("Number of accounts by account type:");
		for (Map.Entry<String, Long> entry : accountTypeCounts.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	private static void printAccountsAboveBalance(long balance ) {
		long accountAboveBalance = bankingService.getCountOfAccountsAboveBalance(balance);
		System.out.println("Number of accounts with a balance more than " + balance + ": " + accountAboveBalance);
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
		
		if(allAccount.isEmpty()) {
			System.out.println("No account found. ");
		}else {
			for(BankAccount account: allAccount) {
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
		long initialBalance = scanner.nextInt();
		System.out.println("Select Account Type: ");
		System.out.println("1. Savings");
		System.out.println("2. Current");
		int accountTypeChoice = scanner.nextInt();
		String accountType;
		switch (accountTypeChoice){
			case 1:
				accountType ="Savings";
				break;
			case 2:
				accountType = "Current";
				break;
			default:
				System.out.println("Invalid choice. Defaulting to Savings. ");
				accountType = "Savings";
				break;
		}

		
		BankAccount account = new BankAccount(accountNumber, accountHolderName, initialBalance,accountType);
		bankingService.addAccount(account, accountType);
		
		System.out.println("Account created successfully.");
		System.out.println(account);
	}

}
