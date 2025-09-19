package model;

public class CompteEpargne extends Compte {

    private double interestRate;

    public CompteEpargne(double solde, double interestRate) {
        super(solde);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(String operationCode, double amount, String source) {
        if (solde < amount) {
            System.out.println("you wanna withdraw more than you have");
            return;
        }
        solde -= amount;
        Withdrawal withdrawl = new Withdrawal(operationCode, amount, source);
        this.operationList.add(withdrawl);
        showDetails();
    }

    @Override
    public double calculateInterest() {
        if (this.solde >= 1000) {
            return this.solde * 0.07;
        } else if (this.solde > 500) {
            return this.solde * 0.05;
        } else {
            return this.solde * 0.03;
        }
    }




}
