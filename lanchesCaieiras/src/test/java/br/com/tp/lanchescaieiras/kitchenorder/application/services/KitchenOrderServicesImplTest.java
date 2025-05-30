package br.com.tp.lanchescaieiras.kitchenorder.application.services;

import br.com.tp.lanchescaieiras.commons.adapters.outbound.integrations.CustomerOrderIntegrationImpl;
import br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.repositories.JpaKitchenOrderFoodItemRepositoryImpl;
import br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.repositories.JpaKitchenOrderRepositoryImpl;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrder;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderFoodItem;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderStatus;
import br.com.tp.lanchescaieiras.kitchenorder.infraestructure.exceptions.KitchenOrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KitchenOrderServicesImplTest {

    private JpaKitchenOrderRepositoryImpl kitchenOrderRepository;
    private JpaKitchenOrderFoodItemRepositoryImpl foodItemRepository;
    private CustomerOrderIntegrationImpl customerOrderIntegration;
    private ApplicationEventPublisher eventPublisher;
    private KitchenOrderServicesImpl service;

    @BeforeEach
    void setUp() {
        kitchenOrderRepository = mock(JpaKitchenOrderRepositoryImpl.class);
        foodItemRepository = mock(JpaKitchenOrderFoodItemRepositoryImpl.class);
        customerOrderIntegration = mock(CustomerOrderIntegrationImpl.class);
        eventPublisher = mock(ApplicationEventPublisher.class);
        service = new KitchenOrderServicesImpl(
                kitchenOrderRepository,
                foodItemRepository,
                customerOrderIntegration,
                eventPublisher
        );
    }

    @Test
    void testCreateKitchenOrderSuccess() {
        KitchenOrder order = new KitchenOrder();
        order.setCustomerOrderId(1);
        order.setStatus(KitchenOrderStatus.RECEIVED.getDescription());
        List<KitchenOrderFoodItem> items = new ArrayList<>();
        KitchenOrderFoodItem item = new KitchenOrderFoodItem();
        items.add(item);
        order.setFoodItems(items);

        when(kitchenOrderRepository.findByCustomerOrderId(1)).thenReturn(null);
        when(kitchenOrderRepository.save(any())).thenAnswer(inv -> {
            KitchenOrder o = inv.getArgument(0);
            o.setId(10);
            return o;
        });
        when(foodItemRepository.save(any(), anyInt())).thenReturn(item);

        KitchenOrder created = service.createKitchenOrder(order);

        assertNotNull(created);
        assertEquals(10, created.getId());
        verify(eventPublisher, times(1)).publishEvent(any());
    }

    @Test
    void testCreateKitchenOrderAlreadyExists() {
        KitchenOrder order = new KitchenOrder();
        order.setCustomerOrderId(1);
        when(kitchenOrderRepository.findByCustomerOrderId(1)).thenReturn(new KitchenOrder());

        assertThrows(KitchenOrderException.class, () -> service.createKitchenOrder(order));
    }

    @Test
    void testFindByIdNotFound() {
        when(kitchenOrderRepository.findById(99)).thenReturn(null);
        assertThrows(KitchenOrderException.class, () -> service.findById(99, false));
    }

    @Test
    void testFindByIdWithFoodItems() {
        KitchenOrder order = new KitchenOrder();
        order.setId(5);
        when(kitchenOrderRepository.findById(5)).thenReturn(order);
        when(foodItemRepository.findByKitchenOrderId(5)).thenReturn(Collections.emptyList());

        KitchenOrder result = service.findById(5, true);
        assertNotNull(result);
        assertEquals(5, result.getId());
        assertNotNull(result.getFoodItems());
    }

    @Test
    void testGetKitchenOrderByCustomerOrderByIdNotFound() {
        when(kitchenOrderRepository.findByCustomerOrderId(123)).thenReturn(null);
        assertThrows(KitchenOrderException.class, () -> service.getKitchenOrderByCustomerOrderById(123, false));
    }

    @Test
    void testFindByStatusWithFoodItems() {
        KitchenOrder order = new KitchenOrder();
        order.setId(7);
        when(kitchenOrderRepository.findByStatusId(anyInt())).thenReturn(List.of(order));
        when(foodItemRepository.findByKitchenOrderId(7)).thenReturn(Collections.emptyList());

        List<KitchenOrder> result = service.findByStatus(KitchenOrderStatus.RECEIVED.getDescription(), true);
        assertEquals(1, result.size());
        assertNotNull(result.get(0).getFoodItems());
    }

    @Test
    void testUpdateStatusByIdNotFound() {
        when(kitchenOrderRepository.findById(77)).thenReturn(null);
        assertThrows(KitchenOrderException.class, () -> service.updateStatusById(77, "Ready", false, false));
    }

    @Test
    void testUpdateStatusByIdForceUpdate() {
        KitchenOrder order = new KitchenOrder();
        order.setId(8);
        order.setStatus(KitchenOrderStatus.RECEIVED.getDescription());
        when(kitchenOrderRepository.findById(8)).thenReturn(order);
        when(kitchenOrderRepository.save(any())).thenReturn(order);

        KitchenOrder updated = service.updateStatusById(8, KitchenOrderStatus.PREPARING.getDescription(), true, false);
        assertNotNull(updated);
        assertEquals(KitchenOrderStatus.PREPARING.getDescription(), updated.getStatus());
    }

    @Test
    void testUpdateStatusByIdWithCustomerOrderUpdate() {
        KitchenOrder order = new KitchenOrder();
        order.setId(9);
        order.setCustomerOrderId(100);
        order.setStatus(KitchenOrderStatus.PREPARING.getDescription());
        when(kitchenOrderRepository.findById(9)).thenReturn(order);
        when(kitchenOrderRepository.save(any())).thenReturn(order);

        KitchenOrder updated = service.updateStatusById(9, KitchenOrderStatus.READY.getDescription(), true, true);
        assertNotNull(updated);
        verify(customerOrderIntegration, times(1)).updateCustomerOrderStatus(100, KitchenOrderStatus.READY.getDescription());
    }
}
