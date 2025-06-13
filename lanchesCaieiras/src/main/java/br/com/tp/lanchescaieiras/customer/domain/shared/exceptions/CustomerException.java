package br.com.tp.lanchescaieiras.customer.domain.shared.exceptions;

public class CustomerException extends RuntimeException {
    private Integer code;

    public CustomerException(String message, Integer code) {

        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

