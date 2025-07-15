package br.com.tp.lanchescaieiras._core.commons.exceptions;

public class KitchenOrderException extends RuntimeException {
    private final Integer code;

    public KitchenOrderException(String message, Integer code) {

        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

