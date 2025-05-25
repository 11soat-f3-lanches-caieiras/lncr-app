package br.com.tp.lanchescaieiras.payments.mercadopago.adapter.inbound.handlers;

import br.com.tp.lanchescaieiras.commons.infraestructure.handlers.ExceptionHandlerUtil;
import br.com.tp.lanchescaieiras.payments.mercadopago.infraestructure.exceptions.PaymentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentInboundHandler {

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<Object> handleKitchenOrderException(PaymentException ex) {
        return ExceptionHandlerUtil.handleException(ex.getMessage(), ex.getCode(),ex);
    }
}
