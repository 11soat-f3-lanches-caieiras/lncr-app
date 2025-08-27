package br.com.tp.lncr.core.adapters.customerorder;

import br.com.tp.lncr.core.applications.customerorder.GetCustomerOrderUseCase;
import br.com.tp.lncr.core.applications.customerorder.UpdateCustomerOrderUseCase;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lncr.core.commons.interfaces.customerorder.CustomerOrderDatabase;
import br.com.tp.lncr.core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrder;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrderFoodItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CustomerOrderControllerImplTest {
    private CustomerOrderDatabase customerOrderDatabase;
    private CustomerOrderControllerImpl controller;
    private CustomerOrderDTO customerOrderDTO;
    private CustomerOrderGateway customerOrderGateway;

    @BeforeEach
    void setUp() {
        customerOrderDatabase = mock(CustomerOrderDatabase.class);
        customerOrderGateway = mock(CustomerOrderGateway.class);
        controller = new CustomerOrderControllerImpl(customerOrderDatabase);

        // Criar DTO com itens de comida para satisfazer regras de negócio
        customerOrderDTO = new CustomerOrderDTO();
        customerOrderDTO.setId(1);
        customerOrderDTO.setStatus("RECEIVED");
        customerOrderDTO.setId(1);
        customerOrderDTO.setTotalCost(25.99);

        // Adicionar itens de comida para evitar erro "pedido deve conter pelo menos 1 item"
        List<CustomerOrderFoodItemDTO> foodItems = new ArrayList<>();
        CustomerOrderFoodItemDTO foodItem = new CustomerOrderFoodItemDTO();
        foodItem.setId(1);
        foodItems.add(foodItem);
        customerOrderDTO.setFoodItems(foodItems);
    }

    @Test
    void testCreate() {
        when(customerOrderDatabase.save(any(CustomerOrderDTO.class))).thenReturn(customerOrderDTO);

        CustomerOrderDTO result = controller.create(customerOrderDatabase, customerOrderDTO);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testGetById() {
        when(customerOrderDatabase.findCustomerOrderById(eq(1), eq(true))).thenReturn(customerOrderDTO);

        CustomerOrderDTO result = controller.getById(customerOrderDatabase, 1, true);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testGetByStatusList() {
        // Usar List<Integer> ao invés de List<String> para compatibilidade com a interface
        List<String> statusList = List.of("Checkout", "Received", "Preparing", "Ready", "Finished", "Cancelled");
        List<CustomerOrderFoodItem> foodItems = List.of(new CustomerOrderFoodItem(1,1,"Pizza","Pizza", 12.99, "PIZZA"));
        List<Integer> statusListIds = List.of(1, 2,3,4,5,6);
        CustomerOrder actualCustomerOrder = new CustomerOrder(1,"READY", 25.99, LocalDateTime.now(), null, null, foodItems);
        when(customerOrderGateway.getCustomerOrderByStatusList(eq(statusListIds), eq(true))).thenReturn(List.of(actualCustomerOrder));

        GetCustomerOrderUseCase getCustomerOrderUseCase = new GetCustomerOrderUseCase(customerOrderGateway);
        List<CustomerOrder> result = getCustomerOrderUseCase.getByStatusList(statusList, true);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateStatusById() {
        CustomerOrder actualCustomerOrder = new CustomerOrder(1,"READY", 25.99, null, null, null, null);
        CustomerOrder updatedCustomerOrder = new CustomerOrder(1,"FINISHED", 25.99, null, null, null, null);
        when(customerOrderGateway.getCustomerOrderById(1)).thenReturn(actualCustomerOrder);
        when(customerOrderGateway.updateCustomerOrder(any(CustomerOrder.class))).thenReturn(updatedCustomerOrder);

        UpdateCustomerOrderUseCase updateCustomerOrderUseCase = new UpdateCustomerOrderUseCase(customerOrderGateway);
        updatedCustomerOrder = updateCustomerOrderUseCase.updateStatusById(1,"FINISHED", false);
        assertNotNull(updatedCustomerOrder);
        assertEquals("FINISHED", updatedCustomerOrder.getStatus());
    }
}
