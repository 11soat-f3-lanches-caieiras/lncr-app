package br.com.tp.lncr.external.handlers;

import br.com.tp.lncr.core.commons.exceptions.CustomerOrderException;
import br.com.tp.lncr.external.commons.utils.ExceptionHandlerUtil;
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
