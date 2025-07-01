package br.com.tp.lanchescaieiras.customer.domain.exceptions;

public class CustomerException extends RuntimeException {
    private final Integer code;

    public CustomerException(String message, Integer code) {

        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

