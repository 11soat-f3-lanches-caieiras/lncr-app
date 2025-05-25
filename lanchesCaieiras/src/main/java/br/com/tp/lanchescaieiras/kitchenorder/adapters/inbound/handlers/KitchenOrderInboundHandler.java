package br.com.tp.lanchescaieiras.kitchenorder.adapters.inbound.handlers;

import br.com.tp.lanchescaieiras.commons.infraestructure.handlers.ExceptionHandlerUtil;
import br.com.tp.lanchescaieiras.kitchenorder.infraestructure.exceptions.KitchenOrderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class KitchenOrderInboundHandler {

    @ExceptionHandler(KitchenOrderException.class)
    public ResponseEntity<Object> handleKitchenOrderException(KitchenOrderException ex) {
        return ExceptionHandlerUtil.handleException(ex.getMessage(), ex.getCode(),ex);
    }

}
