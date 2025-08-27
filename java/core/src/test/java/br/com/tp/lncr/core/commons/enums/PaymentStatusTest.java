package br.com.tp.lncr.core.commons.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PaymentStatusTest {
    @Test
    void testGetIdAndDescription() {
        Assertions.assertEquals(0, PaymentStatus.CANCELLED.getId());
        Assertions.assertEquals("Cancelled", PaymentStatus.CANCELLED.getDescription());
    }

    @Test
    void testFromIdValid() {
        Assertions.assertEquals(PaymentStatus.CHARGED, PaymentStatus.fromId(1));
    }

    @Test
    void testFromIdInvalid() {
        Exception ex = Assertions.assertThrows(Exception.class, () -> PaymentStatus.fromId(99));
        Assertions.assertTrue(ex.getMessage().contains("Id do status de pagamento inválido"));
    }

    @Test
    void testFromDescriptionValid() {
        Assertions.assertEquals(PaymentStatus.PAID, PaymentStatus.fromDescription("Paid"));
    }

    @Test
    void testFromDescriptionInvalid() {
        Exception ex = Assertions.assertThrows(Exception.class, () -> PaymentStatus.fromDescription("INVALID"));
        Assertions.assertTrue(ex.getMessage().contains("Status de pagamento inválido"));
    }

    @Test
    void testListOfAllowDescriptionsAndIds() {
        Assertions.assertTrue(PaymentStatus.listOfAllowDescriptions().contains("Cancelled"));
        Assertions.assertTrue(PaymentStatus.listOfAllowIds().contains("0"));
    }
}

