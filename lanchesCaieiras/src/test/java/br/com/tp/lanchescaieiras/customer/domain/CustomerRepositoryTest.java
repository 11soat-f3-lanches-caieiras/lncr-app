package br.com.tp.lanchescaieiras.customer.domain;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {

    static class DummyCustomerRepository implements CustomerRepository {
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
        CustomerRepository repo = new DummyCustomerRepository();
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
