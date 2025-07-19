package br.com.tp.lncr.core.domain.kitchenorder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

public class KitchenOrderTest {
    @Test
    void testConstructorAndGetters() {
        KitchenOrderFoodItem item = new KitchenOrderFoodItem(1, 10, "Coxinha", "Salgado", "Sem pimenta");
        KitchenOrder order = new KitchenOrder(1, 10, "Recebido", Arrays.asList(item), LocalDateTime.now(), LocalDateTime.now());
        Assertions.assertEquals(1, order.getId());
        Assertions.assertEquals(10, order.getCustomerOrderId());
        Assertions.assertEquals("Recebido", order.getStatus());
        Assertions.assertEquals(1, order.getFoodItems().size());
    }

    @Test
    void testSetters() {
        KitchenOrder order = new KitchenOrder();
        order.setId(2);
        order.setCustomerOrderId(20);
        order.setStatus("Em preparo");
        order.setFoodItems(Arrays.asList(new KitchenOrderFoodItem()));
        Assertions.assertEquals(2, order.getId());
        Assertions.assertEquals(20, order.getCustomerOrderId());
        Assertions.assertEquals("Em preparo", order.getStatus());
        Assertions.assertEquals(1, order.getFoodItems().size());
    }
}

