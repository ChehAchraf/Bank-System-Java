package util;
import java.util.UUID;
public class CodeGeneratorUtility {


	
	public static String GenerateOperationCode() {
		String digits = UUID.randomUUID().toString().replaceAll("[^0-9]", ""); 
        String small = digits.substring(0, 6); 
        return small;
	}
	
	public static String GenerateCodeAccount(int counter){
		String code = String.format("CPT-%05d", counter);
		return code;
	}
}
