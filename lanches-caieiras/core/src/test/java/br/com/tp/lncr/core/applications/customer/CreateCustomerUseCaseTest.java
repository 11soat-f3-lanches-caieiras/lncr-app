package br.com.tp.lncr.core.applications.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lncr.core.commons.exceptions.CustomerException;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lncr.core.domain.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateCustomerUseCaseTest {
    private CustomerGateway customerGateway;
    private CreateCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        customerGateway = mock(CustomerGateway.class);
        useCase = new CreateCustomerUseCase(customerGateway);
    }

    @Test
    void deveCriarClienteComSucesso() {
        CustomerDTO dto = new CustomerDTO();
        dto.setName("João");
        dto.setDocumentNumber("123");
        dto.setEmail("joao@email.com");
        when(customerGateway.existsByDocumentNumber(any())).thenReturn(false);
        when(customerGateway.existsByEmail(any())).thenReturn(false);
        when(customerGateway.save(any())).thenAnswer(i -> i.getArgument(0));

        Customer customer = useCase.execute(dto);
        assertNotNull(customer);
        assertEquals("João", customer.getName());
    }

    @Test
    void deveLancarExcecaoSeDocumentoExistir() {
        CustomerDTO dto = new CustomerDTO();
        dto.setName("Maria");
        dto.setDocumentNumber("123");
        dto.setEmail("maria@email.com");
        when(customerGateway.existsByDocumentNumber("123")).thenReturn(true);
        when(customerGateway.existsByEmail(any())).thenReturn(false);

        CustomerException ex = assertThrows(CustomerException.class, () -> useCase.execute(dto));
        assertEquals(409, ex.getCode());
    }

    @Test
    void deveLancarExcecaoSeEmailExistir() {
        CustomerDTO dto = new CustomerDTO();
        dto.setName("Pedro");
        dto.setDocumentNumber("456");
        dto.setEmail("pedro@email.com");
        when(customerGateway.existsByDocumentNumber(any())).thenReturn(false);
        when(customerGateway.existsByEmail("pedro@email.com")).thenReturn(true);

        CustomerException ex = assertThrows(CustomerException.class, () -> useCase.execute(dto));
        assertEquals(409, ex.getCode());
    }
}

