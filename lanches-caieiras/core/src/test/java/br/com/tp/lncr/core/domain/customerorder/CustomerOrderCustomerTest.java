package br.com.tp.lncr.core.domain.customerorder;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerOrderCustomerTest {
    @Test
    void testConstructorAndGetters() {
        CustomerOrderCustomer customer = new CustomerOrderCustomer(1, "Cliente Teste");
        Assertions.assertEquals(1, customer.getId());
        Assertions.assertEquals("Cliente Teste", customer.getName());
    }

    @Test
    void testSetters() {
        CustomerOrderCustomer customer = new CustomerOrderCustomer();
        customer.setId(2);
        customer.setName("Outro Cliente");
        Assertions.assertEquals(2, customer.getId());
        Assertions.assertEquals("Outro Cliente", customer.getName());
    }

    @Test
    void testDTOConstructor() {
        CustomerOrderCustomerDTO dto = new CustomerOrderCustomerDTO();
        dto.setId(3);
        dto.setName("DTO Cliente");
        CustomerOrderCustomer customer = new CustomerOrderCustomer(dto);
        Assertions.assertEquals(3, customer.getId());
        Assertions.assertEquals("DTO Cliente", customer.getName());
    }
}

