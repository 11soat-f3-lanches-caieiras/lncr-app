package br.com.tp.lanchescaieiras.customer.application.mappers;

import br.com.tp.lanchescaieiras.customer.external.datasources.postgres.JpaCustomerPostgresEntity;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    private final CustomerDtoMapper mapper = Mappers.getMapper(CustomerDtoMapper.class);

    @Test
    void jpaToDomain() {
        JpaCustomerPostgresEntity entity = new JpaCustomerPostgresEntity(1, "123", "João", "joao@email.com");
        Customer customer = mapper.jpaToDomain(entity);
        assertEquals(entity.getId(), customer.getId());
        assertEquals(entity.getDocumentNumber(), customer.getDocumentNumber());
        assertEquals(entity.getName(), customer.getName());
        assertEquals(entity.getEmail(), customer.getEmail());
    }

    @Test
    void domainToJpa() {
        Customer customer = new Customer(2, "456", "Maria", "maria@email.com");
        JpaCustomerPostgresEntity entity = mapper.domainToJpa(customer);
        assertEquals(customer.getId(), entity.getId());
        assertEquals(customer.getDocumentNumber(), entity.getDocumentNumber());
        assertEquals(customer.getName(), entity.getName());
        assertEquals(customer.getEmail(), entity.getEmail());
    }
}
