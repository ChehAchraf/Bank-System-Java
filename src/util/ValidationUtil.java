package util;

public class ValidationUtil {
	
    public static boolean validerFormatCodeCompte(String code) {
        if (code == null || code.trim().isEmpty()) {
            return false;
        }
        
        return code.trim().matches("CPT-\\d{5}");
    }
    
    public static boolean validerMontant(double montant) {
        return montant > 0;
    }
    
    public static boolean validerMontantNonNegatif(double montant) {
        return montant >= 0;
    }
    
    public static boolean validerTauxInteret(double taux) {
        return taux >= 0 && taux <= 100;
    }
    
    public static boolean validerChaineNonVide(String chaine) {
        return chaine != null && !chaine.trim().isEmpty();
    }
    
    public static String nettoyerChaine(String chaine) {
        if (chaine == null) {
            return null;
        }
        return chaine.trim();
    }
    
    public static String formaterMontant(double montant) {
        return String.format("%.2f", montant);
    }
    
    public static boolean estNombreValide(String chaine) {
        if (chaine == null || chaine.trim().isEmpty()) {
            return false;
        }
        
        try {
            Double.parseDouble(chaine.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static double convertirEnDouble(String chaine) {
        try {
            return Double.parseDouble(chaine.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
