package br.com.tp.lanchescaieiras.customerorder.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.customerorder.application.services.CustomerOrderServicesImpl;
import br.com.tp.lanchescaieiras.customerorder.domain.*;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.config.CustomerOrderConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerOrderControllerImplTest {

    private CustomerOrderServicesImpl service;
    private CustomerOrderConfig config;
    private CustomerOrderControllerImpl controller;

    @BeforeEach
    void setup() {
        service = mock(CustomerOrderServicesImpl.class);
        config = mock(CustomerOrderConfig.class);
        when(config.getLocationPrefix()).thenReturn("/customerOrders");
        controller = new CustomerOrderControllerImpl(service, config, config);
    }

    @Test
    void testCreateCustomerOrder() {
        CustomerOrder order = new CustomerOrder();
        order.setId(10);
        when(service.createCustomerOrder(any())).thenReturn(order);

        ResponseEntity<CustomerOrderResponse> response = controller.createCustomerOrder(order);

        assertEquals(201, response.getStatusCodeValue());
        assertTrue(response.getHeaders().get("Location").get(0).contains("/customerOrders/10"));
        assertNotNull(response.getBody());
    }

    @Test
    void testGetCustomerOrderById() {
        CustomerOrder order = new CustomerOrder();
        order.setId(1);
        when(service.findById(eq(1), anyBoolean())).thenReturn(order);

        ResponseEntity<CustomerOrderResponse> response = controller.getCustomerOrderById(1, true);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(order, response.getBody().get_content());
    }

    @Test
    void testGetCustomerOrderByStatusFound() {
        CustomerOrder order = new CustomerOrder();
        when(service.findByStatus(eq("Ready"), anyBoolean())).thenReturn(List.of(order));

        ResponseEntity<CustomerOrderListResponse> response = controller.getCustomerOrderByStatus("Ready", false);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().get_content().size());
    }

    @Test
    void testGetCustomerOrderByStatusNotFound() {
        when(service.findByStatus(eq("NotFound"), anyBoolean())).thenReturn(null);

        ResponseEntity<CustomerOrderListResponse> response = controller.getCustomerOrderByStatus("NotFound", false);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testUpdateOrderStatusById() {
        CustomerOrder order = new CustomerOrder();
        order.setId(5);
        when(service.updateStatusById(eq(5), eq("Finished"), eq(true))).thenReturn(order);

        ResponseEntity<CustomerOrderResponse> response = controller.updateOrderStatusById(5, "Finished", true);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(order, response.getBody().get_content());
    }
}
