package br.com.tp.lanchescaieiras.customer.infraestructure.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerExceptionTest {

    @Test
    void constructorInitializesMessageAndCode() {
        CustomerException exception = new CustomerException("Error occurred", 404);

        assertEquals("Error occurred", exception.getMessage());
        assertEquals(404, exception.getCode());
    }

    @Test
    void getCodeReturnsNullWhenCodeIsNotSet() {
        CustomerException exception = new CustomerException("Error occurred", null);

        assertEquals("Error occurred", exception.getMessage());
        assertNull(exception.getCode());
    }

    @Test
    void exceptionMessageCanBeNull() {
        CustomerException exception = new CustomerException(null, 500);

        assertNull(exception.getMessage());
        assertEquals(500, exception.getCode());
    }
}
