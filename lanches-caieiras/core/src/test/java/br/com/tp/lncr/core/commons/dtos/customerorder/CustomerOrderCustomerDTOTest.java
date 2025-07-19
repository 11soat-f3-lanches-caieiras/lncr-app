package br.com.tp.lncr.core.commons.dtos.customerorder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerOrderCustomerDTOTest {
    @Test
    void createCustomerOrderCustomerDTOWithAllFields() {
        CustomerOrderCustomerDTO dto = new CustomerOrderCustomerDTO(1, "João");
        Assertions.assertEquals(1, dto.getId());
        Assertions.assertEquals("João", dto.getName());
    }

    @Test
    void setAndGetFieldsIndividually() {
        CustomerOrderCustomerDTO dto = new CustomerOrderCustomerDTO();
        dto.setId(2);
        dto.setName("Maria");
        Assertions.assertEquals(2, dto.getId());
        Assertions.assertEquals("Maria", dto.getName());
    }

    @Test
    void allowNullFields() {
        CustomerOrderCustomerDTO dto = new CustomerOrderCustomerDTO(null, null);
        Assertions.assertNull(dto.getId());
        Assertions.assertNull(dto.getName());
    }
}

