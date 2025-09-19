package presentation;
import ui.Menu;
public class MainApp {

    public static void main(String [] args){
        try{
            Menu menu = new Menu();
            menu.startApplication();
        }catch(Exception e){
            System.out.println("Error openning the app");
            System.exit(1);
        }
    }
}
