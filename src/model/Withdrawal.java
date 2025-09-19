package model;

public class Withdrawal extends Operation{
	
    private String destinantion;

    public Withdrawal(String codeOperation, double ammount, String destinantion) {
        super(ammount);
        this.destinantion = destinantion;
    }


    @Override
    public String toString() {
        return "Withdrawal of " + ammount + " tp " + destinantion + " on " + date;
    }


}
