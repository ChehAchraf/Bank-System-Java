package model;

public class Deposit extends Operation {
    private String source;

    public Deposit(double ammount, String source) {
        super(ammount);
        this.source = source;
    }

    @Override
    public String toString() {
        return "Deposit of " + ammount + " from " + source + " on " + date;
    }
}

    
