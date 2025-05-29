package br.com.tp.lanchescaieiras.customerorder.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrderFoodItemTest {

    @Test
    void testConstructorAndGetters() {
        CustomerOrderFoodItem item = new CustomerOrderFoodItem(1, "Coxinha", "Frango", 10.0, "Sem pimenta");
        assertEquals(1, item.getId());
        assertEquals("Coxinha", item.getName());
        assertEquals("Frango", item.getDescription());
        assertEquals(10.0, item.getPrice());
        assertNull(item.getNotes()); // O construtor não define notes
    }

    @Test
    void testSetters() {
        CustomerOrderFoodItem item = new CustomerOrderFoodItem();
        item.setId(2);
        item.setName("Pastel");
        item.setDescription("Carne");
        item.setPrice(8.0);
        item.setNotes("Com queijo");

        assertEquals(2, item.getId());
        assertEquals("Pastel", item.getName());
        assertEquals("Carne", item.getDescription());
        assertEquals(8.0, item.getPrice());
        assertEquals("Com queijo", item.getNotes());
    }
}
