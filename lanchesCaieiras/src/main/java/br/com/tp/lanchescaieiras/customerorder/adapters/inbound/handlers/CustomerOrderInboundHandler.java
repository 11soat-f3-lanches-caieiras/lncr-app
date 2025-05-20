package br.com.tp.lanchescaieiras.customerorder.adapters.inbound.handlers;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations.FoodItemIntegrationImpl;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderResponse;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.CustomerOrderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestControllerAdvice
public class CustomerOrderInboundHandler {

    @ExceptionHandler(CustomerOrderException.class)
    public ResponseEntity<CustomerOrderResponse> foodItemCustomerOrder(CustomerOrderException ex) {
        return new ResponseEntity<>(createCustomerOrderResponse(ex.getMessage()), getHttpStatusByCode(ex.getCode()));
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

    public CustomerOrderResponse createCustomerOrderResponse(String message) {
        return new CustomerOrderResponse(
                new ResponseMetada(UUID.randomUUID().toString(), OffsetDateTime.now().toString(), message),
                null);
    }
}
