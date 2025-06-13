package br.com.tp.lanchescaieiras.customer.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.customer.external.datasource.entities.JpaCustomerEntity;
import br.com.tp.lanchescaieiras.customer.application.usecases.mappers.CustomerMapper;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import br.com.tp.lanchescaieiras.customer.external.datasource.repositories.interfaces.JpaCustomerRepository;
import br.com.tp.lanchescaieiras.customer.external.datasource.repositories.impl.JpaCustomerReposityImpl;
import br.com.tp.lanchescaieiras.customer.domain.shared.exceptions.CustomerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JpaCustomerReposityImplTest {

    private JpaCustomerRepository jpaRepo;
    private CustomerMapper mapper;
    private JpaCustomerReposityImpl repo;

    @BeforeEach
    void setUp() {
        jpaRepo = mock(JpaCustomerRepository.class);
        mapper = mock(CustomerMapper.class);
        repo = new JpaCustomerReposityImpl(jpaRepo, mapper);
    }

    @Test
    void save() {
        Customer c = new Customer(1, "123", "João", "joao@email.com");
        JpaCustomerEntity entity = new JpaCustomerEntity(1, "123", "João", "joao@email.com");
        when(mapper.domainToJpa(c)).thenReturn(entity);
        when(jpaRepo.save(entity)).thenReturn(entity);
        when(mapper.jpaToDomain(entity)).thenReturn(c);

        Customer result = repo.save(c);
        assertEquals(c, result);
    }

    @Test
    void findByDocumentNumber_Found() {
        JpaCustomerEntity entity = new JpaCustomerEntity(1, "123", "João", "joao@email.com");
        Customer c = new Customer(1, "123", "João", "joao@email.com");
        when(jpaRepo.findByDocumentNumber("123")).thenReturn(Optional.of(entity));
        when(mapper.jpaToDomain(entity)).thenReturn(c);

        Optional<Customer> result = repo.findByDocumentNumber("123");
        assertTrue(result.isPresent());
        assertEquals(c, result.get());
    }

    @Test
    void findByDocumentNumber_NotFound() {
        when(jpaRepo.findByDocumentNumber("999")).thenReturn(Optional.empty());
        assertThrows(CustomerException.class, () -> repo.findByDocumentNumber("999"));
    }

    @Test
    void partialUpdateById_Found() {
        JpaCustomerEntity entity = new JpaCustomerEntity(1, "123", "João", "joao@email.com");
        Customer c = new Customer(1, "123", "João", "joao@email.com");
        when(jpaRepo.findById(1)).thenReturn(Optional.of(entity));
        when(jpaRepo.save(entity)).thenReturn(entity);
        when(mapper.jpaToDomain(entity)).thenReturn(c);

        Optional<Customer> result = repo.partialUpdateById(c, 1);
        assertTrue(result.isPresent());
        assertEquals(c, result.get());
    }

    @Test
    void partialUpdateById_NotFound() {
        Customer c = new Customer();
        when(jpaRepo.findById(1)).thenReturn(Optional.empty());
        Optional<Customer> result = repo.partialUpdateById(c, 1);
        assertTrue(result.isEmpty());
    }

    @Test
    void deleteById_Found() {
        JpaCustomerEntity entity = new JpaCustomerEntity(1, "123", "João", "joao@email.com");
        when(jpaRepo.findById(1)).thenReturn(Optional.of(entity));
        doNothing().when(jpaRepo).delete(entity);
        assertDoesNotThrow(() -> repo.deleteById(1));
    }

    @Test
    void deleteById_NotFound() {
        when(jpaRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(CustomerException.class, () -> repo.deleteById(1));
    }

    @Test
    void findById() {
        JpaCustomerEntity entity = new JpaCustomerEntity(1, "123", "João", "joao@email.com");
        Customer c = new Customer(1, "123", "João", "joao@email.com");
        when(jpaRepo.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.jpaToDomain(entity)).thenReturn(c);

        Optional<Customer> result = repo.findById(1);
        assertTrue(result.isPresent());
        assertEquals(c, result.get());
    }

    @Test
    void findAll() {
        JpaCustomerEntity entity = new JpaCustomerEntity(1, "123", "João", "joao@email.com");
        Customer c = new Customer(1, "123", "João", "joao@email.com");
        when(jpaRepo.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(entity)));
        when(mapper.jpaToDomain(entity)).thenReturn(c);

        List<Customer> result = repo.findAll(10);
        assertEquals(1, result.size());
        assertEquals(c, result.get(0));
    }

    @Test
    void existsByDocumentNumber() {
        when(jpaRepo.existsByDocumentNumber("123")).thenReturn(true);
        assertTrue(repo.existsByDocumentNumber("123"));
    }

    @Test
    void existsByEmail() {
        when(jpaRepo.existsByEmail("a@b.com")).thenReturn(true);
        assertTrue(repo.existsByEmail("a@b.com"));
    }
}
