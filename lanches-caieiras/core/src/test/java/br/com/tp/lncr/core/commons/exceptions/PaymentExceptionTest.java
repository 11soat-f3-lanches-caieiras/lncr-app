package br.com.tp.lncr.core.commons.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentExceptionTest {
    @Test
    void testMessageAndCode() {
        PaymentException ex = new PaymentException("msg", 987);
        assertEquals("msg", ex.getMessage());
        assertEquals(987, ex.getCode());
    }
}
