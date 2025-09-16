package metier;

public class CompteEpargne extends Compte {
	
	private double tauxInteret;
	
	
	public CompteEpargne(String code, double solde, double tauxInteret) {
		super(code, solde);
		this.tauxInteret = tauxInteret;
	}


	@Override
	public void showDetails() {
		return;
		
	}


	@Override
	public double calculerInteret() {
		return solde * tauxInteret / 100;
	}





	


}
