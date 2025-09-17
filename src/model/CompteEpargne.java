package model;

public class CompteEpargne extends Compte {
    private double tauxInteret;
    
    public CompteEpargne(String code, double soldeInitial, double tauxInteret) {
        super(code, soldeInitial);
        this.tauxInteret = tauxInteret;
    }
    

    @Override
    public boolean retirer(double montant) {
        if (montant <= 0) {
            System.out.println("Erreur: Le montant doit être positif");
            return false;
        }
        
        if (this.solde < montant) {
            System.out.println("Erreur: Retrait impossible. Solde insuffisant.");
            System.out.println("Solde actuel: " + this.solde);
            System.out.println("Montant demandé: " + montant);
            System.out.println("Déficit: " + (montant - this.solde));
            return false;
        }
        
        return true;
    }
    

    @Override
    public double calculerInteret() {
        return this.solde * (this.tauxInteret / 100.0);
    }

    @Override
    public void afficherDetails() {
        System.out.println("\n=== Détails du Compte Épargne ===");
        System.out.println("Code: " + this.code);
        System.out.println("Type: Compte Épargne");
        System.out.println("Solde: " + this.solde + " DH");
        System.out.println("Taux d'intérêt: " + this.tauxInteret + "%");
        System.out.println("Intérêts calculés: " + calculerInteret() + " DH");
        System.out.println("Nombre d'opérations: " + this.listeOperations.size());
        System.out.println("===============================\n");
    }
    

    public void capitaliserInterets() {
        double interets = calculerInteret();
        if (interets > 0) {
            this.solde += interets;
            System.out.println("Intérêts de " + interets + " DH capitalisés sur le compte " + this.code);
        }
    }
    
    // Getter et Setter pour tauxInteret
    public double getTauxInteret() {
        return tauxInteret;
    }
    
    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
    
    @Override
    public String toString() {
        return "CompteEpargne{" +
                "code='" + code + '\'' +
                ", solde=" + solde +
                ", tauxInteret=" + tauxInteret +
                ", nombreOperations=" + listeOperations.size() +
                '}';
    }
}
