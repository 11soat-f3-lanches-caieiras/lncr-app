package br.com.tp.lanchescaieiras.customerorder.infraestructure.exceptions;

public class CustomerOrderException extends RuntimeException {
    private Integer code;

    public CustomerOrderException(String message, Integer code) {

        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

