package br.com.tp.lncr.core.commons.exceptions;

public class NotificationException extends RuntimeException {
    private final Integer code;

    public NotificationException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
