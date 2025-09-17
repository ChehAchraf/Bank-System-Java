package model;

public class CompteCourant extends Compte {
    private double decouvert;
    

    public CompteCourant(String code, double soldeInitial, double decouvert) {
        super(code, soldeInitial);
        this.decouvert = decouvert;
    }
    

    @Override
    public boolean retirer(double montant) {
        if (montant <= 0) {
            System.out.println("Erreur: Le montant doit être positif");
            return false;
        }
        
        double soldeApresRetrait = this.solde - montant;
        double limiteMinimale = -this.decouvert;
        
        if (soldeApresRetrait < limiteMinimale) {
            System.out.println("Erreur: Retrait impossible. Solde insuffisant.");
            System.out.println("Solde actuel: " + this.solde);
            System.out.println("Découvert autorisé: " + this.decouvert);
            System.out.println("Limite minimale: " + limiteMinimale);
            System.out.println("Montant demandé: " + montant);
            return false;
        }
        
        return true;
    }
    

    @Override
    public double calculerInteret() {
        return 0.0;
    }
    

    @Override
    public void afficherDetails() {
        System.out.println("\n=== Détails du Compte Courant ===");
        System.out.println("Code: " + this.code);
        System.out.println("Type: Compte Courant");
        System.out.println("Solde: " + this.solde + " DH");
        System.out.println("Découvert autorisé: " + this.decouvert + " DH");
        System.out.println("Limite minimale: " + (-this.decouvert) + " DH");
        System.out.println("Intérêts: " + calculerInteret() + " DH (aucun intérêt)");
        System.out.println("Nombre d'opérations: " + this.listeOperations.size());
        System.out.println("===============================\n");
    }
    
    // Getter et Setter pour découvert
    public double getDecouvert() {
        return decouvert;
    }
    
    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
    
    @Override
    public String toString() {
        return "CompteCourant{" +
                "code='" + code + '\'' +
                ", solde=" + solde +
                ", decouvert=" + decouvert +
                ", nombreOperations=" + listeOperations.size() +
                '}';
    }
}