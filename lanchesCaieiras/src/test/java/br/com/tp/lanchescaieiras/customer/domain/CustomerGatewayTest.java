package br.com.tp.lanchescaieiras.customer.domain;

import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import br.com.tp.lanchescaieiras.commons.interfaces.CustomerGateway;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerGatewayTest {

    static class DummyCustomerGateway implements CustomerGateway {
        @Override public Customer save(Customer customer) { return customer; }
        @Override public List<Customer> findAll(Integer _limit) { return List.of(); }
        @Override public Optional<Customer> findById(Integer id) { return Optional.empty(); }
        @Override public Optional<Customer> findByDocumentNumber(String documentNumber) { return Optional.empty(); }
        @Override public Optional<Customer> partialUpdateById(Customer customer, Integer id) { return Optional.empty(); }
        @Override public void deleteById(Integer id) {}
        @Override public Boolean existsByDocumentNumber(String documentNumber) { return false; }
        @Override public Boolean existsByEmail(String email) { return false; }
    }

    @Test
    void testInterfaceImplementation() {
        CustomerGateway repo = new DummyCustomerGateway();
        assertNotNull(repo);
        assertDoesNotThrow(() -> repo.save(new Customer()));
        assertDoesNotThrow(() -> repo.findAll(10));
        assertDoesNotThrow(() -> repo.findById(1));
        assertDoesNotThrow(() -> repo.findByDocumentNumber("123"));
        assertDoesNotThrow(() -> repo.partialUpdateById(new Customer(), 1));
        assertDoesNotThrow(() -> repo.deleteById(1));
        assertDoesNotThrow(() -> repo.existsByDocumentNumber("123"));
        assertDoesNotThrow(() -> repo.existsByEmail("a@b.com"));
    }
}
