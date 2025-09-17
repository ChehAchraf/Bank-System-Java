package model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public abstract class Operation {
    protected UUID numero;
    protected LocalDateTime date;
    protected double montant;
    
    public Operation(double montant) {
        this.numero = UUID.randomUUID();
        this.date = LocalDateTime.now();
        this.montant = montant;
    }
    
    public abstract String getTypeOperation();
    
    public abstract String getDescription();

    public UUID getNumero() {
        return numero;
    }
    
    public LocalDateTime getDate() {
        return date;
    }
    
    public double getMontant() {
        return montant;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(numero, operation.numero);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - Montant: %.2f DH - Date: %s",
                numero.toString().substring(0, 8),
                getTypeOperation(),
                montant,
                date.toString().replace('T', ' ').substring(0, 19));
    }
}
