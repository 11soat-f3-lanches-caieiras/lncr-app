package br.com.tp.lanchescaieiras.customerorder.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrderCustomerTest {

    @Test
    void testConstructorAndGetters() {
        CustomerOrderCustomer customer = new CustomerOrderCustomer(1, "Maria");
        assertEquals(1, customer.getId());
        assertEquals("Maria", customer.getName());
    }

    @Test
    void testSetters() {
        CustomerOrderCustomer customer = new CustomerOrderCustomer();
        customer.setId(2);
        customer.setName("José");
        assertEquals(2, customer.getId());
        assertEquals("José", customer.getName());
    }
}
