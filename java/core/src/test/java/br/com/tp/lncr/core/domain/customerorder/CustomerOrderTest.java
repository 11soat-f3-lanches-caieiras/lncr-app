package br.com.tp.lncr.core.domain.customerorder;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lncr.core.commons.enums.CustomerOrderStatus;
import br.com.tp.lncr.core.commons.exceptions.CustomerOrderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class CustomerOrderTest {
    @Test
    void testConstructorAndGetters() {
        CustomerOrderCustomer customer = new CustomerOrderCustomer(1, "Cliente Teste");
        CustomerOrderFoodItem item = new CustomerOrderFoodItem(1, 1, "Coxinha", "Salgado", 7.5, "Sem pimenta");
        CustomerOrder order = new CustomerOrder(10, CustomerOrderStatus.RECEIVED.getDescription(), 7.5, LocalDateTime.now(), LocalDateTime.now(), customer, List.of(item));
        Assertions.assertEquals(10, order.getId());
        Assertions.assertEquals("Received", order.getStatus());
        Assertions.assertEquals(7.5, order.getTotalCost());
        Assertions.assertEquals(customer, order.getCustomer());
        Assertions.assertEquals(1, order.getFoodItems().size());
    }

    @Test
    void testSetters() {
        CustomerOrder order = new CustomerOrder();
        order.setId(20);
        order.setStatus(CustomerOrderStatus.CHECKOUT);
        order.setTotalCost(15.0);
        CustomerOrderCustomer customer = new CustomerOrderCustomer(2, "Outro Cliente");
        order.setCustomer(customer);
        CustomerOrderFoodItem item = new CustomerOrderFoodItem(2, 20, "Pastel", "Pastel de queijo", 8.0, "Com queijo extra");
        order.setFoodItems(List.of(item));
        Assertions.assertEquals(20, order.getId());
        Assertions.assertEquals("Checkout", order.getStatus());
        Assertions.assertEquals(8.0, order.getTotalCost());
        Assertions.assertEquals(customer, order.getCustomer());
        Assertions.assertEquals(1, order.getFoodItems().size());
    }

    @Test
    void testDTOConstructorWithItems() {
        CustomerOrderDTO dto = new CustomerOrderDTO();
        dto.setId(30);
        dto.setStatus(CustomerOrderStatus.RECEIVED.getDescription());
        dto.setTotalCost(10.0);
        dto.set_created(LocalDateTime.now());
        dto.set_updated(LocalDateTime.now());
        CustomerOrderCustomerDTO customerDTO = new CustomerOrderCustomerDTO();
        customerDTO.setId(3);
        customerDTO.setName("DTO Cliente");
        dto.setCustomer(customerDTO);
        CustomerOrderFoodItemDTO itemDTO = new CustomerOrderFoodItemDTO();
        itemDTO.setId(3);
        itemDTO.setOrderId(30);
        itemDTO.setName("Esfiha");
        itemDTO.setDescription("Esfiha de carne");
        itemDTO.setPrice(6.0);
        itemDTO.setNotes("Sem cebola");
        dto.setFoodItems(List.of(itemDTO));
        CustomerOrder order = new CustomerOrder(dto);
        Assertions.assertEquals(30, order.getId());
        Assertions.assertEquals("Received", order.getStatus());
        Assertions.assertEquals(6.0, order.getTotalCost());
        Assertions.assertEquals("DTO Cliente", order.getCustomer().getName());
        Assertions.assertEquals(1, order.getFoodItems().size());
    }

    @Test
    void testDTOConstructorWithoutItemsThrowsException() {
        CustomerOrderDTO dto = new CustomerOrderDTO();
        dto.setId(40);
        dto.setStatus(CustomerOrderStatus.RECEIVED.getDescription());
        dto.setTotalCost(10.0);
        dto.set_created(LocalDateTime.now());
        dto.set_updated(LocalDateTime.now());
        dto.setFoodItems(Collections.emptyList());
        Assertions.assertThrows(CustomerOrderException.class, () -> new CustomerOrder(dto));
    }

    @Test
    void testToString() {
        CustomerOrderCustomer customer = new CustomerOrderCustomer(1, "Cliente Teste");
        CustomerOrderFoodItem item = new CustomerOrderFoodItem(1, 1, "Coxinha", "Salgado", 7.5, "Sem pimenta");
        CustomerOrder order = new CustomerOrder(10, CustomerOrderStatus.RECEIVED.getDescription(), 7.5, LocalDateTime.now(), LocalDateTime.now(), customer, List.of(item));
        String str = order.toString();
        Assertions.assertTrue(str.contains("CustomerOrder{"));
    }
}

