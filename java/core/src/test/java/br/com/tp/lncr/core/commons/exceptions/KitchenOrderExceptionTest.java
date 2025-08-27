package br.com.tp.lncr.core.commons.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KitchenOrderExceptionTest {
    @Test
    void testMessageAndCode() {
        KitchenOrderException ex = new KitchenOrderException("msg", 321);
        assertEquals("msg", ex.getMessage());
        assertEquals(321, ex.getCode());
    }
}

