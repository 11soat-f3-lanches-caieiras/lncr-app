package br.com.tp.lanchescaieiras.customer.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetadata;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import br.com.tp.lanchescaieiras.customer.domain.shared.CustomerResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerResponseTest {

    @Test
    void testConstructorAndGetters() {
        ResponseMetadata meta = new ResponseMetadata();
        Customer customer = new Customer(1, "12345678909", "João", "joao@email.com");
        CustomerResponse response = new CustomerResponse(meta, customer);
        assertEquals(meta, response.get_response());
        assertEquals(customer, response.get_content());
    }

    @Test
    void testSetters() {
        CustomerResponse response = new CustomerResponse();
        ResponseMetadata meta = new ResponseMetadata();
        Customer customer = new Customer();
        response.set_response(meta);
        response.set_content(customer);
        assertEquals(meta, response.get_response());
        assertEquals(customer, response.get_content());
    }

    @Test
    void testConstructorWithContentOnly() {
        Customer customer = new Customer();
        CustomerResponse response = new CustomerResponse(customer);
        assertNotNull(response.get_response());
        assertEquals(customer, response.get_content());
    }
}
