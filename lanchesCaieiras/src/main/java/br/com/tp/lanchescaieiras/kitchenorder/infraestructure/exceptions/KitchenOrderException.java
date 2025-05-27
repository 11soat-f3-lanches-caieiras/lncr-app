package br.com.tp.lanchescaieiras.kitchenorder.infraestructure.exceptions;

public class KitchenOrderException extends RuntimeException {
    private Integer code;

    public KitchenOrderException(String message, Integer code) {

        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

