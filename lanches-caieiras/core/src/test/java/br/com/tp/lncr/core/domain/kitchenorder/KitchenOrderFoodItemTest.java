package br.com.tp.lncr.core.domain.kitchenorder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KitchenOrderFoodItemTest {
    @Test
    void testConstructorAndGetters() {
        KitchenOrderFoodItem item = new KitchenOrderFoodItem(1, 2, "Coxinha", "Salgado", "Sem pimenta");
        Assertions.assertEquals(1, item.getId());
        Assertions.assertEquals(2, item.getKitchenOrderId());
        Assertions.assertEquals("Coxinha", item.getName());
        Assertions.assertEquals("Salgado", item.getDescription());
        Assertions.assertEquals("Sem pimenta", item.getNotes());
    }

    @Test
    void testSetters() {
        KitchenOrderFoodItem item = new KitchenOrderFoodItem();
        item.setId(3);
        item.setKitchenOrderId(4);
        item.setName("Pastel");
        item.setDescription("Pastel de queijo");
        item.setNotes("Com queijo extra");
        Assertions.assertEquals(3, item.getId());
        Assertions.assertEquals(4, item.getKitchenOrderId());
        Assertions.assertEquals("Pastel", item.getName());
        Assertions.assertEquals("Pastel de queijo", item.getDescription());
        Assertions.assertEquals("Com queijo extra", item.getNotes());
    }
}

