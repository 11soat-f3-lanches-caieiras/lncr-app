package br.com.tp.lncr.core.adapters.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerController;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerImplTest {
    private CustomerController controller;
    private CustomerDatabase customerDatabase;

    @BeforeEach
    void setUp() {
        customerDatabase = mock(CustomerDatabase.class);
        controller = new CustomerControllerImpl(customerDatabase);
    }

    @Test
    void testCreate() {
        CustomerDTO dto = new CustomerDTO(1, "71590186214", "Tito", "tito@email.com");
        when(customerDatabase.save(any())).thenReturn(dto);
        CustomerDTO result = controller.create(dto);
        assertEquals(dto.getId(), result.getId());
    }

    @Test
    void testDelete() {
        // Mock para verificar se o cliente existe antes de deletar
        CustomerDTO dto = new CustomerDTO(1, "71590186214", "Tito", "tito@email.com");
        when(customerDatabase.findById(1)).thenReturn(Optional.of(dto));
        doNothing().when(customerDatabase).deleteById(1);
        assertDoesNotThrow(() -> controller.delete(1));
    }

    @Test
    void testGetAll() {
        CustomerDTO dto = new CustomerDTO(1, "71590186214", "Tito", "tito@email.com");
        when(customerDatabase.findAll(any())).thenReturn(List.of(dto));
        List<CustomerDTO> result = controller.getAll(Optional.of(10));
        assertEquals(1, result.size());
    }

    @Test
    void testGetByDocumentNumber() {
        // Usar um CPF válido ao invés de "123"
        CustomerDTO dto = new CustomerDTO(1, "71590186214", "Tito", "tito@email.com");
        when(customerDatabase.findByDocumentNumber("71590186214")).thenReturn(Optional.of(dto));
        CustomerDTO result = controller.getByDocumentNumber("71590186214");
        assertEquals("71590186214", result.getDocumentNumber());
    }

    @Test
    void testGetById() {
        CustomerDTO dto = new CustomerDTO(1, "71590186214", "Tito", "tito@email.com");
        when(customerDatabase.findById(1)).thenReturn(Optional.of(dto));
        CustomerDTO result = controller.getById(1);
        assertEquals(1, result.getId());
    }

    @Test
    void testGetByIdList() {
        CustomerDTO dto = new CustomerDTO(1, "71590186214", "Tito", "tito@email.com");
        when(customerDatabase.findByIdList(any())).thenReturn(List.of(dto));
        List<CustomerDTO> result = controller.getByIdList(List.of(1));
        assertEquals(1, result.size());
    }

    @Test
    void testPartialUpdateById() {
        CustomerDTO existingDto = new CustomerDTO(1, "71590186214", "Tito", "tito@email.com");
        // Para atualização parcial, vamos simular apenas mudança de nome, mantendo os outros dados
        CustomerDTO updateDto = new CustomerDTO();
        updateDto.setName("Tito Updated");
        updateDto.setDocumentNumber("71590186214"); // Deve manter o CPF original
        updateDto.setEmail("tito@email.com");

        CustomerDTO updatedDto = new CustomerDTO(1, "71590186214", "Tito Updated", "tito@email.com");

        when(customerDatabase.findById(1)).thenReturn(Optional.of(existingDto));
        when(customerDatabase.save(any())).thenReturn(updatedDto);

        CustomerDTO result = controller.partialUpdateById(1, updateDto);
        assertEquals(1, result.getId());
        assertEquals("Tito Updated", result.getName());
        assertEquals("71590186214", result.getDocumentNumber()); // Deve manter o CPF original
        assertEquals("tito@email.com", result.getEmail()); // Deve manter o email original
    }
}
