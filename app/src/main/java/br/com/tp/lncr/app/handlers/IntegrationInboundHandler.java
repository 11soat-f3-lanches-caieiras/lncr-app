package br.com.tp.lncr.app.handlers;

import br.com.tp.lncr.app.commons.utils.ExceptionHandlerUtil;
import br.com.tp.lncr.app.integrations.IntegrationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IntegrationInboundHandler {

    @ExceptionHandler(IntegrationException.class)
    public ResponseEntity<Object> handlerIntegrationException(IntegrationException ex) {
        return ExceptionHandlerUtil.handleException(ex.getMessage(), ex.getCode(), ex);
    }
}
