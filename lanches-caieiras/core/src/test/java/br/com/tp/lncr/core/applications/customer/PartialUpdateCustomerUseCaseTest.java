package br.com.tp.lncr.core.applications.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lncr.core.commons.exceptions.CustomerException;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lncr.core.domain.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

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
        // Criar customer existente com dados válidos
        Customer customerExistente = new Customer(1, "71590186214", "Nome Original", "original@email.com");

        CustomerDTO dto = new CustomerDTO();
        dto.setId(2);
        dto.setName("Nome Atualizado");
        dto.setEmail("teste2@gmail.com");
        dto.setDocumentNumber("13802445791");


        when(customerGateway.getCustomerById(1)).thenReturn(customerExistente);
        when(customerGateway.existsByDocumentNumber(anyString())).thenReturn(false);
        when(customerGateway.existsByEmail(anyString())).thenReturn(false);

        // Mock do customer atualizado
        Customer customerAtualizado = new Customer(1, "71590186214", "Nome Atualizado", "teste2@gmail.com");
        when(customerGateway.save(any(Customer.class))).thenReturn(customerAtualizado);

        Customer result = useCase.execute(1, dto);

        assertNotNull(result);
        assertEquals("Nome Atualizado", result.getName());
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerGateway).save(captor.capture());
        Customer saved = captor.getValue();
        assertEquals("Nome Atualizado", saved.getName());
        assertEquals("71590186214", saved.getDocumentNumber());
        assertEquals("teste2@gmail.com", saved.getEmail());
    }

    @Test
    void deveLancarExcecaoSeClienteNaoEncontrado() {
        when(customerGateway.getCustomerById(2)).thenReturn(null);
        CustomerDTO dto = new CustomerDTO();
        dto.setName("Novo Nome");

        CustomerException ex = assertThrows(CustomerException.class, () -> useCase.execute(2, dto));
        assertEquals(400, ex.getCode());
    }

    @Test
    void deveLancarExcecaoSeDocumentoExistente() {
        Customer customerExistente = new Customer(3, "71590186214", "Nome", "email@test.com");
        CustomerDTO dto = new CustomerDTO();
        dto.setDocumentNumber("13802445791");
        dto.setEmail("teste@teste.com");

        when(customerGateway.getCustomerById(3)).thenReturn(customerExistente);
        when(customerGateway.existsByDocumentNumber("13802445791")).thenReturn(true);
        when(customerGateway.existsByEmail(anyString())).thenReturn(false);

        CustomerException ex = assertThrows(CustomerException.class, () -> useCase.execute(3, dto));
        assertEquals(409, ex.getCode());
    }

    @Test
    void deveLancarExcecaoSeEmailExistente() {
        Customer customerExistente = new Customer(4, "71590186214", "Nome", "original@email.com");
        CustomerDTO dto = new CustomerDTO();
        dto.setDocumentNumber("13802445791");
        dto.setEmail("existe@email.com"); // Email que já existe

        when(customerGateway.getCustomerById(4)).thenReturn(customerExistente);
        when(customerGateway.existsByDocumentNumber(anyString())).thenReturn(false);
        when(customerGateway.existsByEmail("existe@email.com")).thenReturn(true);

        CustomerException ex = assertThrows(CustomerException.class, () -> useCase.execute(4, dto));
        assertEquals(409, ex.getCode());
    }
}
