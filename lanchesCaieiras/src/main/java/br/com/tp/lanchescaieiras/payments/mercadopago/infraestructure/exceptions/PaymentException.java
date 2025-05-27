package br.com.tp.lanchescaieiras.payments.mercadopago.infraestructure.exceptions;

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
