package br.com.tp.lncr.core.adapters.customerorder;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lncr.core.commons.interfaces.customerorder.CustomerOrderDatabase;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrder;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrderCustomer;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrderFoodItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CustomerOrderGatewayImplTest {
    private CustomerOrderDatabase customerOrderDatabase;
    private CustomerOrderMapper customerOrderMapper;
    private CustomerOrderGatewayImpl gateway;

    @BeforeEach
    void setUp() {
        customerOrderDatabase = mock(CustomerOrderDatabase.class);
        customerOrderMapper = mock(CustomerOrderMapper.class);
        gateway = new CustomerOrderGatewayImpl(customerOrderDatabase, customerOrderMapper);
    }

    @Test
    void testCreateCustomerOrder() {
        CustomerOrder order = new CustomerOrder();
        CustomerOrderDTO dto = new CustomerOrderDTO();
        when(customerOrderMapper.customerOrderToDTO(order)).thenReturn(dto);
        when(customerOrderDatabase.save(dto)).thenReturn(dto);
        when(customerOrderMapper.customerOrderToDomain(dto)).thenReturn(order);
        CustomerOrder result = gateway.createCustomerOrder(order);
        assertNotNull(result);
    }

    @Test
    void testGetCustomerDetailsList() {
        List<Integer> ids = List.of(1,2);
        CustomerOrderCustomerDTO dto = new CustomerOrderCustomerDTO();
        when(customerOrderDatabase.findCustomerDetailsList(ids)).thenReturn(List.of(dto));
        when(customerOrderMapper.customerInOrderToDomain(dto)).thenReturn(new CustomerOrderCustomer());
        List<CustomerOrderCustomer> result = gateway.getCustomerDetailsList(ids);
        assertNotNull(result);
    }

    @Test
    void testGetCustomerDetails() {
        CustomerOrderCustomerDTO dto = new CustomerOrderCustomerDTO();
        when(customerOrderDatabase.findCustomerDetails(1)).thenReturn(dto);
        when(customerOrderMapper.customerInOrderToDomain(dto)).thenReturn(new CustomerOrderCustomer());
        CustomerOrderCustomer result = gateway.getCustomerDetails(1);
        assertNotNull(result);
    }

    @Test
    void testGetFoodItemsDetails() {
        List<Integer> ids = List.of(1,2);
        CustomerOrderFoodItem item = new CustomerOrderFoodItem();
        when(customerOrderDatabase.findFoodItemsDetailsList(ids)).thenReturn(List.of(new br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderFoodItemDTO()));
        when(customerOrderMapper.foodItemInOrderToDomain(any())).thenReturn(item);
        List<CustomerOrderFoodItem> result = gateway.getFoodItemsDetails(ids);
        assertNotNull(result);
    }

    @Test
    void testCreatePaymentCharge() {
        CustomerOrder order = mock(CustomerOrder.class);
        when(order.getId()).thenReturn(1);
        when(order.getTotalCost()).thenReturn(10.0);
        doNothing().when(customerOrderDatabase).createPaymentCharge(1, 10.0);
        gateway.createPaymentCharge(order);
        verify(customerOrderDatabase).createPaymentCharge(1, 10.0);
    }

    @Test
    void testSendNotification() {
        doNothing().when(customerOrderDatabase).sendNotification(anyString(), anyInt(), anyString());
        gateway.sendNotification("type", 1, "msg");
        verify(customerOrderDatabase).sendNotification("type", 1, "msg");
    }

    @Test
    void testGetCustomerOrderById() {
        CustomerOrderDTO dto = new CustomerOrderDTO();
        when(customerOrderDatabase.findCustomerOrderById(1, false)).thenReturn(dto);
        when(customerOrderMapper.customerOrderToDomain(dto)).thenReturn(new CustomerOrder());
        CustomerOrder result = gateway.getCustomerOrderById(1);
        assertNotNull(result);
    }

    @Test
    void testGetCustomerOrderByIdWithFoodItems() {
        CustomerOrderDTO dto = new CustomerOrderDTO();
        when(customerOrderDatabase.findCustomerOrderById(1, true)).thenReturn(dto);
        when(customerOrderMapper.customerOrderToDomain(dto)).thenReturn(new CustomerOrder());
        CustomerOrder result = gateway.getCustomerOrderById(1, true);
        assertNotNull(result);
    }

    @Test
    void testGetCustomerOrderByStatusList() {
        List<Integer> ids = List.of(1,2);
        CustomerOrderDTO dto = new CustomerOrderDTO();
        when(customerOrderDatabase.findCustomerOrderByStatusList(ids, true)).thenReturn(List.of(dto));
        when(customerOrderMapper.customerOrderToDomain(dto)).thenReturn(new CustomerOrder());
        List<CustomerOrder> result = gateway.getCustomerOrderByStatusList(ids, true);
        assertNotNull(result);
    }

    @Test
    void testUpdateCustomerOrder() {
        CustomerOrder order = new CustomerOrder();
        CustomerOrderDTO dto = new CustomerOrderDTO();
        when(customerOrderMapper.customerOrderToDTO(order)).thenReturn(dto);
        when(customerOrderDatabase.updateCustomerOrder(dto)).thenReturn(dto);
        when(customerOrderMapper.customerOrderToDomain(dto)).thenReturn(order);
        CustomerOrder result = gateway.updateCustomerOrder(order);
        assertNotNull(result);
    }

    @Test
    void testCreateKitchenOrder() {
        CustomerOrder order = new CustomerOrder();
        CustomerOrderDTO dto = new CustomerOrderDTO();
        when(customerOrderMapper.customerOrderToDTO(order)).thenReturn(dto);
        doNothing().when(customerOrderDatabase).createKitchenOrder(dto);
        gateway.createKitchenOrder(order);
        verify(customerOrderDatabase).createKitchenOrder(dto);
    }

    @Test
    void testCancelPaymentChargeByCustomerOrderId() {
        doNothing().when(customerOrderDatabase).cancelPaymentChargeByCustomerOrderId(1);
        gateway.cancelPaymentChargeByCustomerOrderId(1);
        verify(customerOrderDatabase).cancelPaymentChargeByCustomerOrderId(1);
    }

    @Test
    void testGetPaymentByCustomerOrderId() {
        PaymentMercadopagoQrDTO dto = new PaymentMercadopagoQrDTO();
        when(customerOrderDatabase.findPaymentByCustomerOrderId(1)).thenReturn(dto);
        PaymentMercadopagoQrDTO result = gateway.getPaymentByCustomerOrderId(1);
        assertNotNull(result);
    }

    @Test
    void testCancelKitchenOrderById() {
        doNothing().when(customerOrderDatabase).cancelKitchenOrderById(1);
        gateway.cancelKitchenOrderById(1);
        verify(customerOrderDatabase).cancelKitchenOrderById(1);
    }

    @Test
    void testGetKitchenOrderByCustomerOrderId() {
        KitchenOrderDTO dto = new KitchenOrderDTO();
        when(customerOrderDatabase.findKitchenOrderByCustomerOrderId(1)).thenReturn(dto);
        KitchenOrderDTO result = gateway.getKitchenOrderByCustomerOrderId(1);
        assertNotNull(result);
    }
}

