package utilitaire;

import java.util.Random;

public class CodeGeneratorUtility {
	private static final String PREFIX = "CPT-";
	private static final Random random = new Random();
	
	public static String generateAccountCode() {
		int number = 10000 + random.nextInt(90000);
		return PREFIX + number ; 
	}
}
