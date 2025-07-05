package br.com.tp.lanchescaieiras._core.domain.exceptions;

public class PaymentException extends RuntimeException {

    private final int code;

    public PaymentException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
