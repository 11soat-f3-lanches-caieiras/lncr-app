package br.com.tp.lanchescaieiras.customer.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class CustomerListResponseTest {

    @DisplayName("Should create CustomerListResponse with response metadata and customers")
    @Test
    void createWithResponseAndCustomers() {
        ResponseMetada responseMetada = new ResponseMetada();
        List<Customer> customers = List.of(new Customer());
        CustomerListResponse response = new CustomerListResponse(responseMetada, customers);

        Assertions.assertEquals(responseMetada, response.get_response());
        Assertions.assertEquals(customers, response.getCustomers());
    }

    @DisplayName("Should create CustomerListResponse with only customers and default response metadata")
    @Test
    void createWithOnlyCustomers() {
        List<Customer> customers = List.of(new Customer());
        CustomerListResponse response = new CustomerListResponse(customers);

        Assertions.assertNotNull(response.get_response());
        Assertions.assertEquals(customers, response.getCustomers());
    }

    @DisplayName("Should handle empty customer list")
    @Test
    void handleEmptyCustomerList() {
        List<Customer> customers = Collections.emptyList();
        CustomerListResponse response = new CustomerListResponse(customers);

        Assertions.assertNotNull(response.get_response());
        Assertions.assertTrue(response.getCustomers().isEmpty());
    }

    @DisplayName("Should set and get response metadata")
    @Test
    void setAndGetResponseMetadata() {
        ResponseMetada responseMetada = new ResponseMetada();
        CustomerListResponse response = new CustomerListResponse();
        response.set_response(responseMetada);

        Assertions.assertEquals(responseMetada, response.get_response());
    }

    @DisplayName("Should set and get customers")
    @Test
    void setAndGetCustomers() {
        List<Customer> customers = List.of(new Customer());
        CustomerListResponse response = new CustomerListResponse();
        response.setCustomers(customers);

        Assertions.assertEquals(customers, response.getCustomers());
    }
}