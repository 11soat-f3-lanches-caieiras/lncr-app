package br.com.tp.lncr.core.commons.dtos.customerorder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class CustomerOrderDTOTest {
    @Test
    void createCustomerOrderDTOWithAllFields() {
        CustomerOrderCustomerDTO customer = new CustomerOrderCustomerDTO(1, "João");
        CustomerOrderFoodItemDTO foodItem = new CustomerOrderFoodItemDTO(10, 100, "Pizza", "Mussarela", 30.0, "Sem cebola");
        List<CustomerOrderFoodItemDTO> foodItems = Collections.singletonList(foodItem);
        LocalDateTime now = LocalDateTime.now();
        CustomerOrderDTO dto = new CustomerOrderDTO(100, "RECEIVED", 30.0, now, now, customer, foodItems);
        Assertions.assertEquals(100, dto.getId());
        Assertions.assertEquals("RECEIVED", dto.getStatus());
        Assertions.assertEquals(30.0, dto.getTotalCost());
        Assertions.assertEquals(now, dto.get_created());
        Assertions.assertEquals(now, dto.get_updated());
        Assertions.assertEquals(customer, dto.getCustomer());
        Assertions.assertEquals(foodItems, dto.getFoodItems());
    }

    @Test
    void setAndGetFieldsIndividually() {
        CustomerOrderDTO dto = new CustomerOrderDTO();
        LocalDateTime now = LocalDateTime.now();
        CustomerOrderCustomerDTO customer = new CustomerOrderCustomerDTO(2, "Maria");
        CustomerOrderFoodItemDTO foodItem = new CustomerOrderFoodItemDTO();
        List<CustomerOrderFoodItemDTO> foodItems = List.of(foodItem);
        dto.setId(200);
        dto.setStatus("READY");
        dto.setTotalCost(50.0);
        dto.set_created(now);
        dto.set_updated(now);
        dto.setCustomer(customer);
        dto.setFoodItems(foodItems);
        Assertions.assertEquals(200, dto.getId());
        Assertions.assertEquals("READY", dto.getStatus());
        Assertions.assertEquals(50.0, dto.getTotalCost());
        Assertions.assertEquals(now, dto.get_created());
        Assertions.assertEquals(now, dto.get_updated());
        Assertions.assertEquals(customer, dto.getCustomer());
        Assertions.assertEquals(foodItems, dto.getFoodItems());
    }

    @Test
    void allowNullFields() {
        CustomerOrderDTO dto = new CustomerOrderDTO(null, null, null, null, null, null, null);
        Assertions.assertNull(dto.getId());
        Assertions.assertNull(dto.getStatus());
        Assertions.assertNull(dto.getTotalCost());
        Assertions.assertNull(dto.get_created());
        Assertions.assertNull(dto.get_updated());
        Assertions.assertNull(dto.getCustomer());
        Assertions.assertNull(dto.getFoodItems());
    }
}

