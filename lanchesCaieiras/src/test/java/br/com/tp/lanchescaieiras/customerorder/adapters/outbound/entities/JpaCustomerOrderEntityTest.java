package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.entities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JpaCustomerOrderEntityTest {

    @Test
    void testConstructorAndGetters() {
        JpaCustomerOrderFoodItemEntity item = new JpaCustomerOrderFoodItemEntity(1, 2, 3, 10.0, "obs");
        List<JpaCustomerOrderFoodItemEntity> items = List.of(item);
        JpaCustomerOrderEntity entity = new JpaCustomerOrderEntity(1, 20.0, 2, 3, items);

        assertEquals(1, entity.getId());
        assertEquals(20.0, entity.getTotalCost());
        assertEquals(2, entity.getStatusId());
        assertEquals(3, entity.getCustomerId());
        assertEquals(items, entity.getFoodItems());
    }

    @Test
    void testSetters() {
        JpaCustomerOrderEntity entity = new JpaCustomerOrderEntity();
        entity.setId(10);
        entity.setTotalCost(50.0);
        entity.setStatusId(5);
        entity.setCustomerId(7);

        JpaCustomerOrderFoodItemEntity item = new JpaCustomerOrderFoodItemEntity();
        entity.setFoodItems(List.of(item));

        assertEquals(10, entity.getId());
        assertEquals(50.0, entity.getTotalCost());
        assertEquals(5, entity.getStatusId());
        assertEquals(7, entity.getCustomerId());
        assertEquals(1, entity.getFoodItems().size());
    }
}
