package br.com.tp.lanchescaieiras.customerorder.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerOrderTest {

    @Test
    void testConstructorAndGetters() {
        CustomerOrderCustomer customer = new CustomerOrderCustomer(1, "João");
        CustomerOrderFoodItem item1 = new CustomerOrderFoodItem(1, "Coxinha", "Frango", 10.0, null);
        CustomerOrderFoodItem item2 = new CustomerOrderFoodItem(2, "Pastel", "Carne", 8.0, null);
        List<CustomerOrderFoodItem> items = List.of(item1, item2);

        CustomerOrder order = new CustomerOrder(1, CustomerOrderStatus.RECEIVED, customer, items, 18.0);

        assertEquals(1, order.getId());
        assertEquals("Received", order.getStatus());
        assertEquals(customer, order.getCustomer());
        assertEquals(items, order.getFoodItems());
        assertEquals(18.0, order.getTotalCost());
    }

    @Test
    void testSetters() {
        CustomerOrder order = new CustomerOrder();
        order.setId(2);
        order.setStatus("Ready");
        order.setTotalCost(20.0);

        assertEquals(2, order.getId());
        assertEquals("Ready", order.getStatus());
        assertEquals(20.0, order.getTotalCost());
    }

    @Test
    void testSetStatusEnum() {
        CustomerOrder order = new CustomerOrder();
        order.setStatus(CustomerOrderStatus.FINISHED);
        assertEquals("Finished", order.getStatus());
    }

    @Test
    void testSetTotalCostCalculatesCorrectly() {
        CustomerOrder order = new CustomerOrder();
        CustomerOrderFoodItem item1 = new CustomerOrderFoodItem(1,"Coxinha", "Frango", 10.0, null);
        CustomerOrderFoodItem item2 = new CustomerOrderFoodItem(2,  "Pastel", "Carne", 8.0, null);
        order.setFoodItems(List.of(item1, item2));
        order.setTotalCost();
        assertEquals(18.00, order.getTotalCost());
    }
}
