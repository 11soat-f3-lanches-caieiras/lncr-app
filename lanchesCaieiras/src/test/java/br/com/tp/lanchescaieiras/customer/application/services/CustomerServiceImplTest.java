package br.com.tp.lanchescaieiras.customer.application.services;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.repositories.JpaCustomerReposityImpl;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import br.com.tp.lanchescaieiras.customer.infraestructure.exceptions.CustomerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    private JpaCustomerReposityImpl repo;
    private CustomerServiceImpl service;

    @BeforeEach
    void setUp() {
        repo = mock(JpaCustomerReposityImpl.class);
        service = new CustomerServiceImpl(repo);
    }

    @Test
    void createCustomer_Success() {
        Customer c = new Customer(1, "52998224725", "João", "joao@email.com");
        when(repo.existsByDocumentNumber(anyString())).thenReturn(false);
        when(repo.existsByEmail(anyString())).thenReturn(false);
        when(repo.save(any())).thenReturn(c);

        Customer result = service.createCustomer(c);
        assertEquals(c, result);
    }

    @Test
    void createCustomer_MissingFields() {
        Customer c = new Customer();
        Exception ex = assertThrows(CustomerException.class, () -> service.createCustomer(c));
        assertEquals(400, ((CustomerException) ex).getCode());
    }

    @Test
    void createCustomer_InvalidDocument() {
        Customer c = new Customer(1, "11111111111", "João", "joao@email.com");
        Exception ex = assertThrows(CustomerException.class, () -> service.createCustomer(c));
        assertEquals(404, ((CustomerException) ex).getCode());
    }

    @Test
    void createCustomer_DuplicateDocument() {
        Customer c = new Customer(1, "52998224725", "João", "joao@email.com");
        when(repo.existsByDocumentNumber(anyString())).thenReturn(true);
        Exception ex = assertThrows(CustomerException.class, () -> service.createCustomer(c));
        assertEquals(409, ((CustomerException) ex).getCode());
    }

    @Test
    void createCustomer_InvalidEmail() {
        Customer c = new Customer(1, "52998224725", "João", "invalido");
        when(repo.existsByDocumentNumber(anyString())).thenReturn(false);
        Exception ex = assertThrows(CustomerException.class, () -> service.createCustomer(c));
        assertEquals(404, ((CustomerException) ex).getCode());
    }

    @Test
    void createCustomer_DuplicateEmail() {
        Customer c = new Customer(1, "52998224725", "João", "joao@email.com");
        when(repo.existsByDocumentNumber(anyString())).thenReturn(false);
        when(repo.existsByEmail(anyString())).thenReturn(true);
        Exception ex = assertThrows(CustomerException.class, () -> service.createCustomer(c));
        assertEquals(409, ((CustomerException) ex).getCode());
    }

    @Test
    void getCustomerById_Found() {
        Customer c = new Customer(1, "52998224725", "João", "joao@email.com");
        when(repo.findById(1)).thenReturn(Optional.of(c));
        Optional<Customer> result = service.getCustomerById(1);
        assertTrue(result.isPresent());
        assertEquals(c, result.get());
    }

    @Test
    void getCustomerById_NotFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());
        Exception ex = assertThrows(CustomerException.class, () -> service.getCustomerById(1));
        assertEquals(404, ((CustomerException) ex).getCode());
    }

    @Test
    void getAllCustomers() {
        when(repo.findAll(10)).thenReturn(List.of());
        List<Customer> result = service.getAllCustomers(10);
        assertNotNull(result);
    }

    @Test
    void getCustomerByDocumentNumber() {
        Customer c = new Customer(1, "52998224725", "João", "joao@email.com");
        when(repo.findByDocumentNumber("52998224725")).thenReturn(Optional.of(c));
        Optional<Customer> result = service.getCustomerByDocumentNumber("52998224725");
        assertTrue(result.isPresent());
    }

    @Test
    void partialUpdateCustomer_Success() {
        Customer c = new Customer(1, "52998224725", "João", "joao@email.com");
        when(repo.partialUpdateById(any(), eq(1))).thenReturn(Optional.of(c));
        Customer result = service.partialUpdateCustomer(c, 1);
        assertEquals(c, result);
    }

    @Test
    void partialUpdateCustomer_NotFound() {
        Customer c = new Customer(1, "52998224725", "João", "joao@email.com");
        when(repo.partialUpdateById(any(), eq(1))).thenReturn(Optional.empty());
        Exception ex = assertThrows(CustomerException.class, () -> service.partialUpdateCustomer(c, 1));
        assertEquals(404, ((CustomerException) ex).getCode());
    }

    @Test
    void deleteCustomer() {
        doNothing().when(repo).deleteById(1);
        assertDoesNotThrow(() -> service.deleteCustomer(1));
    }
}
