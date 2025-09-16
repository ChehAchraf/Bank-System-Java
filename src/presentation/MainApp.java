package presentation;
import java.util.Scanner;
import metier.*;

public class MainApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while(choice != 2){
            System.out.println("--------- Bank System ---------");
            System.out.println("1 - Create an account ");
            System.out.println("2 - exit from the app ");
            System.out.print("\n=> nter you're choice : ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                	CreateAccountMenu();
                
                		break;
                case 2:
                    System.out.println("exit");
                    break;
                default:
                    throw new AssertionError();
            }

        }
    }
    
    public static void CreateAccountMenu() {
    		int createAccountChoice = 0;
    		double soldeAccount;
    		double decouvert;
    		Scanner sc = new Scanner(System.in);
    		while(createAccountChoice != 3) {
    			System.out.println("1 - Compte Courant");
    			System.out.println("2 - Compte Epargne");
    			System.out.println("3 - return back ");
    			System.out.print("\n=> - enter your choice :\n");
    			switch(createAccountChoice) {
    			case 1:
    					try {
    						System.out.println("Please add the sold you wanna have : ");
        					soldeAccount = sc.nextInt();
        					System.out.println("Please add the decouvert you wanna have : ");
        					decouvert = sc.nextInt();
        					CompteCourant newAccount = new CompteCourant(null, soldeAccount, decouvert);
        					System.out.println("Account created seccussfully ! ");
    					}catch(IllegalArgumentException e) {
    						System.out.println(e.getMessage());
    					}
    				break;
    			}
    			createAccountChoice = sc.nextInt();
    		}
    }
}
