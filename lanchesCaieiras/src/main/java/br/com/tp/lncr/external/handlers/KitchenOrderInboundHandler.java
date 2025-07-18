package br.com.tp.lncr.external.handlers;

import br.com.tp.lncr.core.commons.exceptions.KitchenOrderException;
import br.com.tp.lncr.external.commons.utils.ExceptionHandlerUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class KitchenOrderInboundHandler {

    @ExceptionHandler(KitchenOrderException.class)
    public ResponseEntity<Object> handleKitchenOrderException(KitchenOrderException ex) {
        return ExceptionHandlerUtil.handleException(ex.getMessage(), ex.getCode(), ex);
    }

}
