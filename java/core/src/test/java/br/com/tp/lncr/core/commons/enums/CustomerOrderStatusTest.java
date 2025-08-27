package br.com.tp.lncr.core.commons.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerOrderStatusTest {
    @Test
    void testGetIdAndDescription() {
        Assertions.assertEquals(1, CustomerOrderStatus.CHECKOUT.getId());
        Assertions.assertEquals("Checkout", CustomerOrderStatus.CHECKOUT.getDescription());
    }

    @Test
    void testFromIdValid() {
        Assertions.assertEquals(CustomerOrderStatus.RECEIVED, CustomerOrderStatus.fromId(2));
    }

    @Test
    void testFromIdInvalid() {
        Exception ex = Assertions.assertThrows(Exception.class, () -> CustomerOrderStatus.fromId(99));
        Assertions.assertTrue(ex.getMessage().contains("Id do status inválido"));
    }

    @Test
    void testFromDescriptionValid() {
        Assertions.assertEquals(CustomerOrderStatus.PREPARING, CustomerOrderStatus.fromDescription("Preparing"));
    }

    @Test
    void testFromDescriptionInvalid() {
        Exception ex = Assertions.assertThrows(Exception.class, () -> CustomerOrderStatus.fromDescription("INVALID"));
        Assertions.assertTrue(ex.getMessage().contains("Status inválidos"));
    }

    @Test
    void testListOfAllowDescriptionsAndIds() {
        Assertions.assertTrue(CustomerOrderStatus.listOfAllowDescriptions().contains("Checkout"));
        Assertions.assertTrue(CustomerOrderStatus.listOfAllowIds().contains("1"));
    }
}

