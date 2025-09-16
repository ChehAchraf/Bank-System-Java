package metier;

public class CompteEpargne extends Compte {
	
	private double tauxInteret;
	
	
	public CompteEpargne(String code, double solde, double tauxInteret) {
		super(code, solde);
		this.tauxInteret = tauxInteret;
	}


	@Override
	public void showDetails() {
		System.out.println("Compte Épargne: " + code + ", Solde: " + solde + ", Taux d'intérêt: " + tauxInteret + "%");
	}


	@Override
	public double calculerInteret() {
		return solde * tauxInteret / 100;
	}

    @Override
    public boolean retirer(double montant) {
        try {
        		if (montant > solde) {
        			throw new IllegalArgumentException("Retrait impossible: solde insuffisant");
        		}
        		solde -= montant;
        		Retrait r = new Retrait(montant,"Retrait épargne");
        		listOperations.add(r);
        		System.out.println("Retrait effectué. Nouveau solde: " + solde);
        		return true;
        }catch(IllegalArgumentException e) {
        		System.out.println(e.getMessage());
        		return false;
        }
    }


}
