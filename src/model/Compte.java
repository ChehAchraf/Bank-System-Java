package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public abstract class Compte {
    protected String code;
    protected double solde;
    protected List<Operation> listeOperations;
    

    public Compte(String code, double soldeInitial) {
        this.code = code;
        this.solde = soldeInitial;
        this.listeOperations = new ArrayList<>();
    }

    public abstract boolean retirer(double montant);
    
    public abstract double calculerInteret();
    
    public abstract void afficherDetails();
    

    public boolean verser(double montant, String source) {
        if (montant <= 0) {
            System.out.println("Erreur: Le montant doit être positif");
            return false;
        }
        
        this.solde += montant;
        Versement versement = new Versement(montant, source);
        this.listeOperations.add(versement);
        
        System.out.println("Versement de " + montant + " effectué avec succès depuis: " + source);
        return true;
    }
    
    public boolean retirer(double montant, String destination) {
        if (montant <= 0) {
            System.out.println("Erreur: Le montant doit être positif");
            return false;
        }
        
        if (retirer(montant)) {
            this.solde -= montant;
            Retrait retrait = new Retrait(montant, destination);
            this.listeOperations.add(retrait);
            
            System.out.println("Retrait de " + montant + " effectué avec succès vers: " + destination);
            return true;
        }
        
        return false;
    }
    
    public double consulterSolde() {
        return this.solde;
    }
    

    public void afficherOperations() {
        System.out.println("\n=== Historique des opérations pour le compte " + this.code + " ===");
        if (listeOperations.isEmpty()) {
            System.out.println("Aucune opération effectuée sur ce compte.");
        } else {
            for (Operation operation : listeOperations) {
                System.out.println(operation);
            }
        }
        System.out.println("===============================================\n");
    }
    
    // Getters et Setters
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public double getSolde() {
        return solde;
    }
    
    public void setSolde(double solde) {
        this.solde = solde;
    }
    
    public List<Operation> getListeOperations() {
        return new ArrayList<>(listeOperations);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return Objects.equals(code, compte.code);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
    
    @Override
    public String toString() {
        return "Compte{" +
                "code='" + code + '\'' +
                ", solde=" + solde +
                ", nombreOperations=" + listeOperations.size() +
                '}';
    }
}
