package model;

public class Retrait extends Operation{
	private String destination;
	
    public Retrait(double montant, String destination) {
        super(montant);
        this.destination = destination;
    }

	@Override
	public String getTypeOperation() {
		return "RETRAIT";
	}

	@Override
    public String getDescription() {
        return String.format("Retrait de %.2f DH vers: %s", montant, destination);
    }
	
	public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] RETRAIT - Montant: %.2f DH - Destination: %s - Date: %s",
                numero.toString().substring(0, 8),
                montant,
                destination,
                date.toString().replace('T', ' ').substring(0, 19));
    }
	
}
