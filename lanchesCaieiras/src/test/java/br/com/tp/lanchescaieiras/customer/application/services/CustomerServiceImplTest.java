package br.com.tp.lanchescaieiras.customer.application.services;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.repositories.JpaCustomerReposityImpl;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import br.com.tp.lanchescaieiras.customer.infraestructure.exceptions.CustomerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private JpaCustomerReposityImpl customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("createCustomer")
    class CreateCustomer {

        @Test
        @DisplayName("Should create a customer successfully")
        void shouldCreateCustomerSuccessfully() {
            Customer customer = new Customer();
            when(customerRepository.save(customer)).thenReturn(customer);

            Customer result = customerService.createCustomer(customer);

            assertEquals(customer, result);
            verify(customerRepository, times(1)).save(customer);
        }

        @Test
        @DisplayName("Should throw exception for invalid document number")
        void shouldThrowExceptionForInvalidDocumentNumber() {
            Customer customer = mock(Customer.class);
            when(customer.documentNumberIsValid()).thenReturn(false);

            assertThrows(CustomerException.class, () -> customerService.createCustomer(customer));
        }

        @Test
        @DisplayName("Should throw exception for duplicate document number")
        void shouldThrowExceptionForDuplicateDocumentNumber() {
            Customer customer = mock(Customer.class);
            when(customer.documentNumberIsValid()).thenReturn(true);
            when(customerRepository.existsByDocumentNumber(customer.getDocumentNumber())).thenReturn(true);

            assertThrows(CustomerException.class, () -> customerService.createCustomer(customer));
        }
    }

    @Nested
    @DisplayName("getCustomerById")
    class GetCustomerById {

        @Test
        @DisplayName("Should return customer by ID")
        void shouldReturnCustomerById() {
            Customer customer = new Customer();
            when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

            Optional<Customer> result = customerService.getCustomerById(1);

            assertTrue(result.isPresent());
            assertEquals(customer, result.get());
            verify(customerRepository, times(1)).findById(1);
        }

        @Test
        @DisplayName("Should throw exception when customer not found")
        void shouldThrowExceptionWhenCustomerNotFound() {
            when(customerRepository.findById(1)).thenReturn(Optional.empty());

            assertThrows(CustomerException.class, () -> customerService.getCustomerById(1));
        }
    }

    @Nested
    @DisplayName("getAllCustomers")
    class GetAllCustomers {

        @Test
        @DisplayName("Should return all customers with limit")
        void shouldReturnAllCustomersWithLimit() {
            List<Customer> customers = List.of(new Customer());
            when(customerRepository.findAll(10)).thenReturn(customers);

            List<Customer> result = customerService.getAllCustomers(10);

            assertEquals(customers, result);
            verify(customerRepository, times(1)).findAll(10);
        }
    }

    @Nested
    @DisplayName("deleteCustomer")
    class DeleteCustomer {

        @Test
        @DisplayName("Should delete customer by ID")
        void shouldDeleteCustomerById() {
            doNothing().when(customerRepository).deleteById(1);

            customerService.deleteCustomer(1);

            verify(customerRepository, times(1)).deleteById(1);
        }
    }
}