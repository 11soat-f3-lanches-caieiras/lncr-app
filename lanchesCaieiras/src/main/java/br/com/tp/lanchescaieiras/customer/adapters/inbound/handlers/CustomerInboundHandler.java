package br.com.tp.lanchescaieiras.customer.adapters.inbound.handlers;

import br.com.tp.lanchescaieiras.customer.domain.CustomerResponse;
import br.com.tp.lanchescaieiras.customer.domain.ResponseMetada;
import br.com.tp.lanchescaieiras.customer.infraestructure.exceptions.CustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestControllerAdvice
public class CustomerInboundHandler {

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<CustomerResponse> handlerCustomerAlreadyExists(CustomerException ex) {
        return new ResponseEntity<>(createCustomerResponse(ex.getMessage()), getHttpStatusByCode(ex.getCode()));
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

    public CustomerResponse createCustomerResponse(String message) {
        return new CustomerResponse(
                new ResponseMetada(UUID.randomUUID().toString(), OffsetDateTime.now().toString(), message),
                null);
    }
}
