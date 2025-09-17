package metier;



import java.util.ArrayList;
import java.util.List;

public abstract class Compte {
    protected String code;
    protected double solde;
    protected List<Operation> listOperations;
    
    public Compte(String code, double solde) {
    		this.code = code;
    		this.solde = solde;
    		this.listOperations = new ArrayList<>();
    }
    
    public String getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }
    
    public void verser(double montant, String source) {
    		this.solde += montant;
    		Versement v = new Versement(montant , source);
    		listOperations.add(v);
    }
    
    public void verserToAnotherAccount(String code, double montant) {
    		if(this.solde < montant) {
    			throw new IllegalArgumentException("Your sold is insufisant");
    		}
    }
    
    
    
    public List<Operation> getListOperations() {
        return listOperations;
    }

    public abstract void showDetails();
    public abstract boolean retirer(double montant);
    public abstract double calculerInteret();

}
