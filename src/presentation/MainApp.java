package presentation;
import java.util.Scanner;

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
                    System.out.println("creating account ...");
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
