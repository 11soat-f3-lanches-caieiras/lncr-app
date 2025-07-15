package br.com.tp.lanchescaieiras._external.handlers;

import br.com.tp.lanchescaieiras._core.commons.exceptions.CustomerException;
import br.com.tp.lanchescaieiras._external.commons.utils.ExceptionHandlerUtil;
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
