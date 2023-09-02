package com.bankingApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingApp {

	private static final int MAX_ACCOUNTS = 10;

	private static BankAccount[] accounts = new BankAccount[MAX_ACCOUNTS];

	private static int accountCount = 0;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nBanking App Menu: ");
			System.out.println("1. Add Account");
			System.out.println("2. View All Account");
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
				System.out.println("Invalid choice. Please try again. ");
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}

		}
	}

	private static void addAccount(Scanner scanner) {
		if (accountCount < MAX_ACCOUNTS) {
			System.out.print("Enter Account Number: ");
			String accountNumber = scanner.next();
			System.out.print("Enter Account Holder Name: ");
			String accountHolderName = scanner.next();

			double initialBalance = 0.0;
			boolean validInput = false;

			while (!validInput) {
				System.out.print("Enter Initial Balance: ");
				try {
					initialBalance = scanner.nextDouble();
					validInput = true;
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a valid numeric value for the initial balance.");
					scanner.next();
				}
			}

			accounts[accountCount] = new BankAccount(accountNumber, accountHolderName, initialBalance);
			accountCount++;

			System.out.println("Account created successfully.");
		} else {
			System.out.println("Maximum number of accounts reached.");
		}
	}

	private static void viewAllAccounts() {
		for (int i = 0; i < accountCount; i++) {
			System.out.println("\nAccount " + (i + 1) + ":\n " + accounts[i]);
		}
	}

	private static void viewAccount(Scanner scanner) {

		System.out.println("Enter Account Number");
		String accountNumber = scanner.next();

		for (int i = 0; i < accountCount; i++) {
			if (accounts[i].getAccountNumber().equals(accountNumber)) {
				System.out.println(accounts[i]);
				return;
			}
		}
		System.out.println("Account Number Invalid");
	}

	private static void updateAccount(Scanner scanner) {
		System.out.println("Enter Account Number");
		String accountNumber = scanner.next();

		for (int i = 0; i < accountCount; i++) {
			if (accounts[i].getAccountNumber().equals(accountNumber)) {
				System.out.print("Enter new account holder name: ");
				String newName = scanner.next();
				accounts[i].setAccountHolderName(newName);
				System.out.println("Account updated successfully.");
				return;
			}
		}
		System.out.println("Account not found...!");
	}

	private static void deposit(Scanner scanner) {
		System.out.print("Enter Account Number: ");
		String accountNumber = scanner.next();

		for (int i = 0; i < accountCount; i++) {
			if (accounts[i].getAccountNumber().equals(accountNumber)) {
				System.out.print("Enter deposit amount: ");
				double depositAmount = scanner.nextDouble();
				accounts[i].deposit(depositAmount);
				return;
			}
		}

		System.out.println("Account not found.");
	}

	private static void withdraw(Scanner scanner) {
		System.out.print("Enter Account Number: ");
		String accountNumber = scanner.next();

		for (int i = 0; i < accountCount; i++) {
			if (accounts[i].getAccountNumber().equals(accountNumber)) {
				System.out.print("Enter withdrawal amount: ");
				double withdrawalAmount = scanner.nextDouble();
				accounts[i].withdraw(withdrawalAmount);
				return;
			}
		}

		System.out.println("Account not found.");
	}

	private static void deleteAccount(Scanner scanner) {
		System.out.println("Enter Account Number: ");
		String accountNumber = scanner.next();

		for (int i = 0; i < accountCount; i++) {
			if (accounts[i].getAccountNumber().equals(accountNumber)) {

				// Remove the account and have the remaining accounts
				for (int j = 1; j < accountCount - 1; j++) {
					accounts[j] = accounts[j + 1];
				}
				accounts[accountCount - 1] = null;
				accountCount--;
				System.out.println("Account delete successfully");
				return;
			}
		}
		System.out.println("Account not found..");

	}

}
