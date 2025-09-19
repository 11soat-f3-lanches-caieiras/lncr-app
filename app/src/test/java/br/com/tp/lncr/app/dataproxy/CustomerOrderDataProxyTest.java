package br.com.tp.lncr.app.dataproxy;

import br.com.tp.lncr.app.datasources.postgres.customerorder.*;
import br.com.tp.lncr.app.integrations.customer.CustomerIntegrationImpl;
import br.com.tp.lncr.app.integrations.fooditem.FoodItemIntegrationImpl;
import br.com.tp.lncr.app.integrations.kitchenorder.KitchenOrderIntegrationImpl;
import br.com.tp.lncr.app.integrations.notifcation.NotificationIntegraionImpl;
import br.com.tp.lncr.app.integrations.payment.PaymentIntegrationImpl;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerOrderDataProxyTest {

    @Mock
    private JpaCustomerOrderRepositoryImpl jpaCustomerOrderRepositoryImpl;
    @Mock
    private JpaCustomerOrderRepository jpaCustomerOrderRepository;
    @Mock
    private JpaCustomerOrderFoodItemRepositoryImpl jpaCustomerOrderFoodItemRepositoryImpl;
    @Mock
    private JpaCustomerOrderFoodItemRepository jpaCustomerOrderFoodItemRepository;
    @Mock
    private CustomerIntegrationImpl customerIntegration;
    @Mock
    private FoodItemIntegrationImpl foodItemIntegration;
    @Mock
    private PaymentIntegrationImpl paymentIntegration;
    @Mock
    private KitchenOrderIntegrationImpl kitchenOrderIntegration;
    @Mock
    private NotificationIntegraionImpl notificationIntegration;
    @Mock
    private JpaCustomerOrderMapper jpaCustomerOrderMapper;

    private CustomerOrderDataProxy customerOrderDataProxy;

    @BeforeEach
    void setUp() {
        customerOrderDataProxy = new CustomerOrderDataProxy(
                jpaCustomerOrderRepositoryImpl,
                jpaCustomerOrderRepository,
                jpaCustomerOrderFoodItemRepositoryImpl,
                jpaCustomerOrderFoodItemRepository,
                customerIntegration,
                foodItemIntegration,
                paymentIntegration,
                kitchenOrderIntegration,
                notificationIntegration,
                jpaCustomerOrderMapper
        );
    }

    @Test
    void shouldReturnFoodItemDetailsListWhenValidIds() {
        List<Integer> foodItemIds = Arrays.asList(1, 2, 3);
        List<CustomerOrderFoodItemDTO> expectedItems = Arrays.asList(
                new CustomerOrderFoodItemDTO(),
                new CustomerOrderFoodItemDTO()
        );

        when(foodItemIntegration.getFoodItemDetailList(foodItemIds)).thenReturn(expectedItems);

        List<CustomerOrderFoodItemDTO> result = customerOrderDataProxy.findFoodItemsDetailsList(foodItemIds);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(foodItemIntegration).getFoodItemDetailList(foodItemIds);
    }

    @Test
    void shouldReturnEmptyListWhenNoFoodItemIdsProvided() {
        List<Integer> emptyIds = List.of();
        List<CustomerOrderFoodItemDTO> expectedItems = List.of();

        when(foodItemIntegration.getFoodItemDetailList(emptyIds)).thenReturn(expectedItems);

        List<CustomerOrderFoodItemDTO> result = customerOrderDataProxy.findFoodItemsDetailsList(emptyIds);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(foodItemIntegration).getFoodItemDetailList(emptyIds);
    }

    @Test
    void shouldReturnCustomerDetailsListWhenValidIds() {
        List<Integer> customerIds = Arrays.asList(1, 2);
        List<CustomerOrderCustomerDTO> expectedCustomers = Arrays.asList(
                new CustomerOrderCustomerDTO(),
                new CustomerOrderCustomerDTO()
        );

        when(customerIntegration.getCustomerDetailsList(customerIds)).thenReturn(expectedCustomers);

        List<CustomerOrderCustomerDTO> result = customerOrderDataProxy.findCustomerDetailsList(customerIds);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(customerIntegration).getCustomerDetailsList(customerIds);
    }

    @Test
    void shouldReturnCustomerDetailsWhenValidId() {
        Integer customerId = 1;
        CustomerOrderCustomerDTO expectedCustomer = new CustomerOrderCustomerDTO();

        when(customerIntegration.getCustomerDetails(customerId)).thenReturn(expectedCustomer);

        CustomerOrderCustomerDTO result = customerOrderDataProxy.findCustomerDetails(customerId);

        assertNotNull(result);
        verify(customerIntegration).getCustomerDetails(customerId);
    }

    @Test
    void shouldSaveCustomerOrderWithFoodItems() {
        CustomerOrderDTO orderDTO = new CustomerOrderDTO();
        orderDTO.setId(1);
        CustomerOrderFoodItemDTO foodItem = new CustomerOrderFoodItemDTO();
        orderDTO.setFoodItems(List.of(foodItem));

        CustomerOrderDTO savedOrder = new CustomerOrderDTO();
        savedOrder.setId(1);

        CustomerOrderFoodItemDTO savedFoodItem = new CustomerOrderFoodItemDTO();
        savedFoodItem.setOrderId(1);

        when(jpaCustomerOrderRepositoryImpl.save(any(), eq(jpaCustomerOrderRepository), eq(jpaCustomerOrderMapper)))
                .thenReturn(savedOrder);
        when(jpaCustomerOrderFoodItemRepositoryImpl.saveAll(any(), eq(jpaCustomerOrderFoodItemRepository), eq(jpaCustomerOrderMapper)))
                .thenReturn(List.of(savedFoodItem));

        CustomerOrderDTO result = customerOrderDataProxy.save(orderDTO);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertNotNull(result.getFoodItems());
        assertEquals(1, result.getFoodItems().get(0).getOrderId());
        verify(jpaCustomerOrderRepositoryImpl).save(any(), eq(jpaCustomerOrderRepository), eq(jpaCustomerOrderMapper));
        verify(jpaCustomerOrderFoodItemRepositoryImpl).saveAll(any(), eq(jpaCustomerOrderFoodItemRepository), eq(jpaCustomerOrderMapper));
    }

    @Test
    void shouldCreatePaymentChargeWithValidParameters() {
        Integer customerOrderId = 1;
        Double totalCost = 25.50;

        customerOrderDataProxy.createPaymentCharge(customerOrderId, totalCost);

        verify(paymentIntegration).createPayment(customerOrderId, totalCost);
    }

    @Test
    void shouldSendNotificationWithValidParameters() {
        String notificationSource = "ORDER_UPDATE";
        Integer artefactId = 1;
        String message = "Order status updated";

        customerOrderDataProxy.sendNotification(notificationSource, artefactId, message);

        verify(notificationIntegration).sendNotification(notificationSource, artefactId, message);
    }

    @Test
    void shouldFindCustomerOrderByIdWithoutFoodItems() {
        Integer customerOrderId = 1;
        Boolean includeFoodItems = false;
        CustomerOrderDTO expectedOrder = new CustomerOrderDTO();
        expectedOrder.setId(customerOrderId);

        when(jpaCustomerOrderRepositoryImpl.findById(customerOrderId, jpaCustomerOrderRepository, jpaCustomerOrderMapper))
                .thenReturn(expectedOrder);

        CustomerOrderDTO result = customerOrderDataProxy.findCustomerOrderById(customerOrderId, includeFoodItems);

        assertNotNull(result);
        assertEquals(customerOrderId, result.getId());
        verify(jpaCustomerOrderRepositoryImpl).findById(customerOrderId, jpaCustomerOrderRepository, jpaCustomerOrderMapper);
        verify(jpaCustomerOrderFoodItemRepositoryImpl, never()).findByCustomerOrderId(any(), any(), any());
    }

    @Test
    void shouldFindCustomerOrderByIdWithFoodItems() {
        Integer customerOrderId = 1;
        Boolean includeFoodItems = true;
        CustomerOrderDTO expectedOrder = new CustomerOrderDTO();
        expectedOrder.setId(customerOrderId);
        List<CustomerOrderFoodItemDTO> foodItems = List.of(new CustomerOrderFoodItemDTO());

        when(jpaCustomerOrderRepositoryImpl.findById(customerOrderId, jpaCustomerOrderRepository, jpaCustomerOrderMapper))
                .thenReturn(expectedOrder);
        when(jpaCustomerOrderFoodItemRepositoryImpl.findByCustomerOrderId(customerOrderId, jpaCustomerOrderFoodItemRepository, jpaCustomerOrderMapper))
                .thenReturn(foodItems);

        CustomerOrderDTO result = customerOrderDataProxy.findCustomerOrderById(customerOrderId, includeFoodItems);

        assertNotNull(result);
        assertEquals(customerOrderId, result.getId());
        assertNotNull(result.getFoodItems());
        assertEquals(1, result.getFoodItems().size());
        verify(jpaCustomerOrderRepositoryImpl).findById(customerOrderId, jpaCustomerOrderRepository, jpaCustomerOrderMapper);
        verify(jpaCustomerOrderFoodItemRepositoryImpl).findByCustomerOrderId(customerOrderId, jpaCustomerOrderFoodItemRepository, jpaCustomerOrderMapper);
    }

    @Test
    void shouldReturnNullWhenCustomerOrderNotFound() {
        Integer customerOrderId = 999;
        Boolean includeFoodItems = true;

        when(jpaCustomerOrderRepositoryImpl.findById(customerOrderId, jpaCustomerOrderRepository, jpaCustomerOrderMapper))
                .thenReturn(null);

        CustomerOrderDTO result = customerOrderDataProxy.findCustomerOrderById(customerOrderId, includeFoodItems);

        assertNull(result);
        verify(jpaCustomerOrderRepositoryImpl).findById(customerOrderId, jpaCustomerOrderRepository, jpaCustomerOrderMapper);
        verify(jpaCustomerOrderFoodItemRepositoryImpl, never()).findByCustomerOrderId(any(), any(), any());
    }

    @Test
    void shouldFindCustomerOrdersByStatusListWithFoodItems() {
        List<Integer> statusIds = Arrays.asList(1, 2);
        Boolean includeFoodItems = true;

        CustomerOrderDTO order1 = new CustomerOrderDTO();
        order1.setId(1);
        CustomerOrderDTO order2 = new CustomerOrderDTO();
        order2.setId(2);
        List<CustomerOrderDTO> orders = Arrays.asList(order1, order2);

        CustomerOrderFoodItemDTO foodItem1 = new CustomerOrderFoodItemDTO();
        foodItem1.setOrderId(1);
        CustomerOrderFoodItemDTO foodItem2 = new CustomerOrderFoodItemDTO();
        foodItem2.setOrderId(2);
        List<CustomerOrderFoodItemDTO> foodItems = Arrays.asList(foodItem1, foodItem2);

        when(jpaCustomerOrderRepositoryImpl.findByStatusList(statusIds, jpaCustomerOrderRepository, jpaCustomerOrderMapper))
                .thenReturn(orders);
        when(jpaCustomerOrderFoodItemRepositoryImpl.findByCustomerOrderIdList(anyList(), eq(jpaCustomerOrderFoodItemRepository), eq(jpaCustomerOrderMapper)))
                .thenReturn(foodItems);

        List<CustomerOrderDTO> result = customerOrderDataProxy.findCustomerOrderByStatusList(statusIds, includeFoodItems);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertNotNull(result.get(0).getFoodItems());
        assertNotNull(result.get(1).getFoodItems());
        verify(jpaCustomerOrderRepositoryImpl).findByStatusList(statusIds, jpaCustomerOrderRepository, jpaCustomerOrderMapper);
        verify(jpaCustomerOrderFoodItemRepositoryImpl).findByCustomerOrderIdList(anyList(), eq(jpaCustomerOrderFoodItemRepository), eq(jpaCustomerOrderMapper));
    }

    @Test
    void shouldUpdateCustomerOrderSuccessfully() {
        CustomerOrderDTO updateOrderDTO = new CustomerOrderDTO();
        updateOrderDTO.setId(1);
        CustomerOrderDTO updatedOrder = new CustomerOrderDTO();
        updatedOrder.setId(1);

        when(jpaCustomerOrderRepositoryImpl.save(updateOrderDTO, jpaCustomerOrderRepository, jpaCustomerOrderMapper))
                .thenReturn(updatedOrder);

        CustomerOrderDTO result = customerOrderDataProxy.updateCustomerOrder(updateOrderDTO);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(jpaCustomerOrderRepositoryImpl).save(updateOrderDTO, jpaCustomerOrderRepository, jpaCustomerOrderMapper);
    }

    @Test
    void shouldCreateKitchenOrderSuccessfully() {
        CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO();

        customerOrderDataProxy.createKitchenOrder(customerOrderDTO);

        verify(kitchenOrderIntegration).createKitchenOrder(customerOrderDTO);
    }

    @Test
    void shouldCancelPaymentChargeByCustomerOrderId() {
        Integer customerOrderId = 1;

        customerOrderDataProxy.cancelPaymentChargeByCustomerOrderId(customerOrderId);

        verify(paymentIntegration).cancelPaymentChargeByCustomerOrderId(customerOrderId);
    }

    @Test
    void shouldFindPaymentByCustomerOrderId() {
        Integer customerOrderId = 1;
        PaymentMercadopagoQrDTO expectedPayment = new PaymentMercadopagoQrDTO();

        when(paymentIntegration.getPaymentByCustomerOrderId(customerOrderId)).thenReturn(expectedPayment);

        PaymentMercadopagoQrDTO result = customerOrderDataProxy.findPaymentByCustomerOrderId(customerOrderId);

        assertNotNull(result);
        verify(paymentIntegration).getPaymentByCustomerOrderId(customerOrderId);
    }

    @Test
    void shouldFindKitchenOrderByCustomerOrderId() {
        Integer customerOrderId = 1;
        KitchenOrderDTO expectedKitchenOrder = new KitchenOrderDTO();

        when(kitchenOrderIntegration.getKitchenOrderByCustomerOrderId(customerOrderId)).thenReturn(expectedKitchenOrder);

        KitchenOrderDTO result = customerOrderDataProxy.findKitchenOrderByCustomerOrderId(customerOrderId);

        assertNotNull(result);
        verify(kitchenOrderIntegration).getKitchenOrderByCustomerOrderId(customerOrderId);
    }

    @Test
    void shouldCancelKitchenOrderById() {
        Integer kitchenOrderId = 1;

        customerOrderDataProxy.cancelKitchenOrderById(kitchenOrderId);

        verify(kitchenOrderIntegration).cancelKitchenOrderById(kitchenOrderId);
    }
}
