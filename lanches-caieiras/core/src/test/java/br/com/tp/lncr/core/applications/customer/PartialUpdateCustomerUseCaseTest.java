package br.com.tp.lncr.core.applications.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lncr.core.commons.exceptions.CustomerException;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lncr.core.domain.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PartialUpdateCustomerUseCaseTest {
    private CustomerGateway customerGateway;
    private PartialUpdateCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        customerGateway = mock(CustomerGateway.class);
        useCase = new PartialUpdateCustomerUseCase(customerGateway);
    }

    @Test
    void deveAtualizarParcialmenteComSucesso() {
        CustomerDTO dto = new CustomerDTO();
        dto.setName("Novo Nome");
        dto.setDocumentNumber("999");
        dto.setEmail("novo@email.com");
        Customer atual = mock(Customer.class);
        when(customerGateway.getCustomerById(1)).thenReturn(atual);
        when(customerGateway.existsByDocumentNumber(any())).thenReturn(false);
        when(customerGateway.existsByEmail(any())).thenReturn(false);
        when(customerGateway.save(any())).thenReturn(atual);
        Customer result = useCase.execute(1, dto);
        assertNotNull(result);
    }

    @Test
    void deveLancarExcecaoSeClienteNaoEncontrado() {
        when(customerGateway.getCustomerById(2)).thenReturn(null);
        CustomerDTO dto = new CustomerDTO();
        CustomerException ex = assertThrows(CustomerException.class, () -> useCase.execute(2, dto));
        assertEquals(404, ex.getCode());
    }

    @Test
    void deveLancarExcecaoSeDocumentoExistente() {
        CustomerDTO dto = new CustomerDTO();
        dto.setDocumentNumber("123");
        when(customerGateway.getCustomerById(3)).thenReturn(mock(Customer.class));
        when(customerGateway.existsByDocumentNumber(any())).thenReturn(true);
        when(customerGateway.existsByEmail(any())).thenReturn(false);
        CustomerException ex = assertThrows(CustomerException.class, () -> useCase.execute(3, dto));
        assertEquals(409, ex.getCode());
    }

    @Test
    void deveLancarExcecaoSeEmailExistente() {
        CustomerDTO dto = new CustomerDTO();
        dto.setEmail("existe@email.com");
        when(customerGateway.getCustomerById(4)).thenReturn(mock(Customer.class));
        when(customerGateway.existsByDocumentNumber(any())).thenReturn(false);
        when(customerGateway.existsByEmail(any())).thenReturn(true);
        CustomerException ex = assertThrows(CustomerException.class, () -> useCase.execute(4, dto));
        assertEquals(409, ex.getCode());
    }
}
