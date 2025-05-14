package br.com.tp.lanchescaieiras.customer.adapters.inbound;

import br.com.tp.lanchescaieiras.customer.application.services.CustomerServiceImpl;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import br.com.tp.lanchescaieiras.customer.domain.CustomerListResponse;
import br.com.tp.lanchescaieiras.customer.domain.CustomerResponse;
import br.com.tp.lanchescaieiras.customer.infraestructure.exceptions.CustomerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerImplTest {

    @Mock
    private CustomerServiceImpl customerService;

    private CustomerControllerImpl customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerController = new CustomerControllerImpl(customerService);
    }

    @Test
    void createCustomerReturnsCreatedResponse() {
        Customer customer = new Customer();
        Customer createdCustomer = new Customer();
        when(customerService.createCustomer(customer)).thenReturn(createdCustomer);

        ResponseEntity<CustomerResponse> response = customerController.createCustomer(customer);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(customerService, times(1)).createCustomer(customer);
    }

    @Test
    void getAllCustomersReturnsCustomerListResponse() {
        List<Customer> customers = List.of(new Customer(), new Customer());
        when(customerService.getAllCustomers(10)).thenReturn(customers);

        ResponseEntity<CustomerListResponse> response = customerController.getAllCustomers(Optional.of(10));

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().getCustomers().size());
        verify(customerService, times(1)).getAllCustomers(10);
    }

    @Test
    void getAllCustomersThrowsExceptionForInvalidLimit() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerController.getAllCustomers(Optional.of(0)));

        assertEquals("Limite deve ser maior que 0 e menor ou igual a 50", exception.getMessage());
    }

    @Test
    void getCustomerByIdReturnsCustomerResponseWhenFound() {
        Customer customer = new Customer();
        when(customerService.getCustomerById(1)).thenReturn(Optional.of(customer));

        ResponseEntity<CustomerResponse> response = customerController.getCustomerById(1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(customerService, times(1)).getCustomerById(1);
    }

    @Test
    void getCustomerByIdThrowsExceptionWhenNotFound() {
        when(customerService.getCustomerById(1)).thenReturn(Optional.empty());

        CustomerException exception = assertThrows(CustomerException.class, () -> customerController.getCustomerById(1));

        assertEquals("Cliente não encontrado", exception.getMessage());
    }

    @Test
    void deleteCustomerReturnsOkWhenDeleted() {
        when(customerService.deleteCustomer(1)).thenReturn(true);

        ResponseEntity<CustomerResponse> response = customerController.deleteCustomer(1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).deleteCustomer(1);
    }

    @Test
    void deleteCustomerThrowsExceptionWhenNotFound() {
        when(customerService.deleteCustomer(1)).thenReturn(false);

        CustomerException exception = assertThrows(CustomerException.class, () -> customerController.deleteCustomer(1));

        assertEquals("Cliente não encontrado", exception.getMessage());
    }
}