package br.com.tp.lncr.core.applications.customer;

import br.com.tp.lncr.core.commons.exceptions.CustomerException;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lncr.core.domain.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteCustomerUseCaseTest {
    private CustomerGateway customerGateway;
    private DeleteCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        customerGateway = mock(CustomerGateway.class);
        useCase = new DeleteCustomerUseCase(customerGateway);
    }

    @Test
    void deveLancarExcecaoSeClienteNaoEncontrado() {
        when(customerGateway.getCustomerById(1)).thenReturn(null);
        CustomerException ex = assertThrows(CustomerException.class, () -> useCase.execute(1));
        assertEquals(404, ex.getCode());
    }

    @Test
    void deveExecutarSemExcecaoSeClienteEncontrado() {
        when(customerGateway.getCustomerById(2)).thenReturn(mock(Customer.class));
        assertDoesNotThrow(() -> useCase.execute(2));
    }
}
