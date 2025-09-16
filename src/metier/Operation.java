package metier;

import java.util.UUID;
import java.time.LocalDateTime;


public abstract class Operation {
    protected String code;
    protected LocalDateTime date;
    protected double montant;

    	public Operation(double montant) {
    		
    		this.code = UUID.randomUUID().toString();
    		this.date = LocalDateTime.now();
    		this.montant = montant;
    		
    	}
    
    	public double getMontant() { 
		return montant;
	}
    public LocalDateTime getDate() { 
		return date;
	}
    public String getNumero() { 
		return code; 
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
