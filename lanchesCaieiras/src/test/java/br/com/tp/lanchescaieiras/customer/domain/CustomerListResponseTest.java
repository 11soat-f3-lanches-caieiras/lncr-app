package br.com.tp.lanchescaieiras.customer.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetadata;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import br.com.tp.lanchescaieiras.customer.domain.shared.CustomerListResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerListResponseTest {

    @Test
    void testConstructorAndGetters() {
        ResponseMetadata meta = new ResponseMetadata();
        Customer c1 = new Customer(1, "12345678909", "João", "joao@email.com");
        Customer c2 = new Customer(2, "98765432100", "Maria", "maria@email.com");
        List<Customer> customers = Arrays.asList(c1, c2);

        CustomerListResponse response = new CustomerListResponse(meta, customers);
        assertEquals(meta, response.get_response());
        assertEquals(customers, response.get_content());
    }

    @Test
    void testSetters() {
        CustomerListResponse response = new CustomerListResponse();
        ResponseMetadata meta = new ResponseMetadata();
        Customer c = new Customer();
        response.set_response(meta);
        response.set_content(Arrays.asList(c));
        assertEquals(meta, response.get_response());
        assertEquals(1, response.get_content().size());
    }

    @Test
    void testConstructorWithContentOnly() {
        Customer c = new Customer();
        CustomerListResponse response = new CustomerListResponse(Arrays.asList(c));
        assertNotNull(response.get_response());
        assertEquals(1, response.get_content().size());
    }
}
