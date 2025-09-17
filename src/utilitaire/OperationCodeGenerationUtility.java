package utilitaire;
import java.util.Random;


public class OperationCodeGenerationUtility {
	private String CODE ;
	static private Random random = new Random();
	
	public static int generateCode(){
		int number = 100 + random.nextInt(1000);
		return number;
	}
}
