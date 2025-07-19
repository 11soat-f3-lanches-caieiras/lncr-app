package br.com.tp.lncr.core.commons.exceptions;

public class CustomerOrderException extends RuntimeException {
    private final Integer code;

    public CustomerOrderException(String message, Integer code) {

        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

