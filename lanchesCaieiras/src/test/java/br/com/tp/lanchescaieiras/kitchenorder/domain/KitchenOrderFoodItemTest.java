package br.com.tp.lanchescaieiras.kitchenorder.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class KitchenOrderFoodItemTest {

    @Test
    void testConstructorAndGetters() {
        KitchenOrderFoodItem item = new KitchenOrderFoodItem(1, 2, "Coxinha", "Frango", "Bem passada");
        assertEquals(1, item.getId());
        assertEquals(2, item.getKitchenOrderId());
        assertEquals("Coxinha", item.getName());
        assertEquals("Frango", item.getDescription());
        assertEquals("Bem passada", item.getNotes());
    }

    @Test
    void testSetters() {
        KitchenOrderFoodItem item = new KitchenOrderFoodItem();
        item.setId(5);
        item.setKitchenOrderId(6);
        item.setName("Pastel");
        item.setDescription("Carne");
        item.setNotes("Sem pimenta");

        assertEquals(5, item.getId());
        assertEquals(6, item.getKitchenOrderId());
        assertEquals("Pastel", item.getName());
        assertEquals("Carne", item.getDescription());
        assertEquals("Sem pimenta", item.getNotes());
    }
}
