package br.com.tp.lanchescaieiras._external.handlers;

import br.com.tp.lanchescaieiras._core.commons.exceptions.NotificationException;
import br.com.tp.lanchescaieiras._external.commons.utils.ExceptionHandlerUtil;
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


