package br.com.tp.lanchescaieiras.fooditem.domain.exceptions;

public class FoodItemException extends RuntimeException {

    private final int code;

    public FoodItemException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
