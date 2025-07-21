package br.com.tp.lncr.core.applications.customer;

import br.com.tp.lncr.core.commons.exceptions.CustomerException;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lncr.core.domain.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetCustomerUseCaseTest {
    private CustomerGateway customerGateway;
    private GetCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        customerGateway = mock(CustomerGateway.class);
        useCase = new GetCustomerUseCase(customerGateway);
    }

    @Test
    void deveBuscarTodosComLimitePadrao() {
        when(customerGateway.getAllCustomers(10)).thenReturn(Collections.emptyList());
        List<Customer> result = useCase.getAll(Optional.empty());
        assertNotNull(result);
    }

    @Test
    void deveLancarExcecaoSeLimiteInvalido() {
        CustomerException ex = assertThrows(CustomerException.class, () -> useCase.getAll(Optional.of(0)));
        assertEquals(400, ex.getCode());
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        Customer customer = mock(Customer.class);
        when(customerGateway.getCustomerById(1)).thenReturn(customer);
        assertEquals(customer, useCase.getById(1));
    }

    @Test
    void deveLancarExcecaoSeIdNaoEncontrado() {
        when(customerGateway.getCustomerById(2)).thenReturn(null);
        CustomerException ex = assertThrows(CustomerException.class, () -> useCase.getById(2));
        assertEquals(404, ex.getCode());
    }

    @Test
    void deveBuscarPorDocumentoComSucesso() {
        Customer customer = mock(Customer.class);
        when(customerGateway.getCustomerByDocumentNumber("abc")).thenReturn(customer);
        assertEquals(customer, useCase.getByDocumentNumber("abc"));
    }

    @Test
    void deveLancarExcecaoSeDocumentoNaoEncontrado() {
        when(customerGateway.getCustomerByDocumentNumber("xyz")).thenReturn(null);
        CustomerException ex = assertThrows(CustomerException.class, () -> useCase.getByDocumentNumber("xyz"));
        assertEquals(404, ex.getCode());
    }

    @Test
    void deveBuscarListaDeIdsComSucesso() {
        List<Customer> customers = Collections.singletonList(mock(Customer.class));
        when(customerGateway.getCustomerByIdList(anyList())).thenReturn(customers);
        assertEquals(customers, useCase.getByIdList(Arrays.asList(1,2)));
    }

    @Test
    void deveLancarExcecaoSeListaDeIdsNaoEncontrada() {
        when(customerGateway.getCustomerByIdList(anyList())).thenReturn(null);
        CustomerException ex = assertThrows(CustomerException.class, () -> useCase.getByIdList(Arrays.asList(3,4)));
        assertEquals(404, ex.getCode());
    }
}
