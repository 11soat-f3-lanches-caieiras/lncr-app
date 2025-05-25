package br.com.tp.lanchescaieiras.customerorder.adapters.inbound.handlers;

import br.com.tp.lanchescaieiras.commons.infraestructure.handlers.ExceptionHandlerUtil;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.exceptions.CustomerOrderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerOrderInboundHandler {

    @ExceptionHandler(CustomerOrderException.class)
    public ResponseEntity<Object> handleCustomerOrderExceptionException(CustomerOrderException ex) {
        return ExceptionHandlerUtil.handleException(ex.getMessage(), ex.getCode(), ex);
    }

}
