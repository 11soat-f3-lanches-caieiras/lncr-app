package br.com.tp.lanchescaieiras.customer.adapters.outbound.entities;

import br.com.tp.lanchescaieiras.customer.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaCustomerEntityTest {

    @Test
    void testConstructorNoArgs() {
        JpaCustomerEntity entity = new JpaCustomerEntity();
        assertNotNull(entity);
    }

    @Test
    void testConstructorWithArgs() {
        JpaCustomerEntity entity = new JpaCustomerEntity(1, "12345678901", "Teste", "teste@email.com");
        assertNotNull(entity);
        assertEquals(1, entity.getId());
        assertEquals("12345678901", entity.getDocumentNumber());
        assertEquals("Teste", entity.getName());
        assertEquals("teste@email.com", entity.getEmail());
    }

    @Test
    void testConstructorWithCustomer() {
        Customer customer = new Customer(1, "12345678901", "Teste", "teste@email.com");
        JpaCustomerEntity entity = new JpaCustomerEntity(customer);
        assertNotNull(entity);
        assertEquals(customer.getId(), entity.getId());
        assertEquals(customer.getDocumentNumber(), entity.getDocumentNumber());
        assertEquals(customer.getName(), entity.getName());
        assertEquals(customer.getEmail(), entity.getEmail());
    }

    @Test
    void testSettersAndGetters() {
        JpaCustomerEntity entity = new JpaCustomerEntity();
        entity.setId(1);
        entity.setDocumentNumber("12345678901");
        entity.setName("Teste");
        entity.setEmail("teste@email.com");

        assertEquals(1, entity.getId());
        assertEquals("12345678901", entity.getDocumentNumber());
        assertEquals("Teste", entity.getName());
        assertEquals("teste@email.com", entity.getEmail());
    }
}