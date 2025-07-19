package br.com.tp.lncr.core.commons.dtos.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerDTOTest {
    @Test
    void createCustomerDTOWithAllFields() {
        CustomerDTO dto = new CustomerDTO(1, "12345678900", "João Silva", "joao@email.com");
        Assertions.assertEquals(1, dto.getId());
        Assertions.assertEquals("12345678900", dto.getDocumentNumber());
        Assertions.assertEquals("João Silva", dto.getName());
        Assertions.assertEquals("joao@email.com", dto.getEmail());
    }

    @Test
    void setAndGetFieldsIndividually() {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(2);
        dto.setDocumentNumber("98765432100");
        dto.setName("Maria Souza");
        dto.setEmail("maria@email.com");
        Assertions.assertEquals(2, dto.getId());
        Assertions.assertEquals("98765432100", dto.getDocumentNumber());
        Assertions.assertEquals("Maria Souza", dto.getName());
        Assertions.assertEquals("maria@email.com", dto.getEmail());
    }

    @Test
    void toStringReturnsExpectedFormat() {
        CustomerDTO dto = new CustomerDTO(3, "11122233344", "Carlos Lima", "carlos@email.com");
        String str = dto.toString();
        Assertions.assertTrue(str.contains("id=3"));
        Assertions.assertTrue(str.contains("documentNumber='11122233344'"));
        Assertions.assertTrue(str.contains("name='Carlos Lima'"));
        Assertions.assertTrue(str.contains("email='carlos@email.com'"));
    }

    @Test
    void allowNullFields() {
        CustomerDTO dto = new CustomerDTO(null, null, null, null);
        Assertions.assertNull(dto.getId());
        Assertions.assertNull(dto.getDocumentNumber());
        Assertions.assertNull(dto.getName());
        Assertions.assertNull(dto.getEmail());
    }
}

