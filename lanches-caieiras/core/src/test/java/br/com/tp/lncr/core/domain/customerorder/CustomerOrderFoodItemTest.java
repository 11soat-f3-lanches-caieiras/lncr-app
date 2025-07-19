package br.com.tp.lncr.core.domain.customerorder;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerOrderFoodItemTest {
    @Test
    void testConstructorAndGetters() {
        CustomerOrderFoodItem item = new CustomerOrderFoodItem(1, 10, "Coxinha", "Salgado de frango", 7.5, "Sem pimenta");
        Assertions.assertEquals(1, item.getId());
        Assertions.assertEquals(10, item.getCustomerOrderId());
        Assertions.assertEquals("Coxinha", item.getName());
        Assertions.assertEquals("Salgado de frango", item.getDescription());
        Assertions.assertEquals(7.5, item.getPrice());
        Assertions.assertEquals("Sem pimenta", item.getNotes());
    }

    @Test
    void testSetters() {
        CustomerOrderFoodItem item = new CustomerOrderFoodItem();
        item.setId(2);
        item.setCustomerOrderId(20);
        item.setName("Pastel");
        item.setDescription("Pastel de queijo");
        item.setPrice(8.0);
        item.setNotes("Com queijo extra");
        Assertions.assertEquals(2, item.getId());
        Assertions.assertEquals(20, item.getCustomerOrderId());
        Assertions.assertEquals("Pastel", item.getName());
        Assertions.assertEquals("Pastel de queijo", item.getDescription());
        Assertions.assertEquals(8.0, item.getPrice());
        Assertions.assertEquals("Com queijo extra", item.getNotes());
    }

    @Test
    void testDTOConstructor() {
        CustomerOrderFoodItemDTO dto = new CustomerOrderFoodItemDTO();
        dto.setId(3);
        dto.setOrderId(30);
        dto.setName("Esfiha");
        dto.setDescription("Esfiha de carne");
        dto.setPrice(6.0);
        dto.setNotes("Sem cebola");
        CustomerOrderFoodItem item = new CustomerOrderFoodItem(dto);
        Assertions.assertEquals(3, item.getId());
        Assertions.assertEquals(30, item.getCustomerOrderId());
        Assertions.assertEquals("Esfiha", item.getName());
        Assertions.assertEquals("Esfiha de carne", item.getDescription());
        Assertions.assertEquals(6.0, item.getPrice());
        Assertions.assertEquals("Sem cebola", item.getNotes());
    }
}

