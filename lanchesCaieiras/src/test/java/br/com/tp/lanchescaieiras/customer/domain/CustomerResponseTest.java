package br.com.tp.lanchescaieiras.customer.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerResponseTest {

    @Test
    void testNoArgsConstructor() {
        CustomerResponse response = new CustomerResponse();
        assertNotNull(response);
        assertNull(response.get_response());
        assertNull(response.getCustomer());
    }

    @Test
    void testAllArgsConstructor() {
        ResponseMetada responseMetada = new ResponseMetada("trace123", "2023-01-01T10:00:00Z", "Success");
        Customer customer = new Customer();
        CustomerResponse response = new CustomerResponse(responseMetada, customer);

        assertNotNull(response);
        assertEquals(responseMetada, response.get_response());
        assertEquals(customer, response.getCustomer());
    }

    @Test
    void testSettersAndGetters() {
        ResponseMetada responseMetada = new ResponseMetada("trace123", "2023-01-01T10:00:00Z", "Success");
        Customer customer = new Customer();
        CustomerResponse response = new CustomerResponse();

        response.set_response(responseMetada);
        response.setCustomer(customer);

        assertEquals(responseMetada, response.get_response());
        assertEquals(customer, response.getCustomer());
    }
}