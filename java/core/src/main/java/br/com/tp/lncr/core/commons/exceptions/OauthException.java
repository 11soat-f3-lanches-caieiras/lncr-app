package br.com.tp.lncr.core.commons.exceptions;

public class OauthException extends RuntimeException {
    private final Integer code;

    public OauthException(String message, Integer code) {

        super(message);
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }
}
