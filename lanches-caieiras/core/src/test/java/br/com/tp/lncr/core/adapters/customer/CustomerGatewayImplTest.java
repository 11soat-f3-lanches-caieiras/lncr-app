package br.com.tp.lncr.core.adapters.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerDatabase;
import br.com.tp.lncr.core.domain.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerGatewayImplTest {
    private CustomerDatabase customerDatabase;
    private CustomerMapper customerMapper;
    private CustomerGatewayImpl gateway;

    @BeforeEach
    void setUp() {
        customerDatabase = mock(CustomerDatabase.class);
        customerMapper = new CustomerMapper();
        gateway = new CustomerGatewayImpl(customerDatabase, customerMapper);
    }

    @Test
    void testExistsByDocumentNumber() {
        when(customerDatabase.existsByDocumentNumber("123")).thenReturn(true);
        assertTrue(gateway.existsByDocumentNumber("123"));
    }

    @Test
    void testExistsByEmail() {
        when(customerDatabase.existsByEmail("tito@email.com")).thenReturn(true);
        assertTrue(gateway.existsByEmail("tito@email.com"));
    }

    @Test
    void testGetAllCustomers() {
        CustomerDTO dto = new CustomerDTO(1, "123", "Tito", "tito@email.com");
        when(customerDatabase.findAll(any())).thenReturn(List.of(dto));
        List<Customer> result = gateway.getAllCustomers(10);
        assertEquals(1, result.size());
    }

    @Test
    void testGetCustomerByDocumentNumber() {
        CustomerDTO dto = new CustomerDTO(1, "123", "Tito", "tito@email.com");
        when(customerDatabase.findByDocumentNumber("123")).thenReturn(Optional.of(dto));
        Customer result = gateway.getCustomerByDocumentNumber("123");
        assertNotNull(result);
        assertEquals("123", result.getDocumentNumber());
    }

    @Test
    void testGetCustomerById() {
        CustomerDTO dto = new CustomerDTO(1, "123", "Tito", "tito@email.com");
        when(customerDatabase.findById(1)).thenReturn(Optional.of(dto));
        Customer result = gateway.getCustomerById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testGetCustomerByIdList() {
        CustomerDTO dto = new CustomerDTO(1, "123", "Tito", "tito@email.com");
        when(customerDatabase.findByIdList(any())).thenReturn(List.of(dto));
        List<Customer> result = gateway.getCustomerByIdList(List.of(1));
        assertEquals(1, result.size());
    }

    @Test
    void testDeleteCustomerByIdThrows() {
        when(customerDatabase.findById(99)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> gateway.deleteCustomerById(99));
    }

    @Test
    void testSave() {
        Customer customer = new Customer(1, "123", "Tito", "tito@email.com");
        CustomerDTO dto = new CustomerDTO(1, "123", "Tito", "tito@email.com");
        when(customerDatabase.save(any())).thenReturn(dto);
        Customer result = gateway.save(customer);
        assertEquals(customer.getId(), result.getId());
    }
}

