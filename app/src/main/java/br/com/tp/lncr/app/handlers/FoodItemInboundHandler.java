package br.com.tp.lncr.app.handlers;

import br.com.tp.lncr.app.commons.utils.ExceptionHandlerUtil;
import br.com.tp.lncr.core.commons.exceptions.FoodItemException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FoodItemInboundHandler {

    @ExceptionHandler(FoodItemException.class)
    public ResponseEntity<Object> handleFoodItemExceptionException(FoodItemException ex) {
        return ExceptionHandlerUtil.handleException(ex.getMessage(), ex.getCode(), ex);
    }

}


