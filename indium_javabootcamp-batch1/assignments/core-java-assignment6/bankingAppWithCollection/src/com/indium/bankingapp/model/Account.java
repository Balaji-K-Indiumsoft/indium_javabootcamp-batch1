package com.indium.bankingapp.model;

public class Account {
	
	private String accountNumber;
	private String accountHolderName;
	private double balance;
	
	
	public Account(String accountNumber, String accountHolderName, double balance) {
		super();
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
			balance+=amount;
			System.out.println("Deposit successful. ");
		}else {
			System.out.println("Deposit faild");
		}
	}
	
	public void withdraw(double amount) {
		if(amount > 0 && amount <= balance){
			balance-=amount;
			System.out.println("Withdrawal successful. ");
		}else {
			System.out.println("Withdrawal faild");
		}
	}


	@Override
	public String toString() {
		return "BankAccount Details\n Account Number: " + accountNumber + ", Account HolderName: " + accountHolderName + ", Balance: "
				+ balance ;
	}
	
	

}
