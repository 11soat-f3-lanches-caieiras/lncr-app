package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaCustomerOrderFoodItemEntityTest {

    @Test
    void testConstructorAndGetters() {
        JpaCustomerOrderFoodItemEntity entity = new JpaCustomerOrderFoodItemEntity(1, 2, 3, 10.0, "obs");
        assertEquals(1, entity.getId());
        assertEquals(2, entity.getOrderId());
        assertEquals(3, entity.getFoodItemId());
        assertEquals(10.0, entity.getPrice());
        assertEquals("obs", entity.getNotes());
    }

    @Test
    void testSetters() {
        JpaCustomerOrderFoodItemEntity entity = new JpaCustomerOrderFoodItemEntity();
        entity.setId(5);
        entity.setOrderId(6);
        entity.setFoodItemId(7);
        entity.setPrice(12.5);
        entity.setNotes("sem cebola");

        assertEquals(5, entity.getId());
        assertEquals(6, entity.getOrderId());
        assertEquals(7, entity.getFoodItemId());
        assertEquals(12.5, entity.getPrice());
        assertEquals("sem cebola", entity.getNotes());
    }
}
