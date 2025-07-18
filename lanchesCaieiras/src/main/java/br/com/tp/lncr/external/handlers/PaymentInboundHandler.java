package br.com.tp.lncr.external.handlers;

import br.com.tp.lncr.core.commons.exceptions.PaymentException;
import br.com.tp.lncr.external.commons.utils.ExceptionHandlerUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentInboundHandler {

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<Object> handleKitchenOrderException(PaymentException ex) {
        return ExceptionHandlerUtil.handleException(ex.getMessage(), ex.getCode(), ex);
    }
}
