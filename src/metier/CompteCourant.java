package metier;
import java.util.Scanner;

public class CompteCourant extends Compte  {
	
	private double decouvert;
	
	public CompteCourant(String code, double solde,double decouvert) {
		super(code, solde);
		this.decouvert = decouvert;
		if(solde < -decouvert ) {
			throw new IllegalArgumentException("Solde initial inférieur au découvert autorisé");
		}
	}
	
	
	public double getDecouvert() {
		return decouvert;
	}



	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}





	@Override
	public void showDetails() {
        System.out.println("Compte Courant: " + code + ", Solde: " + solde + ", Découvert autorisé: " + decouvert);
	}

	@Override
	public boolean retirer(double montant) {
		String destination;
		Scanner sc = new Scanner(System.in);
		try {
			if(solde - montant < -decouvert) {
				throw new IllegalArgumentException("Retrait impossible: solde insuffisant par rapport au découvert");
			}
			
			solde -= montant;
			System.out.print("\nPlease enter the destination :");
			destination = sc.nextLine();
			sc.nextLine();
			Retrait r = new Retrait(montant,destination);
			listOperations.add(r);
			System.out.println("Retrait effectué. Nouveau solde: " + solde);
			return true;
			
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public double calculerInteret() {
		return 0;
	}
	

}
