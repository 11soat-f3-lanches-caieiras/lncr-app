package br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.entities;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JpaKitchenOrderEntityTest {

    @Test
    void testConstructorAndGettersSetters() {
        JpaKitchenOrderFoodItemEntity item = new JpaKitchenOrderFoodItemEntity(1, 2, "nome", "desc", "obs");
        JpaKitchenOrderEntity entity = new JpaKitchenOrderEntity(10, 20, 1, List.of(item));

        assertEquals(10, entity.getId());
        assertEquals(20, entity.getCustomerOrderId());
        assertEquals(1, entity.getStatusId());
        assertEquals(1, entity.getFoodItems().size());

        entity.setId(11);
        entity.setCustomerOrderId(21);
        entity.setStatusId(2);
        entity.setFoodItems(List.of());

        assertEquals(11, entity.getId());
        assertEquals(21, entity.getCustomerOrderId());
        assertEquals(2, entity.getStatusId());
        assertTrue(entity.getFoodItems().isEmpty());
    }

    @Test
    void testDefaultConstructor() {
        JpaKitchenOrderEntity entity = new JpaKitchenOrderEntity();
        assertNull(entity.getId());
        assertNull(entity.getCustomerOrderId());
        assertNull(entity.getStatusId());
        assertNull(entity.getFoodItems());
    }
}
