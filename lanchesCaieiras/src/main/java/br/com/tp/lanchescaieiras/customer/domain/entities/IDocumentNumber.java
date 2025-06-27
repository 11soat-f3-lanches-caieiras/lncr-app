package br.com.tp.lanchescaieiras.customer.domain.entities;

public interface IDocumentNumber {
    String getValue();
    boolean documentNumberIsValid(String value);
}
