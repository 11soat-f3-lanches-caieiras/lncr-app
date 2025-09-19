package br.com.tp.lncr.app.handlers;

import br.com.tp.lncr.app.commons.utils.ExceptionHandlerUtil;
import br.com.tp.lncr.core.commons.exceptions.OauthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SuppressWarnings("unused")
@RestControllerAdvice
public class OauhInboundHandler {

    @ExceptionHandler(OauthException.class)
    public ResponseEntity<Object> handleOauthException(OauthException ex) {
        return ExceptionHandlerUtil.handleException(ex.getMessage(), ex.getCode(), ex);
    }
}
