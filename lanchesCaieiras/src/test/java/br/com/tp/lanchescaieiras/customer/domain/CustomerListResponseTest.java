package br.com.tp.lanchescaieiras.customer.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerListResponseTest {

    @Test
    void noArgsConstructorCreatesEmptyObject() {
        CustomerListResponse response = new CustomerListResponse();
        assertNotNull(response);
        assertNull(response.get_response());
        assertNull(response.getCustomers());
    }

    @Test
    void allArgsConstructorInitializesFields() {
        ResponseMetada responseMetada = new ResponseMetada("trace123", "2023-01-01T10:00:00Z", "Success");
        List<Customer> customers = List.of(new Customer(), new Customer());
        CustomerListResponse response = new CustomerListResponse(responseMetada, customers);

        assertNotNull(response);
        assertEquals(responseMetada, response.get_response());
        assertEquals(customers, response.getCustomers());
    }

    @Test
    void settersAndGettersWorkCorrectly() {
        ResponseMetada responseMetada = new ResponseMetada("trace123", "2023-01-01T10:00:00Z", "Success");
        List<Customer> customers = List.of(new Customer(), new Customer());
        CustomerListResponse response = new CustomerListResponse();

        response.set_response(responseMetada);
        response.setCustomers(customers);

        assertEquals(responseMetada, response.get_response());
        assertEquals(customers, response.getCustomers());
    }

    @Test
    void settingNullValuesDoesNotThrowException() {
        CustomerListResponse response = new CustomerListResponse();

        response.set_response(null);
        response.setCustomers(null);

        assertNull(response.get_response());
        assertNull(response.getCustomers());
    }

    @Test
    void emptyCustomerListIsHandledCorrectly() {
        ResponseMetada responseMetada = new ResponseMetada("trace123", "2023-01-01T10:00:00Z", "Success");
        CustomerListResponse response = new CustomerListResponse(responseMetada, List.of());

        assertNotNull(response);
        assertEquals(responseMetada, response.get_response());
        assertTrue(response.getCustomers().isEmpty());
    }
}