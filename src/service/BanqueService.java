package service;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import model.*;
import util.CodeGeneratorUtility;
import util.ValiditionUtility;
import java.util.stream.Collectors;

public class BanqueService {

    private Map<String, Compte> accounts;
    private int accountCounter;

    public BanqueService() {
        this.accounts = new HashMap<>();
        this.accountCounter = 1;
    }

    public CompteCourant createCompteCourant(double initialSolde, double overdraft) {
        try {
            CompteCourant account = new CompteCourant(initialSolde, overdraft);
            String code = account.getCode();
            accounts.put(code, account);
            System.out.println("Account created successfully");
            System.out.println("Code : " + code);
            System.out.println("Initial solde :  : " + initialSolde + "DH");
            System.out.println("Allowed overdraft : " + overdraft);
            return account;
        } catch (Exception e) {
            System.out.println("Error creating you're account courant." + e.getMessage());
            return null;
        }
    }

    public CompteEpargne createCompteEpargne(double initialSolde, double interestRate) {
        try {
            CompteEpargne account = new CompteEpargne(initialSolde, interestRate);
            String code = account.getCode();
            accounts.put(code, account);

            System.out.println("Account epargne created successfully");
            System.out.println("Code : " + code);
            System.out.println("Initial solde :  : " + initialSolde + "DH");
            System.out.println("Interest rate : " + interestRate + "%");
            return account;
        } catch (Exception e) {
            System.out.println("Sorry but we cant create your account now, please try again");
            return null;
        }
    }

    public boolean makeDeposit(String accountCode, double ammount, String source) {
        try {
            if (!ValiditionUtility.validFormatCode(accountCode)) {
                System.out.println("Please enter a valid code");
                return false;
            }
            Compte account = findAccount(accountCode);
            if (account == null) {
                System.out.println("Account " + accountCode + " not found!");
                return false;
            }
            return account.verser(ammount, source);
        } catch (Exception e) {
            System.out.println("error : " + e.getMessage());
            return false;
        }
    }

    public boolean makeWithdraw(String accountCode, double ammount, String detination) {
        try {
            Compte account = findAccount(accountCode);
            if (account == null) {
                System.out.println("No account found");
                return false;
            }
            String operationCode = CodeGeneratorUtility.GenerateOperationCode();
            account.withdraw(operationCode, ammount, detination);
            return true;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }

    public boolean makeTransfer(String codeAccount, String destinationCode, double ammount, String where) {
        try {
            Compte sourceAccount = findAccount(codeAccount);
            Compte destinationAccount = findAccount(destinationCode);

            if (sourceAccount == null) {
                System.out.println(
                        "The account you are trying to transfer money from is not a valid, make sure its exists or check the code account format (CPT-XXXXX)");
                return false;
            } else if (destinationAccount == null) {
                System.out.println(
                        "The account you are trying to transfer money to is not a valid, make sure its exists or check the code account format (CPT-XXXXX)");
                return false;
            }

            sourceAccount.withdraw(where, ammount, where);
            destinationAccount.verser(ammount, where);
            return true;

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }

    public double checkAccountBalance(String codeAccount) {
        try {
            Compte account = findAccount(codeAccount);
            if (account == null) {
                System.out.println("We didn't find account under this code");
                return -1;
            }
            double accountBalance = account.getSolde();
            return accountBalance;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return -1;
        }
    }

    public boolean showOperation(String codeAccount) {
        try {
            Compte account = findAccount(codeAccount);
            if (account == null) {
                System.out.println("We didnt find any account unde this code :" + codeAccount);
                return false;
            }
            account.showOperations();
            return true;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }

    }

    public boolean showAccountDetails(String codeAccount) {
        try {
            Compte account = findAccount(codeAccount);
            if (account == null) {
                System.out.println("Account under this code " + codeAccount + " is not available");
                return false;
            }
            account.showDetails();
            return true;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }

    public void showAllAccounts() {
        System.out.println("\n ==== Acount list ====");
        if (accounts.isEmpty()) {
            System.out.println("No account is available right now!");
        } else {
            accounts.values().forEach(account -> {
                System.out.println("Account code : " + account.getCode() + " Solde : " + account.getSolde());
            });
        }
        System.out.println("====================");
    }

    // method to search for an account
    public Compte findAccount(String accountCode) {
        return accounts.get(accountCode);
    }

    public Compte sreachForAccount(String codeAccount) {
        return accounts.get(codeAccount);
    }

    public int getAccountNumber() {
        return accounts.size();
    }

    public List<CompteCourant> getCompteCourant() {
        return accounts.values().stream()
                .filter(account -> account instanceof CompteCourant)
                .map(account -> (CompteCourant) account)
                .sorted(Comparator.comparingDouble(CompteCourant::getSolde).reversed())
                .collect(Collectors.toList());
    }

    public List<CompteEpargne> getCompteEpargnes() {
        return accounts.values().stream()
                .filter(account -> account instanceof CompteEpargne)
                .map(account -> (CompteEpargne) account)
                .sorted(Comparator.comparingDouble(CompteEpargne::getSolde).reversed())
                .collect(Collectors.toList())
                ;
    }

}
