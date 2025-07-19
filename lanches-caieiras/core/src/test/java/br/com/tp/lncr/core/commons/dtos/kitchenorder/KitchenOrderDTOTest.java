package br.com.tp.lncr.core.commons.dtos.kitchenorder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class KitchenOrderDTOTest {
    @Test
    void createKitchenOrderDTOWithAllFields() {
        KitchenOrderFoodItemDTO foodItem = new KitchenOrderFoodItemDTO(1, 2, "Pizza", "Mussarela", "Sem cebola");
        List<KitchenOrderFoodItemDTO> foodItems = Collections.singletonList(foodItem);
        LocalDateTime now = LocalDateTime.now();
        KitchenOrderDTO dto = new KitchenOrderDTO(10, 20, "PREPARING", foodItems, now, now);
        Assertions.assertEquals(10, dto.getId());
        Assertions.assertEquals(20, dto.getCustomerOrderId());
        Assertions.assertEquals("PREPARING", dto.getStatus());
        Assertions.assertEquals(foodItems, dto.getFoodItems());
        Assertions.assertEquals(now, dto.getCreated());
        Assertions.assertEquals(now, dto.getUpdated());
    }

    @Test
    void setAndGetFieldsIndividually() {
        KitchenOrderDTO dto = new KitchenOrderDTO();
        KitchenOrderFoodItemDTO foodItem = new KitchenOrderFoodItemDTO();
        List<KitchenOrderFoodItemDTO> foodItems = Arrays.asList(foodItem);
        LocalDateTime now = LocalDateTime.now();
        dto.setId(11);
        dto.setCustomerOrderId(21);
        dto.setStatus("READY");
        dto.setFoodItems(foodItems);
        dto.setCreated(now);
        dto.setUpdated(now);
        Assertions.assertEquals(11, dto.getId());
        Assertions.assertEquals(21, dto.getCustomerOrderId());
        Assertions.assertEquals("READY", dto.getStatus());
        Assertions.assertEquals(foodItems, dto.getFoodItems());
        Assertions.assertEquals(now, dto.getCreated());
        Assertions.assertEquals(now, dto.getUpdated());
    }

    @Test
    void allowNullFields() {
        KitchenOrderDTO dto = new KitchenOrderDTO(null, null, null, null, null, null);
        Assertions.assertNull(dto.getId());
        Assertions.assertNull(dto.getCustomerOrderId());
        Assertions.assertNull(dto.getStatus());
        Assertions.assertNull(dto.getFoodItems());
        Assertions.assertNull(dto.getCreated());
        Assertions.assertNull(dto.getUpdated());
    }
}

