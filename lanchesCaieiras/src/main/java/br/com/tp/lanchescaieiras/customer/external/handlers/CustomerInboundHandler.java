package br.com.tp.lanchescaieiras.customer.external.handlers;

import br.com.tp.lanchescaieiras.commons.infraestructure.handlers.ExceptionHandlerUtil;
import br.com.tp.lanchescaieiras.customer.domain.shared.exceptions.CustomerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerInboundHandler {

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<Object> handlerCustomerException(CustomerException ex) {
        return ExceptionHandlerUtil.handleException(ex.getMessage(), ex.getCode(), ex);
    }
}
