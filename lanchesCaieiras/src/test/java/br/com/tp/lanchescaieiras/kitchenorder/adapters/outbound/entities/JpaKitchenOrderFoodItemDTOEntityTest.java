package br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JpaKitchenOrderFoodItemDTOEntityTest {

    @Test
    void testConstructorAndGettersSetters() {
        JpaKitchenOrderFoodItemEntity entity = new JpaKitchenOrderFoodItemEntity(1, 2, "nome", "desc", "obs");

        assertEquals(1, entity.getId());
        assertEquals(2, entity.getKitchenOrderId());
        assertEquals("nome", entity.getName());
        assertEquals("desc", entity.getDescription());
        assertEquals("obs", entity.getNotes());

        entity.setId(3);
        entity.setKitchenOrderId(4);
        entity.setName("novo");
        entity.setDescription("nova desc");
        entity.setNotes("nova obs");

        assertEquals(3, entity.getId());
        assertEquals(4, entity.getKitchenOrderId());
        assertEquals("novo", entity.getName());
        assertEquals("nova desc", entity.getDescription());
        assertEquals("nova obs", entity.getNotes());
    }

    @Test
    void testDefaultConstructor() {
        JpaKitchenOrderFoodItemEntity entity = new JpaKitchenOrderFoodItemEntity();
        assertNull(entity.getId());
        assertNull(entity.getKitchenOrderId());
        assertNull(entity.getName());
        assertNull(entity.getDescription());
        assertNull(entity.getNotes());
    }
}
