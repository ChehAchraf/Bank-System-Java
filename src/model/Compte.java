package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Compte {

	private static int counter = 1;
	protected String code;
	protected double solde;
	protected List<Operation> operationList;
	
	public Compte(double solde) {
		this.code = String.format("CPT-%05d", counter++);
		this.solde = solde;
		this.operationList = new ArrayList<>();
	}

	public abstract void withdraw(String operatorCode, double amount, String source);
	public abstract double calculateInterest();

	public void showDetails(){
		System.out.println("Acount code : " + this.code + " account solde : " + this.solde );
	};
	
	public boolean verser(double amount, String source) {
		this.solde += amount;
		Deposit deposit = new Deposit(amount, source);
		this.operationList.add(deposit);
		return true;
	}

	public void showOperations() {
		System.out.println("=== history operation for the accoun " + this.code + "===");
		if (operationList.isEmpty()) {
			System.out.println("No operation yet for this account");
		} else {
			operationList.stream().forEach(p -> System.err.println(p));
		}
	}

	public String getCode() {
		return code;
	}

	public double getSolde() {
		return solde;
	}


	
}