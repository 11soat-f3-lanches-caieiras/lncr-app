package br.com.tp.lanchescaieiras.fooditem.external.handlers;

import br.com.tp.lanchescaieiras.commons.utils.ExceptionHandlerUtil;
import br.com.tp.lanchescaieiras.fooditem.domain.exceptions.FoodItemException;
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


