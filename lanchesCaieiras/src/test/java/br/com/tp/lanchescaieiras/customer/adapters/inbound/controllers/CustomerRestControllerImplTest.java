/*
package br.com.tp.lanchescaieiras.customer.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.customer.application.services.impl.CustomerServiceImpl;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import br.com.tp.lanchescaieiras.customer.domain.shared.CustomerListResponse;
import br.com.tp.lanchescaieiras.customer.domain.shared.CustomerResponse;
import br.com.tp.lanchescaieiras.customer.external.api.CustomerRestControllerImpl;
import br.com.tp.lanchescaieiras.customer.application.config.CustomerConfig;
import br.com.tp.lanchescaieiras.customer.domain.shared.exceptions.CustomerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerRestControllerImplTest {

    private CustomerServiceImpl service;
    private CustomerConfig config;
    private CustomerRestControllerImpl controller;

    @BeforeEach
    void setUp() {
        service = mock(CustomerServiceImpl.class);
        config = mock(CustomerConfig.class);
        controller = new CustomerRestControllerImpl(service, config);
    }

    @Test
    void createCustomer() {
        Customer c = new Customer(1, "123", "João", "joao@email.com");
        when(service.createCustomer(any())).thenReturn(c);
        when(config.getLocationPrefix()).thenReturn("/customers");
        ResponseEntity<CustomerResponse> resp = controller.createCustomer(c);
        assertEquals(201, resp.getStatusCodeValue());
    }

    @Test
    void getAllCustomers() {
        when(service.getAllCustomers(anyInt())).thenReturn(List.of());
        ResponseEntity<CustomerListResponse> resp = controller.getAllCustomers(Optional.of(10));
        assertEquals(200, resp.getStatusCodeValue());
    }

    @Test
    void getAllCustomers_InvalidLimit() {
        Exception ex = assertThrows(CustomerException.class, () -> controller.getAllCustomers(Optional.of(0)));
        assertEquals(400, ((CustomerException) ex).getCode());
    }

    @Test
    void getCustomerById() {
        Customer c = new Customer(1, "123", "João", "joao@email.com");
        when(service.getCustomerById(1)).thenReturn(Optional.of(c));
        ResponseEntity<CustomerResponse> resp = controller.getCustomerById(1);
        assertEquals(200, resp.getStatusCodeValue());
    }

    @Test
    void getCustomerByDocumentNumber() {
        Customer c = new Customer(1, "123", "João", "joao@email.com");
        when(service.getCustomerByDocumentNumber("123")).thenReturn(Optional.of(c));
        ResponseEntity<CustomerResponse> resp = controller.getCustomerByDocumentNumber("123");
        assertEquals(200, resp.getStatusCodeValue());
    }

    @Test
    void partialUpdateCustomer() {
        Customer c = new Customer(1, "123", "João", "joao@email.com");
        when(service.partialUpdateCustomer(any(), eq(1))).thenReturn(c);
        ResponseEntity<CustomerResponse> resp = controller.partialUpdateCustomer(c, 1);
        assertEquals(200, resp.getStatusCodeValue());
    }

    @Test
    void deleteCustomer() {
        doNothing().when(service).deleteCustomer(1);
        ResponseEntity<CustomerResponse> resp = controller.deleteCustomer(1);
        assertEquals(200, resp.getStatusCodeValue());
    }
}
*/
