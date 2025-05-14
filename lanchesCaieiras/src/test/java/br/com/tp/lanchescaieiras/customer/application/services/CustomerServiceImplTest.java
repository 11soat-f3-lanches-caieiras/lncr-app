package br.com.tp.lanchescaieiras.customer.application.services;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.repositories.JpaCustomerReposityImpl;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import br.com.tp.lanchescaieiras.customer.infraestructure.exceptions.CustomerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private JpaCustomerReposityImpl customerRepository;

    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void createCustomerReturnsSavedCustomer() {
        Customer customer = new Customer();
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.createCustomer(customer);

        assertNotNull(result);
        assertEquals(customer, result);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void createCustomerThrowsExceptionForInvalidDocumentNumber() {
        Customer customer = mock(Customer.class);
        when(customer.documentNumberIsValid()).thenReturn(false);

        CustomerException exception = assertThrows(CustomerException.class, () -> customerService.createCustomer(customer));

        assertEquals("Documento informado invalido", exception.getMessage());
    }

    @Test
    void getCustomerByIdReturnsCustomerWhenFound() {
        Customer customer = new Customer();
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerService.getCustomerById(1);

        assertTrue(result.isPresent());
        assertEquals(customer, result.get());
    }

    @Test
    void getCustomerByIdThrowsExceptionWhenNotFound() {
        when(customerRepository.findById(1)).thenReturn(Optional.empty());

        CustomerException exception = assertThrows(CustomerException.class, () -> customerService.getCustomerById(1));

        assertEquals("Cliente não encontrado com o ID: 1", exception.getMessage());
    }

    @Test
    void getAllCustomersReturnsCustomerList() {
        List<Customer> customers = List.of(new Customer(), new Customer());
        when(customerRepository.findAll(10)).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers(10);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void deleteCustomerReturnsTrueWhenDeleted() {
        when(customerRepository.deleteById(1)).thenReturn(true);

        Boolean result = customerService.deleteCustomer(1);

        assertTrue(result);
        verify(customerRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteCustomerThrowsExceptionWhenNotFound() {
        when(customerRepository.deleteById(1)).thenReturn(false);

        CustomerException exception = assertThrows(CustomerException.class, () -> customerService.deleteCustomer(1));

        assertEquals("Cliente não encontrado com o ID: 1", exception.getMessage());
    }

    @Test
    void validateDocumentNumberThrowsExceptionForDuplicateDocument() {
        Customer customer = new Customer();
        when(customer.documentNumberIsValid()).thenReturn(true);
        when(customerRepository.existsByDocumentNumber(customer.getDocumentNumber())).thenReturn(true);

        CustomerException exception = assertThrows(CustomerException.class, () -> customerService.validateDocumentNumber(customer));

        assertEquals("Documento já utilizado por outro cliente", exception.getMessage());
    }

    @Test
    void validateEmailThrowsExceptionForInvalidEmail() {
        Customer customer = new Customer();
        when(customer.emailIsValid()).thenReturn(false);

        CustomerException exception = assertThrows(CustomerException.class, () -> customerService.validateEmail(customer));

        assertEquals("Email informado invalido", exception.getMessage());
    }
}