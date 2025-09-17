package ui;
import model.*;
import java.util.*;
import service.BanqueService;
public class Menu {
	private BanqueService banqueService;
    private Scanner scanner;
    private boolean applicationActive;
    
    public Menu() {
        this.banqueService = new BanqueService();
        this.scanner = new Scanner(System.in);
        this.applicationActive = true;
    }
    
    
    public void lancerApplication() {
        System.out.println("==========================================");
        System.out.println("    SYSTÈME DE GESTION DE COMPTES       ");
        System.out.println("           BANCAIRES v1.0                ");
        System.out.println("==========================================");
        
        while (applicationActive) {
            afficherMenuPrincipal();
            int choix = lireChoixUtilisateur();
            traiterChoix(choix);
        }
        
        System.out.println("Merci d'avoir utilisé notre système bancaire!");
        scanner.close();
    }
    

    private void afficherMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Créer un compte");
        System.out.println("2. Effectuer un versement");
        System.out.println("3. Effectuer un retrait");
        System.out.println("4. Effectuer un virement");
        System.out.println("5. Consulter le solde d'un compte");
        System.out.println("6. Consulter les opérations d'un compte");
        System.out.println("7. Afficher les détails d'un compte");
        System.out.println("8. Afficher tous les comptes");
        System.out.println("9. Statistiques");
        System.out.println("0. Quitter l'application");
        System.out.println("=======================");
        System.out.print("Votre choix: ");
    }
    

    private int lireChoixUtilisateur() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    

    private void traiterChoix(int choix) {
        switch (choix) {
            case 1:
                creerCompte();
                break;
            case 2:
                effectuerVersement();
                break;
            case 3:
                effectuerRetrait();
                break;
            case 4:
                effectuerVirement();
                break;
            case 5:
                consulterSolde();
                break;
            case 6:
                consulterOperations();
                break;
            case 7:
                afficherDetailsCompte();
                break;
            case 8:
                afficherTousLesComptes();
                break;
            case 9:
                afficherStatistiques();
                break;
            case 0:
                quitterApplication();
                break;
            default:
                System.out.println("Choix invalide! Veuillez saisir un nombre entre 0 et 9.");
        }
        
        if (choix != 0) {
            System.out.println("\nAppuyez sur Entrée pour continuer...");
            scanner.nextLine();
        }
    }

    
    private void creerCompte() {
        System.out.println("\n=== CRÉATION D'UN COMPTE ===");
        System.out.println("1. Compte Courant");
        System.out.println("2. Compte Épargne");
        System.out.print("Type de compte (1 ou 2): ");
        
        int typeCompte = lireChoixUtilisateur();
        
        if (typeCompte == 1) {
            creerCompteCourant();
        } else if (typeCompte == 2) {
            creerCompteEpargne();
        } else {
            System.out.println("Type de compte invalide!");
        }
    }
    

    private void creerCompteCourant() {
        try {
            System.out.print("Solde initial (DH): ");
            double soldeInitial = Double.parseDouble(scanner.nextLine().trim());
            
            System.out.print("Découvert autorisé (DH): ");
            double decouvert = Double.parseDouble(scanner.nextLine().trim());
            
            if (soldeInitial < 0) {
                System.out.println("Erreur: Le solde initial ne peut pas être négatif!");
                return;
            }
            
            if (decouvert < 0) {
                System.out.println("Erreur: Le découvert ne peut pas être négatif!");
                return;
            }
            
            banqueService.creerCompteCourant(soldeInitial, decouvert);
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Veuillez saisir un montant valide!");
        }
    }
    

    private void creerCompteEpargne() {
        try {
            System.out.print("Solde initial (DH): ");
            double soldeInitial = Double.parseDouble(scanner.nextLine().trim());
            
            System.out.print("Taux d'intérêt (%): ");
            double tauxInteret = Double.parseDouble(scanner.nextLine().trim());
            
            if (soldeInitial < 0) {
                System.out.println("Erreur: Le solde initial ne peut pas être négatif!");
                return;
            }
            
            if (tauxInteret < 0) {
                System.out.println("Erreur: Le taux d'intérêt ne peut pas être négatif!");
                return;
            }
            
            banqueService.creerCompteEpargne(soldeInitial, tauxInteret);
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Veuillez saisir un montant valide!");
        }
    }
    

    private void effectuerVersement() {
        System.out.println("\n=== VERSEMENT ===");
        
        String codeCompte = demanderCodeCompte();
        if (codeCompte == null) return;
        
        try {
            System.out.print("Montant à verser (DH): ");
            double montant = Double.parseDouble(scanner.nextLine().trim());
            
            if (!banqueService.validerMontant(montant)) {
                System.out.println("Erreur: Le montant doit être positif!");
                return;
            }
            
            System.out.print("Source du versement: ");
            String source = scanner.nextLine().trim();
            
            if (source.isEmpty()) {
                source = "Dépôt espèces";
            }
            
            banqueService.effectuerVersement(codeCompte, montant, source);
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Veuillez saisir un montant valide!");
        }
    }
    

    private void effectuerRetrait() {
        System.out.println("\n=== RETRAIT ===");
        
        String codeCompte = demanderCodeCompte();
        if (codeCompte == null) return;
        
        try {
            System.out.print("Montant à retirer (DH): ");
            double montant = Double.parseDouble(scanner.nextLine().trim());
            
            if (!banqueService.validerMontant(montant)) {
                System.out.println("Erreur: Le montant doit être positif!");
                return;
            }
            
            System.out.print("Destination du retrait: ");
            String destination = scanner.nextLine().trim();
            
            if (destination.isEmpty()) {
                destination = "Distributeur ATM";
            }
            
            banqueService.effectuerRetrait(codeCompte, montant, destination);
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Veuillez saisir un montant valide!");
        }
    }
    

    private void effectuerVirement() {
        System.out.println("\n=== VIREMENT ===");
        
        System.out.print("Code du compte source: ");
        String codeSource = scanner.nextLine().trim();
        
        if (!banqueService.validerFormatCode(codeSource)) {
            System.out.println("Erreur: Format de code invalide! Utilisez le format CPT-XXXXX");
            return;
        }
        
        System.out.print("Code du compte destination: ");
        String codeDestination = scanner.nextLine().trim();
        
        if (!banqueService.validerFormatCode(codeDestination)) {
            System.out.println("Erreur: Format de code invalide! Utilisez le format CPT-XXXXX");
            return;
        }
        
        try {
            System.out.print("Montant à virer (DH): ");
            double montant = Double.parseDouble(scanner.nextLine().trim());
            
            if (!banqueService.validerMontant(montant)) {
                System.out.println("Erreur: Le montant doit être positif!");
                return;
            }
            
            banqueService.effectuerVirement(codeSource, codeDestination, montant);
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Veuillez saisir un montant valide!");
        }
    }
    

    private void consulterSolde() {
        System.out.println("\n=== CONSULTATION DU SOLDE ===");
        
        String codeCompte = demanderCodeCompte();
        if (codeCompte == null) return;
        
        double solde = banqueService.consulterSolde(codeCompte);
        if (solde >= 0) {
            System.out.println("Solde du compte " + codeCompte + ": " + solde + " DH");
        }
    }
    

    private void consulterOperations() {
        System.out.println("\n=== CONSULTATION DES OPÉRATIONS ===");
        
        String codeCompte = demanderCodeCompte();
        if (codeCompte == null) return;
        
        banqueService.consulterOperations(codeCompte);
    }
    

    private void afficherDetailsCompte() {
        System.out.println("\n=== DÉTAILS DU COMPTE ===");
        
        String codeCompte = demanderCodeCompte();
        if (codeCompte == null) return;
        
        banqueService.afficherDetailsCompte(codeCompte);
    }

    private void afficherTousLesComptes() {
        banqueService.afficherTousLesComptes();
    }
    

    private void afficherStatistiques() {
        System.out.println("\n=== STATISTIQUES ===");
        System.out.println("Nombre total de comptes: " + banqueService.getNombreComptes());
        System.out.println("Nombre de comptes courants: " + banqueService.getComptesCourants().size());
        System.out.println("Nombre de comptes épargne: " + banqueService.getComptesEpargne().size());
        System.out.println("===================");
    }
    

    private String demanderCodeCompte() {
        System.out.print("Code du compte (format CPT-XXXXX): ");
        String code = scanner.nextLine().trim();
        
        if (!banqueService.validerFormatCode(code)) {
            System.out.println("Erreur: Format de code invalide! Utilisez le format CPT-XXXXX");
            return null;
        }
        
        return code;
    }
    

    private void quitterApplication() {
        System.out.println("\nÊtes-vous sûr de vouloir quitter? (o/n)");
        String reponse = scanner.nextLine().trim().toLowerCase();
        
        if (reponse.equals("o") || reponse.equals("oui") || reponse.equals("y") || reponse.equals("yes")) {
            applicationActive = false;
        }
    }
}
