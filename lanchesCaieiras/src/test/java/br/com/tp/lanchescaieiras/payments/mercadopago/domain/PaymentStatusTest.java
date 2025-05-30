package br.com.tp.lanchescaieiras.payments.mercadopago.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentStatusTest {

    @Test
    void testGetIdAndDescription() {
        assertEquals(1, PaymentStatus.CHARGED.getId());
        assertEquals("Charged", PaymentStatus.CHARGED.getDescription());
        assertEquals(2, PaymentStatus.PAID.getId());
        assertEquals("Paid", PaymentStatus.PAID.getDescription());
    }

    @Test
    void testFromIdValid() {
        assertEquals(PaymentStatus.CHARGED, PaymentStatus.fromId(1));
        assertEquals(PaymentStatus.PAID, PaymentStatus.fromId(2));
    }

    @Test
    void testFromIdInvalid() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> PaymentStatus.fromId(99));
        assertTrue(ex.getMessage().contains("Invalid code"));
    }

    @Test
    void testFromDescriptionValid() {
        assertEquals(PaymentStatus.CHARGED, PaymentStatus.fromDescription("Charged"));
        assertEquals(PaymentStatus.PAID, PaymentStatus.fromDescription("Paid"));
        assertEquals(PaymentStatus.PAID, PaymentStatus.fromDescription("paid"));
    }

    @Test
    void testFromDescriptionInvalid() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> PaymentStatus.fromDescription("INVALID"));
        assertTrue(ex.getMessage().contains("Invalid description"));
    }
}
