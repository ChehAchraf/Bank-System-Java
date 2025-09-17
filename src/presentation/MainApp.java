package presentation;
import ui.Menu;

public class MainApp {
    public static void main(String[] args) {
        try {
            
            Menu menu = new Menu();
            menu.lancerApplication();
        } catch (Exception e) {
            System.err.println("Erreur fatale dans l'application: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
