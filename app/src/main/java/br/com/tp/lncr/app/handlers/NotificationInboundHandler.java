package br.com.tp.lncr.app.handlers;

import br.com.tp.lncr.app.commons.utils.ExceptionHandlerUtil;
import br.com.tp.lncr.core.commons.exceptions.NotificationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotificationInboundHandler {

    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<Object> handlerNotificationException(NotificationException ex) {
        return ExceptionHandlerUtil.handleException(ex.getMessage(), ex.getCode(), ex);
    }
}


