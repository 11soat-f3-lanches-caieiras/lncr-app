package br.com.tp.lncr.core.commons.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerOrderExceptionTest {
    @Test
    void testMessageAndCode() {
        CustomerOrderException ex = new CustomerOrderException("msg", 456);
        assertEquals("msg", ex.getMessage());
        assertEquals(456, ex.getCode());
    }
}

