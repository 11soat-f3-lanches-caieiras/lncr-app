package br.com.tp.lanchescaieiras.customer.domain.entities;

public interface DocumentNumber {
    String getValue();
    boolean documentNumberIsValid(String value);
}
