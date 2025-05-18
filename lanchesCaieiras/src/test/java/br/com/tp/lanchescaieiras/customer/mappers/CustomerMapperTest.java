package br.com.tp.lanchescaieiras.customer.mappers;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.entities.JpaCustomerEntity;
import br.com.tp.lanchescaieiras.customer.application.mappers.CustomerMapper;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CustomerMapperTest {

    private final CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    @DisplayName("Should map JpaCustomerEntity to Customer")
    @Test
    void mapJpaToDomain() {
        JpaCustomerEntity jpaEntity = new JpaCustomerEntity();
        jpaEntity.setId(1);
        jpaEntity.setDocumentNumber("123456789");
        jpaEntity.setName("John Doe");
        jpaEntity.setEmail("john.doe@example.com");

        Customer customer = mapper.jpaToDomain(jpaEntity);

        Assertions.assertEquals(jpaEntity.getId(), customer.getId());
        Assertions.assertEquals(jpaEntity.getDocumentNumber(), customer.getDocumentNumber());
        Assertions.assertEquals(jpaEntity.getName(), customer.getName());
        Assertions.assertEquals(jpaEntity.getEmail(), customer.getEmail());
    }

    @DisplayName("Should map Customer to JpaCustomerEntity")
    @Test
    void mapDomainToJpa() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setDocumentNumber("123456789");
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");

        JpaCustomerEntity jpaEntity = mapper.domainToJpa(customer);

        Assertions.assertEquals(customer.getId(), jpaEntity.getId());
        Assertions.assertEquals(customer.getDocumentNumber(), jpaEntity.getDocumentNumber());
        Assertions.assertEquals(customer.getName(), jpaEntity.getName());
        Assertions.assertEquals(customer.getEmail(), jpaEntity.getEmail());
    }

    @DisplayName("Should handle null JpaCustomerEntity when mapping to Customer")
    @Test
    void handleNullJpaToDomain() {
        Customer customer = mapper.jpaToDomain(null);

        Assertions.assertNull(customer);
    }

    @DisplayName("Should handle null Customer when mapping to JpaCustomerEntity")
    @Test
    void handleNullDomainToJpa() {
        JpaCustomerEntity jpaEntity = mapper.domainToJpa(null);

        Assertions.assertNull(jpaEntity);
    }
}