package br.com.tp.lncr.external.handlers;

import br.com.tp.lncr.external.commons.utils.ExceptionHandlerUtil;
import br.com.tp.lncr.external.integrations.IntegrationException;
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
