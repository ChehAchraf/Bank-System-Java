package presentation;
import java.util.Scanner;
import metier.*;
import utilitaire.CodeGeneratorUtility;


public class MainApp {

	static final String GREEN = "\u001B[32m";
    static final String RESET = "\u001B[0m";
    static final String RED = "\u001B[31m";
    static CompteCourant userAccount = null;
    static CompteEpargne epargneAccount = null;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while(choice != 2){
        // check if user already have an account
            if(userAccount == null) {
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
	        // if he don't have an account
            }else {
            		System.out.println("--------- Bank System ---------");
                System.out.println("1 - Versement ");
                System.out.println("2 - retrait ");
                System.out.print("\n=> nter you're choice : ");
                choice = sc.nextInt();
	                switch (choice) {
		                case 1:
		                		VersementAccountMenu();
		                		break;
		                case 2:
		                    System.out.println("exit");
		                    break;
		                default:
		                    throw new AssertionError();
	                }
            }
        }
    }
    
    public static void CreateAccountMenu() {
    		int createAccountChoice = 0;
    		double soldeAccount;
    		double decouvert;
    		double interet;
    		String code;
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
        					code = CodeGeneratorUtility.generateAccountCode();
        					userAccount = new CompteCourant(code, soldeAccount, decouvert);
        					userAccount.showDetails();
        					System.out.println(GREEN + "Account created seccussfully ! " + RESET);
        					return;
    					}catch(IllegalArgumentException e) {
    						System.out.println(RED + e.getMessage() + RESET);
    					}
    				break;
    			case 2 : 
    					try {
    						System.out.println("Please add the solde you wanna have : ");
    						soldeAccount = sc.nextInt();
    						System.out.println("Please add the interet you can handle");
    						interet = sc.nextDouble();
    						if(soldeAccount < 500) {
    							while(interet > 5) {
   	    							System.out.println("Please the interet cant't be greater than 5% : ");
    	    							interet = sc.nextDouble();
    							}
    						}else {
    							while(interet < 5) {
   	    							System.out.println("Please the interet cant't be smaller than 5% : ");
    	    							interet = sc.nextDouble();
    							}
    						}
    						code = CodeGeneratorUtility.generateAccountCode();
    						epargneAccount = new CompteEpargne(code, soldeAccount, interet);
							if(epargneAccount != null){
								System.out.println("The account has beent created successfully");
								epargneAccount.showDetails();
							}
    					}catch(IllegalArgumentException e) {
    						System.out.println(RED + e.getMessage() + RESET);
    					}
    				break;
    			}
    			createAccountChoice = sc.nextInt();
    		}
    }
    
    public static void VersementAccountMenu() {
    		Scanner sc = new Scanner(System.in);
    		try {
    			System.out.print("\n please enter the amount : ");
    			double montant = sc.nextDouble();
    			sc.nextLine();
    			System.out.print("\n Please enter the source : ");
    			String source = sc.nextLine();
    			userAccount.verser(montant, source);
    			System.out.println(GREEN + "\nVersement done ! Be happy now ❤😁\n" + RESET);
    			System.out.println("your new balance is : " + userAccount.getSolde());
    			
    		}catch(IllegalArgumentException e) {
    			System.out.println(RED + e.getMessage() + RESET);
    		}
    }
    
    
}
