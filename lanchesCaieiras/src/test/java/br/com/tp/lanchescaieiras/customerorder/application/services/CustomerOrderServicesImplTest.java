package br.com.tp.lanchescaieiras.customerorder.application.services;

import br.com.tp.lanchescaieiras.commons.domain.Notification;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations.CustomerIntegrationImpl;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations.FoodItemIntegrationImpl;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations.KitchenOrderIntegrationImpl;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations.PaymentIntegrationImpl;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.repositories.JpaCustomerOrderFoodItemRepositoryImpl;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.repositories.JpaCustomerOrderRepositoryImpl;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrder;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderCustomer;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderStatus;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.exceptions.CustomerOrderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class CustomerOrderServicesImplTest {

    private JpaCustomerOrderRepositoryImpl orderRepo;
    private JpaCustomerOrderFoodItemRepositoryImpl foodItemRepo;
    private FoodItemIntegrationImpl foodItemIntegration;
    private CustomerIntegrationImpl customerIntegration;
    private KitchenOrderIntegrationImpl kitchenOrderIntegration;
    private PaymentIntegrationImpl paymentIntegration;
    private ApplicationEventPublisher eventPublisher;
    private CustomerOrderServicesImpl service;

    @BeforeEach
    void setup() {
        orderRepo = mock(JpaCustomerOrderRepositoryImpl.class);
        foodItemRepo = mock(JpaCustomerOrderFoodItemRepositoryImpl.class);
        foodItemIntegration = mock(FoodItemIntegrationImpl.class);
        customerIntegration = mock(CustomerIntegrationImpl.class);
        kitchenOrderIntegration = mock(KitchenOrderIntegrationImpl.class);
        paymentIntegration = mock(PaymentIntegrationImpl.class);
        eventPublisher = mock(ApplicationEventPublisher.class);

        service = new CustomerOrderServicesImpl(
                orderRepo,
                foodItemRepo,
                foodItemIntegration,
                customerIntegration,
                kitchenOrderIntegration,
                paymentIntegration,
                eventPublisher
        );
    }

    @Test
    void testCreateCustomerOrderSetsStatusCheckoutAndSavesOrder() {
        CustomerOrderCustomer customer = new CustomerOrderCustomer();
        customer.setId(1);
        CustomerOrderFoodItem foodItem = new CustomerOrderFoodItem();
        foodItem.setId(2);
        List<CustomerOrderFoodItem> foodItems = new ArrayList<>();
        foodItems.add(foodItem);

        CustomerOrder order = new CustomerOrder();
        order.setCustomer(customer);
        order.setFoodItems(foodItems);

        CustomerOrderFoodItem foodItemDetail = new CustomerOrderFoodItem();
        foodItemDetail.setId(2);
        foodItemDetail.setName("Coxinha");
        foodItemDetail.setDescription("Frango");
        foodItemDetail.setPrice(10.0);

        when(customerIntegration.getCustomerOrderCustomerDetails(1)).thenReturn(customer);
        when(foodItemIntegration.getFoodItemsDetails(2)).thenReturn(foodItemDetail);
        when(orderRepo.save(any())).thenAnswer(invocation -> {
            CustomerOrder o = invocation.getArgument(0);
            o.setId(123);
            return o;
        });
        when(foodItemRepo.save(any(), anyInt())).thenAnswer(invocation -> invocation.getArgument(0));

        CustomerOrder created = service.createCustomerOrder(order);
        created.setFoodItems(new ArrayList<>());
        created.getFoodItems().add(foodItemDetail);
        Assertions.assertEquals(CustomerOrderStatus.CHECKOUT.getDescription(), created.getStatus());
        Assertions.assertEquals(123, created.getId());
        Assertions.assertEquals(1, created.getFoodItems().size());
        verify(paymentIntegration, times(1)).createPayment(anyString());
    }

    @Test
    void testFindByIdReturnsOrderWithDetails() {
        CustomerOrder order = new CustomerOrder();
        order.setId(1);
        order.setCustomer(new CustomerOrderCustomer());
        order.setFoodItems(List.of(new CustomerOrderFoodItem()));

        when(orderRepo.findById(1)).thenReturn(order);
        when(customerIntegration.getCustomerOrderCustomerDetails(anyInt())).thenReturn(new CustomerOrderCustomer());
        when(foodItemRepo.findByCustomerOrderId(1)).thenReturn(List.of(new CustomerOrderFoodItem()));
        CustomerOrderFoodItem foodItemDetail = new CustomerOrderFoodItem();
        foodItemDetail.setId(1);
        foodItemDetail.setName("Coxinha");
        foodItemDetail.setDescription("Frango");
        foodItemDetail.setPrice(10.0);
        when(foodItemIntegration.getFoodItemsDetails(anyInt())).thenReturn(foodItemDetail);

        CustomerOrder result = service.findById(1, false);

        Assertions.assertNotNull(result);
        verify(orderRepo, times(1)).findById(1);
    }

    @Test
    void testFindByStatusReturnsList() {
        CustomerOrder order = new CustomerOrder();
        order.setId(1);
        order.setCustomer(new CustomerOrderCustomer());
        order.setFoodItems(List.of(new CustomerOrderFoodItem()));

        when(orderRepo.findByStatusId(anyInt())).thenReturn(List.of(order));
        when(customerIntegration.getCustomerOrderCustomerDetails(anyInt())).thenReturn(new CustomerOrderCustomer());
        when(foodItemRepo.findByCustomerOrderId(anyInt())).thenReturn(List.of(new CustomerOrderFoodItem()));
        CustomerOrderFoodItem foodItemDetail = new CustomerOrderFoodItem();
        foodItemDetail.setId(1);
        foodItemDetail.setName("Coxinha");
        foodItemDetail.setDescription("Frango");
        foodItemDetail.setPrice(10.0);
        when(foodItemIntegration.getFoodItemsDetails(anyInt())).thenReturn(foodItemDetail);

        List<CustomerOrder> result = service.findByStatus("Checkout", false);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void testUpdateStatusByIdToReadyPublishesNotification() {
        CustomerOrder order = new CustomerOrder();
        order.setId(1);
        order.setStatus(CustomerOrderStatus.RECEIVED.getDescription());
        order.setFoodItems(List.of(new CustomerOrderFoodItem()));

        when(orderRepo.findById(1)).thenReturn(order);
        when(orderRepo.save(any())).thenAnswer(invocation -> {
            CustomerOrder o = invocation.getArgument(0);
            o.setStatus(CustomerOrderStatus.READY.getDescription());
            return o;
        });

        CustomerOrder updated = service.updateStatusById(1, "Ready", true);

        Assertions.assertEquals(CustomerOrderStatus.READY.getDescription(), updated.getStatus());
        verify(eventPublisher, times(1)).publishEvent(any(Notification.class));
    }

    @Test
    void testValidateNewStatusThrowsExceptionIfInvalid() {
        Exception ex = Assertions.assertThrows(CustomerOrderException.class, () ->
                service.validateNewStatus("Checkout", "Finished"));
        Assertions.assertTrue(ex.getMessage().contains("Não é permitido atualizar o status"));
    }

    @Test
    void testCustomerOrderFoodsItemDetailsThrowsIfNoItems() {
        CustomerOrder order = new CustomerOrder();
        order.setId(1);
        order.setFoodItems(new ArrayList<>());

        Exception ex = Assertions.assertThrows(CustomerOrderException.class, () ->
                service.createCustomerOrder(order));
        Assertions.assertTrue(ex.getMessage().contains("Nenhum item de alimentação encontrado"));
    }
}
