package br.com.tp.lncr.core.commons.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KitchenOrderStatusTest {
    @Test
    void testGetIdAndDescription() {
        Assertions.assertEquals(1, KitchenOrderStatus.RECEIVED.getId());
        Assertions.assertEquals("Received", KitchenOrderStatus.RECEIVED.getDescription());
    }

    @Test
    void testFromIdValid() {
        Assertions.assertEquals(KitchenOrderStatus.PREPARING, KitchenOrderStatus.fromId(2));
    }

    @Test
    void testFromIdInvalid() {
        Exception ex = Assertions.assertThrows(Exception.class, () -> KitchenOrderStatus.fromId(99));
        Assertions.assertTrue(ex.getMessage().contains("Id do status inválido"));
    }

    @Test
    void testFromDescriptionValid() {
        Assertions.assertEquals(KitchenOrderStatus.READY, KitchenOrderStatus.fromDescription("Ready"));
    }

    @Test
    void testFromDescriptionInvalid() {
        Exception ex = Assertions.assertThrows(Exception.class, () -> KitchenOrderStatus.fromDescription("INVALID"));
        Assertions.assertTrue(ex.getMessage().contains("Status inválidos"));
    }

    @Test
    void testListOfAllowDescriptionsAndIds() {
        Assertions.assertTrue(KitchenOrderStatus.listOfAllowDescriptions().contains("Received"));
        Assertions.assertTrue(KitchenOrderStatus.listOfAllowIds().contains("1"));
    }
}

