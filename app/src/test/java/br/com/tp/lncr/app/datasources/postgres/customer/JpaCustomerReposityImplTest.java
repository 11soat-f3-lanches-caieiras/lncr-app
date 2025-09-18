package br.com.tp.lncr.app.datasources.postgres.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JpaCustomerReposityImplTest {

    @Mock
    private JpaCustomerRepository jpaCustomerRepository;
    @Mock
    private JpaCustomerMapper jpaCustomerMapper;

    private JpaCustomerReposityImpl repository;

    @BeforeEach
    void setUp() {
        repository = new JpaCustomerReposityImpl(jpaCustomerRepository, jpaCustomerMapper);
    }

    @Test
    void shouldSaveCustomerSuccessfully() {
        CustomerDTO inputDto = new CustomerDTO(null, "12345678901", "João Silva", "joao@email.com");
        JpaCustomerEntity mappedEntity = new JpaCustomerEntity(null, "12345678901", "João Silva", "joao@email.com");
        JpaCustomerEntity savedEntity = new JpaCustomerEntity(1, "12345678901", "João Silva", "joao@email.com");
        CustomerDTO expectedDto = new CustomerDTO(1, "12345678901", "João Silva", "joao@email.com");

        when(jpaCustomerMapper.customerDtoToJpa(inputDto)).thenReturn(mappedEntity);
        when(jpaCustomerRepository.save(mappedEntity)).thenReturn(savedEntity);
        when(jpaCustomerMapper.jpaCustomerToDTO(savedEntity)).thenReturn(expectedDto);

        CustomerDTO result = repository.save(inputDto);

        assertEquals(expectedDto, result);
        verify(jpaCustomerMapper).customerDtoToJpa(inputDto);
        verify(jpaCustomerRepository).save(mappedEntity);
        verify(jpaCustomerMapper).jpaCustomerToDTO(savedEntity);
    }

    @Test
    void shouldFindCustomerByIdWhenExists() {
        Integer customerId = 1;
        JpaCustomerEntity entity = new JpaCustomerEntity(1, "12345678901", "João Silva", "joao@email.com");
        CustomerDTO expectedDto = new CustomerDTO(1, "12345678901", "João Silva", "joao@email.com");

        when(jpaCustomerRepository.findById(customerId)).thenReturn(Optional.of(entity));
        when(jpaCustomerMapper.jpaCustomerToDTO(entity)).thenReturn(expectedDto);

        Optional<CustomerDTO> result = repository.findById(customerId);

        assertTrue(result.isPresent());
        assertEquals(expectedDto, result.get());
        verify(jpaCustomerRepository).findById(customerId);
        verify(jpaCustomerMapper).jpaCustomerToDTO(entity);
    }

    @Test
    void shouldReturnEmptyOptionalWhenCustomerNotFoundById() {
        Integer customerId = 999;

        when(jpaCustomerRepository.findById(customerId)).thenReturn(Optional.empty());

        Optional<CustomerDTO> result = repository.findById(customerId);

        assertFalse(result.isPresent());
        verify(jpaCustomerRepository).findById(customerId);
        verifyNoInteractions(jpaCustomerMapper);
    }

    @Test
    void shouldFindAllCustomersWithLimit() {
        Integer limit = 5;
        List<JpaCustomerEntity> entities = Arrays.asList(
                new JpaCustomerEntity(1, "12345678901", "João Silva", "joao@email.com"),
                new JpaCustomerEntity(2, "98765432100", "Maria Santos", "maria@email.com")
        );
        CustomerDTO dto1 = new CustomerDTO(1, "12345678901", "João Silva", "joao@email.com");
        CustomerDTO dto2 = new CustomerDTO(2, "98765432100", "Maria Santos", "maria@email.com");

        when(jpaCustomerRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(entities));
        when(jpaCustomerMapper.jpaCustomerToDTO(entities.get(0))).thenReturn(dto1);
        when(jpaCustomerMapper.jpaCustomerToDTO(entities.get(1))).thenReturn(dto2);

        List<CustomerDTO> result = repository.findAll(limit);

        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
        verify(jpaCustomerRepository).findAll(Pageable.ofSize(limit));
    }

    @Test
    void shouldReturnEmptyListWhenNoCustomersFound() {
        Integer limit = 10;

        when(jpaCustomerRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        List<CustomerDTO> result = repository.findAll(limit);

        assertTrue(result.isEmpty());
        verify(jpaCustomerRepository).findAll(Pageable.ofSize(limit));
        verifyNoInteractions(jpaCustomerMapper);
    }

    @Test
    void shouldFindCustomerByDocumentNumberWhenExists() {
        String documentNumber = "12345678901";
        JpaCustomerEntity entity = new JpaCustomerEntity(1, documentNumber, "João Silva", "joao@email.com");
        CustomerDTO expectedDto = new CustomerDTO(1, documentNumber, "João Silva", "joao@email.com");

        when(jpaCustomerRepository.findByDocumentNumber(documentNumber)).thenReturn(Optional.of(entity));
        when(jpaCustomerMapper.jpaCustomerToDTO(entity)).thenReturn(expectedDto);

        Optional<CustomerDTO> result = repository.findByDocumentNumber(documentNumber);

        assertTrue(result.isPresent());
        assertEquals(expectedDto, result.get());
        verify(jpaCustomerRepository).findByDocumentNumber(documentNumber);
        verify(jpaCustomerMapper).jpaCustomerToDTO(entity);
    }

    @Test
    void shouldReturnEmptyOptionalWhenDocumentNumberNotFound() {
        String documentNumber = "99999999999";

        when(jpaCustomerRepository.findByDocumentNumber(documentNumber)).thenReturn(Optional.empty());

        Optional<CustomerDTO> result = repository.findByDocumentNumber(documentNumber);

        assertFalse(result.isPresent());
        verify(jpaCustomerRepository).findByDocumentNumber(documentNumber);
        verifyNoInteractions(jpaCustomerMapper);
    }

    @Test
    void shouldDeleteCustomerById() {
        Integer customerId = 1;

        repository.deleteById(customerId);

        verify(jpaCustomerRepository).deleteById(customerId);
    }

    @Test
    void shouldFindCustomersByIdList() {
        List<Integer> customerIds = Arrays.asList(1, 2);
        List<JpaCustomerEntity> entities = Arrays.asList(
                new JpaCustomerEntity(1, "12345678901", "João Silva", "joao@email.com"),
                new JpaCustomerEntity(2, "98765432100", "Maria Santos", "maria@email.com")
        );
        CustomerDTO dto1 = new CustomerDTO(1, "12345678901", "João Silva", "joao@email.com");
        CustomerDTO dto2 = new CustomerDTO(2, "98765432100", "Maria Santos", "maria@email.com");

        when(jpaCustomerRepository.findByCustomerIdList(customerIds)).thenReturn(entities);
        when(jpaCustomerMapper.jpaCustomerToDTO(entities.get(0))).thenReturn(dto1);
        when(jpaCustomerMapper.jpaCustomerToDTO(entities.get(1))).thenReturn(dto2);

        List<CustomerDTO> result = repository.findByIdList(customerIds);

        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
        verify(jpaCustomerRepository).findByCustomerIdList(customerIds);
    }

    @Test
    void shouldReturnEmptyListWhenIdListReturnsNoResults() {
        List<Integer> customerIds = Arrays.asList(999, 998);

        when(jpaCustomerRepository.findByCustomerIdList(customerIds)).thenReturn(Collections.emptyList());

        List<CustomerDTO> result = repository.findByIdList(customerIds);

        assertTrue(result.isEmpty());
        verify(jpaCustomerRepository).findByCustomerIdList(customerIds);
        verifyNoInteractions(jpaCustomerMapper);
    }

    @Test
    void shouldReturnTrueWhenCustomerExistsByDocumentNumber() {
        String documentNumber = "12345678901";

        when(jpaCustomerRepository.existsByDocumentNumber(documentNumber)).thenReturn(true);

        boolean result = repository.existsByDocumentNumber(documentNumber);

        assertTrue(result);
        verify(jpaCustomerRepository).existsByDocumentNumber(documentNumber);
    }

    @Test
    void shouldReturnFalseWhenCustomerDoesNotExistByDocumentNumber() {
        String documentNumber = "99999999999";

        when(jpaCustomerRepository.existsByDocumentNumber(documentNumber)).thenReturn(false);

        boolean result = repository.existsByDocumentNumber(documentNumber);

        assertFalse(result);
        verify(jpaCustomerRepository).existsByDocumentNumber(documentNumber);
    }

    @Test
    void shouldReturnTrueWhenCustomerExistsByEmail() {
        String email = "joao@email.com";

        when(jpaCustomerRepository.existsByEmail(email)).thenReturn(true);

        boolean result = repository.existsByEmail(email);

        assertTrue(result);
        verify(jpaCustomerRepository).existsByEmail(email);
    }

    @Test
    void shouldReturnFalseWhenCustomerDoesNotExistByEmail() {
        String email = "inexistente@email.com";

        when(jpaCustomerRepository.existsByEmail(email)).thenReturn(false);

        boolean result = repository.existsByEmail(email);

        assertFalse(result);
        verify(jpaCustomerRepository).existsByEmail(email);
    }
}
