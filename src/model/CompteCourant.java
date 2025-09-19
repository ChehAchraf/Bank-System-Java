package model;
import model.*;

    public class CompteCourant extends Compte {
        private double overdraft;

        public CompteCourant(double solde, double overdraft) {
            super(solde);
            this.overdraft = overdraft;
        }

        

    @Override
    public void withdraw(String operatorCode, double amount, String source) {
        double newSolde = this.solde - amount;
        if (newSolde < -overdraft) {
            System.out.println("You can't withdraw: overdraft limit exceeded.");
            return;
        }
        this.solde = newSolde;
        Withdrawal withdrawl = new Withdrawal(operatorCode, amount, source);
        this.operationList.add(withdrawl);
        showDetails();
    }

    @Override
    public double calculateInterest() {
        return 0;
    }

}
