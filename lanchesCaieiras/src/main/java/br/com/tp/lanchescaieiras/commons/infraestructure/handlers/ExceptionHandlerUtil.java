package br.com.tp.lanchescaieiras.commons.infraestructure.handlers;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ExceptionHandlerUtil {

    public static ResponseEntity<Object> handleException(String message, int code) {
        return new ResponseEntity<>(createResponse(message), getHttpStatusByCode(code));
    }

    private static HttpStatus getHttpStatusByCode(int code) {
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

    private static ResponseMetada createResponse(String message) {
        return new ResponseMetada(UUID.randomUUID().toString(), OffsetDateTime.now().toString(), message);
    }
}