package br.com.tp.lncr.app.datasources.postgres.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaCustomerMapperTest {

    private JpaCustomerMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new JpaCustomerMapper();
    }

    @Test
    void shouldMapCustomerDtoToJpaEntity() {
        CustomerDTO dto = new CustomerDTO(1, "12345678901", "João Silva", "joao@email.com");

        JpaCustomerEntity entity = mapper.customerDtoToJpa(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getDocumentNumber(), entity.getDocumentNumber());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getEmail(), entity.getEmail());
    }

    @Test
    void shouldMapJpaEntityToCustomerDto() {
        JpaCustomerEntity entity = new JpaCustomerEntity(1, "12345678901", "João Silva", "joao@email.com");

        CustomerDTO dto = mapper.jpaCustomerToDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getDocumentNumber(), dto.getDocumentNumber());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getEmail(), dto.getEmail());
    }

    @Test
    void shouldReturnNullWhenCustomerDtoIsNull() {
        JpaCustomerEntity entity = mapper.customerDtoToJpa(null);

        assertNull(entity);
    }

    @Test
    void shouldReturnNullWhenJpaEntityIsNull() {
        CustomerDTO dto = mapper.jpaCustomerToDTO(null);

        assertNull(dto);
    }

    @Test
    void shouldHandleNullValuesInCustomerDto() {
        CustomerDTO dto = new CustomerDTO(null, null, null, null);

        JpaCustomerEntity entity = mapper.customerDtoToJpa(dto);

        assertNotNull(entity);
        assertNull(entity.getId());
        assertNull(entity.getDocumentNumber());
        assertNull(entity.getName());
        assertNull(entity.getEmail());
    }

    @Test
    void shouldHandleNullValuesInJpaEntity() {
        JpaCustomerEntity entity = new JpaCustomerEntity(null, null, null, null);

        CustomerDTO dto = mapper.jpaCustomerToDTO(entity);

        assertNotNull(dto);
        assertNull(dto.getId());
        assertNull(dto.getDocumentNumber());
        assertNull(dto.getName());
        assertNull(dto.getEmail());
    }

    @Test
    void shouldMapEmptyStringsFromDtoToEntity() {
        CustomerDTO dto = new CustomerDTO(1, "", "", "");

        JpaCustomerEntity entity = mapper.customerDtoToJpa(dto);

        assertNotNull(entity);
        assertEquals(1, entity.getId());
        assertEquals("", entity.getDocumentNumber());
        assertEquals("", entity.getName());
        assertEquals("", entity.getEmail());
    }

    @Test
    void shouldMapEmptyStringsFromEntityToDto() {
        JpaCustomerEntity entity = new JpaCustomerEntity(1, "", "", "");

        CustomerDTO dto = mapper.jpaCustomerToDTO(entity);

        assertNotNull(dto);
        assertEquals(1, dto.getId());
        assertEquals("", dto.getDocumentNumber());
        assertEquals("", dto.getName());
        assertEquals("", dto.getEmail());
    }
}
