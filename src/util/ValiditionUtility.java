package util;

public class ValiditionUtility {
	
	public static boolean ValidateAmount(Object o ){
		if(o instanceof String){
			System.out.println("Please enter a valid number not text");
			return false;
		}
		if((Double)o < 0){
			System.out.println("The amount must be greater than 0");
			return false;
		}
		return true;
	}

	public static boolean valiteWithdrawAmount(double amount,double solde){

		if(amount > solde){
			System.out.println("The amout you wanna withdraw is greater than your solde.");
			return false;
		}
		return true;
	}

	public static boolean respectingDecouvert(double amount, double overDraft, double solde){
		ValidateAmount(amount);
		if(solde - amount >= -overDraft){
			System.out.println("Please you are tryin to withdraw passing the limit.");
			return false;
		}
		return true;
	}

	public static boolean validFormatCode(String code){
		return code != null && code.matches("CPT-\\d{5}");
	}
	
}
