package br.com.tp.lncr.core.adapters.customerorder;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrder;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrderCustomer;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrderFoodItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerOrderMapperTest {
    private final CustomerOrderMapper mapper = new CustomerOrderMapper();

    @Test
    void testCustomerOrderToDTOAndBack() {
        CustomerOrder order = new CustomerOrder();
        order.setId(1);
        order.setStatus("PENDING");
        order.setTotalCost(10.0);
        CustomerOrderCustomer customer = new CustomerOrderCustomer();
        customer.setId(2);
        customer.setName("Cliente");
        order.setCustomer(customer);
        CustomerOrderFoodItem item = new CustomerOrderFoodItem();
        item.setId(3);
        item.setName("Lanche");
        item.setPrice(5.0);
        order.setFoodItems(List.of(item));
        CustomerOrderDTO dto = mapper.customerOrderToDTO(order);
        assertNotNull(dto);
        assertEquals(order.getId(), dto.getId());
        assertEquals(order.getStatus(), dto.getStatus());
        assertEquals(order.getTotalCost(), dto.getTotalCost());
        assertNotNull(dto.getCustomer());
        assertNotNull(dto.getFoodItems());
        CustomerOrder back = mapper.customerOrderToDomain(dto);
        assertNotNull(back);
        assertEquals(order.getId(), back.getId());
        assertEquals(order.getStatus(), back.getStatus());
    }

    @Test
    void testCustomerInOrderToDTOAndBack() {
        CustomerOrderCustomer customer = new CustomerOrderCustomer();
        customer.setId(1);
        customer.setName("Nome");
        CustomerOrderCustomerDTO dto = mapper.customerInOrderToDTO(customer);
        assertNotNull(dto);
        assertEquals(customer.getId(), dto.getId());
        assertEquals(customer.getName(), dto.getName());
        CustomerOrderCustomer back = mapper.customerInOrderToDomain(dto);
        assertNotNull(back);
        assertEquals(customer.getId(), back.getId());
        assertEquals(customer.getName(), back.getName());
    }

    @Test
    void testFoodItemInOrderToDTOAndBack() {
        CustomerOrderFoodItem item = new CustomerOrderFoodItem();
        item.setId(1);
        item.setName("Lanche");
        item.setDescription("desc");
        item.setPrice(5.0);
        item.setNotes("obs");
        CustomerOrderFoodItemDTO dto = mapper.foodItemInOrderToDTO(item);
        assertNotNull(dto);
        assertEquals(item.getId(), dto.getId());
        assertEquals(item.getName(), dto.getName());
        assertEquals(item.getDescription(), dto.getDescription());
        assertEquals(item.getPrice(), dto.getPrice());
        assertEquals(item.getNotes(), dto.getNotes());
        CustomerOrderFoodItem back = mapper.foodItemInOrderToDomain(dto);
        assertNotNull(back);
        assertEquals(item.getId(), back.getId());
        assertEquals(item.getName(), back.getName());
    }
}

