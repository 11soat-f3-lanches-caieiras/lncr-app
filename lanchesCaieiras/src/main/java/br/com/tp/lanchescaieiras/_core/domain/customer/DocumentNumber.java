package br.com.tp.lanchescaieiras._core.domain.customer;

public interface DocumentNumber {
    String getValue();

    boolean documentNumberIsValid(String value);
}
