package com.bankingApplication;

public class BankAccount {

	private String accountNumber;
	private String accountHolderName;
	private double balance;
	
	
	public BankAccount(String accountNumber, String accountHolderName, double balance) {
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
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
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void deposit(double amount) {
		if(amount >0) {
			balance += amount;
			System.out.println("Deposit Successful..");
		}else {
			System.out.println("Invalid amount for deposit..");
		}
	}
	
	public void withdraw(double amount) {
		if(amount >0 && amount<=balance) {
			balance -= amount;
			System.out.println("Withdrawal Successful..");
		}else {
			System.out.println("Invaild amount for withdrwal..");
		}
	}

	@Override
	public String toString() {
		return "Account Number: " + accountNumber + ",\n Account HolderName: " + accountHolderName + ", Balance: "
				+ balance ;
	}
	
	
	
	
	
}
