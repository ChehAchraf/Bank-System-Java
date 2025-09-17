package model;

public class Versement extends Operation{
	
    private String source;

	
    public Versement(double montant, String source) {
        super(montant);
        this.source = source;
    }


	@Override
    public String getTypeOperation() {
        return "VERSEMENT";
    }


	@Override
	public String getDescription() {
        return String.format("Versement de %.2f DH depuis: %s", montant, source);
    }
    
	public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] VERSEMENT - Montant: %.2f DH - Source: %s - Date: %s",
                numero.toString().substring(0, 8),
                montant,
                source,
                date.toString().replace('T', ' ').substring(0, 19));
    }
	
	
}
