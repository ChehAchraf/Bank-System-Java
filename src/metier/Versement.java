package metier;

public class Versement extends Operation {

	private String source;
	
	public Versement(double montant, String source) {
		super(montant);
		this.source = source;
		
	}
	
	public String getSource() {
		return source;
	}
	
	public double getMontant() {
		return montant;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	
}
