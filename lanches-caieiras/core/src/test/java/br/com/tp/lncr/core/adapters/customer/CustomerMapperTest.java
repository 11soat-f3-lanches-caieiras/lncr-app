package br.com.tp.lncr.core.adapters.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lncr.core.domain.customer.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {
    private final CustomerMapper mapper = new CustomerMapper();

    @Test
    void testCustomerToDomain() {
        Customer customer = new Customer(1, "71590186214", "Tito", "tito@email.com");
        CustomerDTO dto = mapper.customerToDomain(customer);
        assertEquals(customer.getId(), dto.getId());
        assertEquals(customer.getDocumentNumber(), dto.getDocumentNumber());
        assertEquals(customer.getName(), dto.getName());
        assertEquals(customer.getEmail(), dto.getEmail());
    }

    @Test
    void testCustomerToDTO() {
        CustomerDTO dto = new CustomerDTO(1, "71590186214", "Tito", "tito@email.com");
        Customer customer = mapper.customerToDTO(dto);
        assertEquals(dto.getId(), customer.getId());
        assertEquals(dto.getDocumentNumber(), customer.getDocumentNumber());
        assertEquals(dto.getName(), customer.getName());
        assertEquals(dto.getEmail(), customer.getEmail());
    }
}

