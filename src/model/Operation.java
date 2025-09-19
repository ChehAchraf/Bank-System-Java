package model;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Operation {
    protected UUID id;
    protected LocalDateTime date;
    protected double ammount;

    public Operation(double ammount){
    	this.id = UUID.randomUUID();
    	this.date = LocalDateTime.now();
    	this.ammount = ammount;
    }

}
