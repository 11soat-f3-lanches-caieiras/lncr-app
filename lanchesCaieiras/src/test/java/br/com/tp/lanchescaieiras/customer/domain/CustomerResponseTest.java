package br.com.tp.lanchescaieiras.customer.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerResponseTest {

    @Test
    void createWithResponseAndCustomer() {
        ResponseMetada responseMetada = new ResponseMetada();
        Customer customer = new Customer();
        CustomerResponse response = new CustomerResponse(responseMetada, customer);

        Assertions.assertEquals(responseMetada, response.get_response());
        Assertions.assertEquals(customer, response.getCustomer());
    }

    @Test
    void createWithOnlyCustomer() {
        Customer customer = new Customer();
        CustomerResponse response = new CustomerResponse(customer);

        Assertions.assertNotNull(response.get_response());
        Assertions.assertEquals(customer, response.getCustomer());
    }

    @Test
    void handleNullCustomer() {
        CustomerResponse response = new CustomerResponse(null);

        Assertions.assertNotNull(response.get_response());
        Assertions.assertNull(response.getCustomer());
    }

    @Test
    void setAndGetResponseMetadata() {
        ResponseMetada responseMetada = new ResponseMetada();
        CustomerResponse response = new CustomerResponse();
        response.set_response(responseMetada);

        Assertions.assertEquals(responseMetada, response.get_response());
    }

    @Test
    void setAndGetCustomer() {
        Customer customer = new Customer();
        CustomerResponse response = new CustomerResponse();
        response.setCustomer(customer);

        Assertions.assertEquals(customer, response.getCustomer());
    }
}