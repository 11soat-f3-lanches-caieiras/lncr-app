package br.com.tp.lanchescaieiras.kitchenorder.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.kitchenorder.application.services.KitchenOrderServicesImpl;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrder;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderListResponse;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderResponse;
import br.com.tp.lanchescaieiras.kitchenorder.infraestructure.config.KitchenOrderConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KitchenOrderControllerImplTest {

    private KitchenOrderServicesImpl kitchenOrderServices;
    private KitchenOrderConfig kitchenOrderConfig;
    private KitchenOrderControllerImpl controller;

    @BeforeEach
    void setUp() {
        kitchenOrderServices = mock(KitchenOrderServicesImpl.class);
        kitchenOrderConfig = mock(KitchenOrderConfig.class);
        controller = new KitchenOrderControllerImpl(kitchenOrderServices, kitchenOrderConfig, kitchenOrderConfig);
    }

    @Test
    void testCreateKitchenOrder() {
        KitchenOrder order = new KitchenOrder();
        order.setId(1);
        when(kitchenOrderServices.createKitchenOrder(any())).thenReturn(order);
        when(kitchenOrderConfig.getLocationPrefix()).thenReturn("/kitchenOrders");

        ResponseEntity<KitchenOrderResponse> response = controller.createKitchenOrder(order);

        assertEquals(201, response.getStatusCodeValue());
        assertTrue(response.getHeaders().containsKey("Location"));
    }

    @Test
    void testGetKitchenOrderById() {
        KitchenOrder order = new KitchenOrder();
        when(kitchenOrderServices.findById(anyInt(), anyBoolean())).thenReturn(order);

        ResponseEntity<KitchenOrderResponse> response = controller.getKitchenOrderById(1, true);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetKitchenOrderByCustomerOrderId() {
        KitchenOrder order = new KitchenOrder();
        when(kitchenOrderServices.getKitchenOrderByCustomerOrderById(anyInt(), anyBoolean())).thenReturn(order);

        ResponseEntity<KitchenOrderResponse> response = controller.getKitchenOrderByCustomerOrderId(1, true);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetKitchenOrderByStatusFound() {
        when(kitchenOrderServices.findByStatus(anyString(), anyBoolean())).thenReturn(Collections.emptyList());

        ResponseEntity<KitchenOrderListResponse> response = controller.getKitchenOrderByStatus("Received", true);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetKitchenOrderByStatusNotFound() {
        when(kitchenOrderServices.findByStatus(anyString(), anyBoolean())).thenReturn(null);

        ResponseEntity<KitchenOrderListResponse> response = controller.getKitchenOrderByStatus("Received", true);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testUpdateOrderStatusById() {
        KitchenOrder order = new KitchenOrder();
        when(kitchenOrderServices.updateStatusById(anyInt(), anyString(), anyBoolean(), anyBoolean())).thenReturn(order);

        ResponseEntity<KitchenOrderResponse> response = controller.updateOrderStatusById(1, "Ready", false, true);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}
