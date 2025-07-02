package br.com.tp.lanchescaieiras.customer.domain;

public interface DocumentNumber {
    String getValue();
    boolean documentNumberIsValid(String value);
}
