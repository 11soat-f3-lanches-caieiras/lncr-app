package br.com.tp.lanchescaieiras.kitchenorder.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class KitchenOrderTest {

    @Test
    void testConstructorAndGetters() {
        KitchenOrderFoodItem item = new KitchenOrderFoodItem(1, 1, "X-Burger", "Hambúrguer", "Sem cebola");
        KitchenOrder order = new KitchenOrder(10, 20, KitchenOrderStatus.RECEIVED, List.of(item));

        assertEquals(10, order.getId());
        assertEquals(20, order.getCustomerOrderId());
        assertEquals("Received", order.getStatus());
        assertEquals(1, order.getFoodItems().size());
        assertEquals("X-Burger", order.getFoodItems().get(0).getName());
    }

    @Test
    void testSetters() {
        KitchenOrder order = new KitchenOrder();
        order.setId(2);
        order.setCustomerOrderId(3);
        order.setStatus("Ready");
        order.setFoodItems(List.of());

        assertEquals(2, order.getId());
        assertEquals(3, order.getCustomerOrderId());
        assertEquals("Ready", order.getStatus());
        assertTrue(order.getFoodItems().isEmpty());
    }

    @Test
    void testSetStatusEnum() {
        KitchenOrder order = new KitchenOrder();
        order.setStatus(KitchenOrderStatus.FINISHED);
        assertEquals("Finished", order.getStatus());
    }

    @Test
    void testFromKitchenOrderStatus() {
        KitchenOrder order = new KitchenOrder();
        assertEquals("Preparing", order.fromKitchenOrderStatus(KitchenOrderStatus.PREPARING));
    }
}
