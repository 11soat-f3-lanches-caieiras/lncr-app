package br.com.tp.lncr.core.commons.dtos.customerorder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerOrderFoodItemDTOTest {
    @Test
    void createCustomerOrderFoodItemDTOWithAllFields() {
        CustomerOrderFoodItemDTO dto = new CustomerOrderFoodItemDTO(1, 10, "Pizza", "Mussarela", 30.0, "Sem cebola");
        Assertions.assertEquals(1, dto.getId());
        Assertions.assertEquals(10, dto.getOrderId());
        Assertions.assertEquals("Pizza", dto.getName());
        Assertions.assertEquals("Mussarela", dto.getDescription());
        Assertions.assertEquals(30.0, dto.getPrice());
        Assertions.assertEquals("Sem cebola", dto.getNotes());
    }

    @Test
    void setAndGetFieldsIndividually() {
        CustomerOrderFoodItemDTO dto = new CustomerOrderFoodItemDTO();
        dto.setId(2);
        dto.setOrderId(20);
        dto.setName("Hamburguer");
        dto.setDescription("Carne");
        dto.setPrice(25.0);
        dto.setNotes("Sem sal");
        Assertions.assertEquals(2, dto.getId());
        Assertions.assertEquals(20, dto.getOrderId());
        Assertions.assertEquals("Hamburguer", dto.getName());
        Assertions.assertEquals("Carne", dto.getDescription());
        Assertions.assertEquals(25.0, dto.getPrice());
        Assertions.assertEquals("Sem sal", dto.getNotes());
    }

    @Test
    void allowNullFields() {
        CustomerOrderFoodItemDTO dto = new CustomerOrderFoodItemDTO(null, null, null, null, null, null);
        Assertions.assertNull(dto.getId());
        Assertions.assertNull(dto.getOrderId());
        Assertions.assertNull(dto.getName());
        Assertions.assertNull(dto.getDescription());
        Assertions.assertNull(dto.getPrice());
        Assertions.assertNull(dto.getNotes());
    }
}

