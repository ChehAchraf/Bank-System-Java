package ui;

import java.util.*;
import service.*;
import util.*;
import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;

public class Menu {

    private BanqueService banqueService;
    private Scanner scanner;
    private boolean appIsActive;

    public Menu() {
        this.banqueService = new BanqueService();
        this.scanner = new Scanner(System.in);
        this.appIsActive = true;

    }

    public void startApplication() {
        System.out.println("=======================================");
        System.out.println("       Banque system management        ");
        System.out.println("              Version 1.0              ");
        System.out.println("=======================================");

        while (appIsActive) {
            showMainMenu();
            int choice = readUserChoice();
            handleChoice(choice);

        }

    }

    private void showMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Create an account");
        System.out.println("2. Make a deposit");
        System.out.println("3. Make a withdrawal");
        System.out.println("4. Make a transfer");
        System.out.println("5. Check an account balance");
        System.out.println("6. View an account's transactions");
        System.out.println("7. Display account details");
        System.out.println("8. Display all accounts");
        System.out.println("9. Search for an account");
        System.out.println("10.Get Compte Courant");
        System.out.println("11.Get Compte Epargnes");
        System.out.println("12.Statistiques");
        System.out.println("0. Exit the application");
        System.out.println("=======================");
        System.out.print("Your choice: ");
    }

    private int readUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                createAccount();
                break;
            case 2:
                makeDeposit();
                break;
            case 3:
                makeWithdraw();
                break;
            case 4:
                makeTransfer();
                break;
            case 5:
                checkAccountBalance();
                break;
            case 6:
                showAccountOperation();
                break;
            case 7:
                showAccountDetails();
                break;
            case 8:
                showAllAccounts();
                break;
            case 9:
                searchForAccount();
                break;
            case 10:
                getCompteCourant();
                break;
            case 11:
                getCompteEpargnes();
                break;
            case 12:
                Statistiques();
                break;
            default:
                System.out.println("invalid choice");
        }
    }

    // create account 
    private void createAccount() {
        System.out.println("\n=== Creating an account ===");
        System.out.println("1. Compte Courant");
        System.out.println("2. Compte Épargne");
        System.out.print("account type (1 ou 2): ");

        int accountType = readUserChoice();
        if (accountType == 1) {
            createCompteCourant();
        } else if (accountType == 2) {
            createCompteEpargne();
        } else {
            System.out.println("\nPlease eneter a valid choice");
            return;
        }
    }

    private void createCompteCourant() {
        try {
            System.out.println("Initial sold : ");
            double initialSolde = Double.parseDouble(scanner.nextLine().trim());

            System.out.println("the withdraft : ");
            double withdraft = Double.parseDouble(scanner.nextLine().trim());
            boolean isPositive = ValiditionUtility.ValidateAmount(initialSolde);
            boolean isValid = ValiditionUtility.ValidateAmount(withdraft);
            if (!isPositive || !isValid) {
                return;
            }

            banqueService.createCompteCourant(initialSolde, withdraft);

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void createCompteEpargne() {
        try {
            System.out.println("Initial sold : ");
            double initialSolde = Double.parseDouble(scanner.nextLine().trim());

            System.out.println("Interest rate : ");
            double interestRate = Double.parseDouble(scanner.nextLine().trim());

            boolean isPositivesolde = ValiditionUtility.ValidateAmount(initialSolde);
            boolean isPositiveinterest = ValiditionUtility.ValidateAmount(interestRate);

            if (!isPositiveinterest || !isPositivesolde) {
                return;
            }

            banqueService.createCompteEpargne(initialSolde, interestRate);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void makeDeposit() {
        try {
            System.out.println("Please enter the account code (CPT-XXXXX) : ");
            String codeAccount = scanner.nextLine().trim();
            scanner.nextLine();
            if (codeAccount == null) {
                System.out.println("\n Please provide us a code");
                return;
            }
            System.out.println("Please enter the amount : ");
            double amount = Double.parseDouble(scanner.nextLine().trim());

            System.out.println("Please enter the source : ");
            String source = scanner.nextLine();

            banqueService.makeDeposit(codeAccount, amount, source);
            System.out.println("Deposit successful");
            System.out.println("Amount : " + amount);
            System.out.println("Source : " + source);
            System.out.println("Code Account : " + codeAccount);

        } catch (Exception e) {
            System.out.println("Erorr : " + e.getMessage());
        }
    }

    private void makeWithdraw() {
        String accountCode = askForCode();
        if (!ValiditionUtility.validFormatCode(accountCode)) {
            System.out.println("Please enter a valid format code (CPT-XXXXX)");
        }
        System.err.println("enter Please the amount you want : ");
        double ammount = Double.parseDouble(scanner.nextLine().trim());
        banqueService.makeWithdraw(accountCode, ammount, accountCode);

    }

    private void makeTransfer() {
        try {

            String sourceCode = askForCode();
            if (sourceCode == null) {
                return;
            }

            // Get destination account
            System.out.print("Please enter the destination account code (CPT-XXXXX): ");
            String destinationCode = scanner.nextLine().trim();
            if (!ValiditionUtility.validFormatCode(destinationCode)) {
                System.out.println("Invalid destination account format!");
                return;
            }

            // Get amount
            System.out.print("Enter the transfer amount: ");
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (!ValiditionUtility.ValidateAmount(amount)) {
                System.out.println("Invalid amount!");
                return;
            }

            banqueService.makeTransfer(sourceCode, destinationCode, amount, "transfer");
            System.out.println("Transfer done");

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void checkAccountBalance() {
        try {
            String codeAccount = askForCode();
            double accountBalance = banqueService.checkAccountBalance(codeAccount);
            if (accountBalance == -1) {
                System.out.println("Operation failed");
                return;
            }
            System.out.println("The account balance is : " + accountBalance);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void showAccountOperation() {
        try {
            String codeAccount = askForCode();
            if (codeAccount == null) {
                return;
            }

            banqueService.showOperation(codeAccount);

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void showAccountDetails() {
        try {
            String accountCode = askForCode();
            if (accountCode == null) {
                return;
            }
            banqueService.showAccountDetails(accountCode);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void showAllAccounts() {
        banqueService.showAllAccounts();
    }

    private void searchForAccount() {
        String codeAccount = askForCode();

        if (codeAccount == null) {
            return;
        }

        Compte account = banqueService.sreachForAccount(codeAccount);

        System.out.println("The account was found => code :" + account.getCode() + " solde : " + account.getSolde());

    }

    private void getCompteCourant() {
        try {
            List<CompteCourant> courants = banqueService.getCompteCourant();
            System.out.println("===== coupte courant =====");
            courants.stream()
                    .forEach(p -> System.out.println("Code :" + p.getCode() + " solde : " + p.getSolde()));
            System.out.println("==========================");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void getCompteEpargnes() {
        try {
            List<CompteEpargne> epargnes = banqueService.getCompteEpargnes();
            System.out.println("===== coupte epargnes =====");
            epargnes.stream()
                    .forEach(p -> System.out.println("Code :" + p.getCode() + " solde : " + p.getSolde()));
            System.out.println("==========================");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void Statistiques() {
        System.out.println("\n=== STATISTIQUES ===");
        System.out.println("Number of all the accounts: " + banqueService.getAccountNumber());
        System.out.println("Number of comptes courants: " + banqueService.getCompteCourant().size());
        System.out.println("Number of comptes épargne: " + banqueService.getCompteEpargnes().size());
        System.out.println("===================");
    }

    // method to get codeOperation
    public String askForCode() {
        System.out.println("\nPlease enter the bank account code (CPT-XXXXX) : ");
        String codeAccount = scanner.nextLine().trim();
        if (!ValiditionUtility.validFormatCode(codeAccount)) {
            System.out.println("Please eneter a valid format code (CPT-XXXXX)");
            return null;
        }
        return codeAccount;
    }
}
