package br.com.tp.lanchescaieiras.kitchenorder.domain;

import br.com.tp.lanchescaieiras.kitchenorder.infraestructure.exceptions.KitchenOrderException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

class KitchenOrderStatusTest {

    @Test
    void testFromIdValid() {
        assertEquals(KitchenOrderStatus.RECEIVED, KitchenOrderStatus.fromId(1));
        assertEquals(KitchenOrderStatus.PREPARING, KitchenOrderStatus.fromId(2));
        assertEquals(KitchenOrderStatus.READY, KitchenOrderStatus.fromId(3));
        assertEquals(KitchenOrderStatus.FINISHED, KitchenOrderStatus.fromId(4));
    }

    @Test
    void testFromIdInvalid() {
        Exception exception = assertThrows(KitchenOrderException.class, () -> {
            KitchenOrderStatus.fromId(99);
        });
        assertTrue(exception.getMessage().contains("Id do status inválido"));
    }

    @Test
    void testFromDescriptionValid() {
        assertEquals(KitchenOrderStatus.RECEIVED, KitchenOrderStatus.fromDescription("Received"));
        assertEquals(KitchenOrderStatus.PREPARING, KitchenOrderStatus.fromDescription("Preparing"));
        assertEquals(KitchenOrderStatus.READY, KitchenOrderStatus.fromDescription("Ready"));
        assertEquals(KitchenOrderStatus.FINISHED, KitchenOrderStatus.fromDescription("Finished"));
    }

    @Test
    void testFromDescriptionInvalid() {
        Exception exception = assertThrows(KitchenOrderException.class, () -> {
            KitchenOrderStatus.fromDescription("INVALID");
        });
        assertTrue(exception.getMessage().contains("Status inválidos"));
    }

    @Test
    void testListOfAllowDescriptions() {
        String desc = KitchenOrderStatus.listOfAllowDescriptions();
        assertTrue(desc.contains("Received"));
        assertTrue(desc.contains("Preparing"));
        assertTrue(desc.contains("Ready"));
        assertTrue(desc.contains("Finished"));
    }

    @Test
    void testListOfAllowIds() {
        String ids = KitchenOrderStatus.listOfAllowIds();
        assertTrue(ids.contains("1"));
        assertTrue(ids.contains("2"));
        assertTrue(ids.contains("3"));
        assertTrue(ids.contains("4"));
    }
}
