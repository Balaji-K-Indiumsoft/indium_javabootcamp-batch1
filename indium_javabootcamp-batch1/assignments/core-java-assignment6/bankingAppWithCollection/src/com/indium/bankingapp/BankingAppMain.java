package com.indium.bankingapp;

import java.util.List;
import java.util.Scanner;

import com.indium.bankingapp.model.BankAccount;
import com.indium.bankingapp.service.BankingService;
import com.indium.bankingapp.service.BankingServiceImpl;



public class BankingAppMain {
	
	private static final BankingService bankingService = new BankingServiceImpl();
	
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
            System.out.println("8. Exit");
            
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
                System.out.println("Thank you for using this bank. Goodbye!");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    
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
		double initialBalance = scanner.nextDouble();
		
		BankAccount account = new BankAccount(accountNumber, accountHolderName, initialBalance);
		bankingService.addAccount(account);
		
		System.out.println("Account created successfully");
	}

}
