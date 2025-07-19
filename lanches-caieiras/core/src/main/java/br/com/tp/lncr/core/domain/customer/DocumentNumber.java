package br.com.tp.lncr.core.domain.customer;

public interface DocumentNumber {
    String getValue();

    boolean documentNumberIsValid(String value);
}
