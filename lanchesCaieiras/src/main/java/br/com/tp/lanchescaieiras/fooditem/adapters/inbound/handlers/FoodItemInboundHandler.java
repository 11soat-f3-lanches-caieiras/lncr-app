package br.com.tp.lanchescaieiras.fooditem.adapters.inbound.handlers;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import br.com.tp.lanchescaieiras.customer.domain.CustomerResponse;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemResponse;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestControllerAdvice
public class FoodItemInboundHandler {

    @ExceptionHandler(FoodItemException.class)
    public ResponseEntity<FoodItemResponse> handlerFoodItem(FoodItemException ex) {
        return new ResponseEntity<>(createfoodItemResponse(ex.getMessage()), getHttpStatusByCode(ex.getCode()));
    }

    public HttpStatus getHttpStatusByCode(int code) {
        return switch (code) {
            case 400 -> HttpStatus.BAD_REQUEST;
            case 401 -> HttpStatus.UNAUTHORIZED;
            case 403 -> HttpStatus.FORBIDDEN;
            case 404 -> HttpStatus.NOT_FOUND;
            case 409 -> HttpStatus.CONFLICT;
            case 500 -> HttpStatus.INTERNAL_SERVER_ERROR;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    public FoodItemResponse createfoodItemResponse(String message) {
        return new FoodItemResponse(
                new ResponseMetada(UUID.randomUUID().toString(), OffsetDateTime.now().toString(), message),
                null);
    }
}


