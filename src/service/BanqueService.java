package service;
import model.*;
import java.util.*;
import java.util.stream.Collectors;


public class BanqueService {
	private Map<String, Compte> comptes;
    private int compteurComptes;
    
    public BanqueService() {
        this.comptes = new HashMap<>();
        this.compteurComptes = 1;
    }
    
    public CompteCourant creerCompteCourant(double soldeInitial, double decouvert) {
        try {
            String code = genererCodeCompte();
            CompteCourant compte = new CompteCourant(code, soldeInitial, decouvert);
            comptes.put(code, compte);
            
            System.out.println("Compte courant créé avec succès!");
            System.out.println("Code: " + code);
            System.out.println("Solde initial: " + soldeInitial + " DH");
            System.out.println("Découvert autorisé: " + decouvert + " DH");
            
            return compte;
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du compte courant: " + e.getMessage());
            return null;
        }
    }
    
    public CompteEpargne creerCompteEpargne(double soldeInitial, double tauxInteret) {
        try {
            String code = genererCodeCompte();
            CompteEpargne compte = new CompteEpargne(code, soldeInitial, tauxInteret);
            comptes.put(code, compte);
            
            System.out.println("Compte épargne créé avec succès!");
            System.out.println("Code: " + code);
            System.out.println("Solde initial: " + soldeInitial + " DH");
            System.out.println("Taux d'intérêt: " + tauxInteret + "%");
            
            return compte;
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du compte épargne: " + e.getMessage());
            return null;
        }
    }
    
    public boolean effectuerVersement(String codeCompte, double montant, String source) {
        try {
            Compte compte = rechercherCompte(codeCompte);
            if (compte == null) {
                System.out.println("Compte non trouvé: " + codeCompte);
                return false;
            }
            
            return compte.verser(montant, source);
        } catch (Exception e) {
            System.out.println("Erreur lors du versement: " + e.getMessage());
            return false;
        }
    }
    public boolean effectuerRetrait(String codeCompte, double montant, String destination) {
        try {
            Compte compte = rechercherCompte(codeCompte);
            if (compte == null) {
                System.out.println("Compte non trouvé: " + codeCompte);
                return false;
            }
            
            return compte.retirer(montant, destination);
        } catch (Exception e) {
            System.out.println("Erreur lors du retrait: " + e.getMessage());
            return false;
        }
    }
    
    public boolean effectuerVirement(String codeCompteSource, String codeCompteDestination, double montant) {
        try {
            Compte compteSource = rechercherCompte(codeCompteSource);
            Compte compteDestination = rechercherCompte(codeCompteDestination);
            
            if (compteSource == null) {
                System.out.println("Compte source non trouvé: " + codeCompteSource);
                return false;
            }
            
            if (compteDestination == null) {
                System.out.println("Compte destination non trouvé: " + codeCompteDestination);
                return false;
            }
            
            if (compteSource.equals(compteDestination)) {
                System.out.println("Erreur: Impossible de virer vers le même compte");
                return false;
            }
            
            // Effectuer le retrait du compte source
            if (compteSource.retirer(montant, "Virement vers " + codeCompteDestination)) {
                // Effectuer le versement sur le compte destination
                if (compteDestination.verser(montant, "Virement depuis " + codeCompteSource)) {
                    System.out.println("Virement de " + montant + " DH effectué avec succès");
                    System.out.println("De: " + codeCompteSource + " vers: " + codeCompteDestination);
                    return true;
                } else {
                    // Annuler le retrait si le versement échoue
                    compteSource.verser(montant, "Annulation virement");
                    System.out.println("Erreur lors du virement: versement échoué");
                    return false;
                }
            } else {
                System.out.println("Erreur lors du virement: retrait impossible");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du virement: " + e.getMessage());
            return false;
        }
    }
    
    public double consulterSolde(String codeCompte) {
        try {
            Compte compte = rechercherCompte(codeCompte);
            if (compte == null) {
                System.out.println("Compte non trouvé: " + codeCompte);
                return -1;
            }
            
            return compte.consulterSolde();
        } catch (Exception e) {
            System.out.println("Erreur lors de la consultation du solde: " + e.getMessage());
            return -1;
        }
    }
    
    public void consulterOperations(String codeCompte) {
        try {
            Compte compte = rechercherCompte(codeCompte);
            if (compte == null) {
                System.out.println("Compte non trouvé: " + codeCompte);
                return;
            }
            
            compte.afficherOperations();
        } catch (Exception e) {
            System.out.println("Erreur lors de la consultation des opérations: " + e.getMessage());
        }
    }
    
    public void afficherDetailsCompte(String codeCompte) {
        try {
            Compte compte = rechercherCompte(codeCompte);
            if (compte == null) {
                System.out.println("Compte non trouvé: " + codeCompte);
                return;
            }
            
            compte.afficherDetails();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage des détails: " + e.getMessage());
        }
    }
    
    public void afficherTousLesComptes() {
        System.out.println("\n=== Liste de tous les comptes ===");
        if (comptes.isEmpty()) {
            System.out.println("Aucun compte n'a été créé.");
        } else {
            comptes.values().forEach(compte -> {
                System.out.println(compte);
            });
        }
        System.out.println("================================\n");
    }
    
    public Compte rechercherCompte(String code) {
        return comptes.get(code);
    }
    
    private String genererCodeCompte() {
        String code = String.format("CPT-%05d", compteurComptes);
        compteurComptes++;
        return code;
    }
    
    public boolean validerFormatCode(String code) {
        return code != null && code.matches("CPT-\\d{5}");
    }
    
    public boolean validerMontant(double montant) {
        return montant > 0;
    }
    
    public int getNombreComptes() {
        return comptes.size();
    }
    
    public List<CompteCourant> getComptesCourants() {
        return comptes.values().stream()
                .filter(compte -> compte instanceof CompteCourant)
                .map(compte -> (CompteCourant) compte)
                .collect(Collectors.toList());
    }
    
    public List<CompteEpargne> getComptesEpargne() {
        return comptes.values().stream()
                .filter(compte -> compte instanceof CompteEpargne)
                .map(compte -> (CompteEpargne) compte)
                .collect(Collectors.toList());
    }
}
