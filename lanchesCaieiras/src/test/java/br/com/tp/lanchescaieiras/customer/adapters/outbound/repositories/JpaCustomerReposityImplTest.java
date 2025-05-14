package br.com.tp.lanchescaieiras.customer.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.entities.JpaCustomerEntity;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import br.com.tp.lanchescaieiras.customer.infraestructure.exceptions.CustomerException;
import br.com.tp.lanchescaieiras.customer.mappers.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JpaCustomerReposityImplTest {

    @Mock
    private JpaCustomerRepository jpaCustomerRepository;

    @Mock
    private CustomerMapper customerMapper;

    private JpaCustomerReposityImpl jpaCustomerReposityImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jpaCustomerReposityImpl = new JpaCustomerReposityImpl(jpaCustomerRepository, customerMapper);
    }

    @Test
    void saveReturnsMappedCustomer() {
        Customer customer = new Customer();
        JpaCustomerEntity entity = new JpaCustomerEntity();
        when(customerMapper.domainToJpa(customer)).thenReturn(entity);
        when(jpaCustomerRepository.save(entity)).thenReturn(entity);
        when(customerMapper.jpaToDomain(entity)).thenReturn(customer);

        Customer result = jpaCustomerReposityImpl.save(customer);

        assertNotNull(result);
        assertEquals(customer, result);
        verify(jpaCustomerRepository, times(1)).save(entity);
    }

    @Test
    void findByDocumentNumberReturnsCustomerWhenFound() {
        String documentNumber = "123456789";
        JpaCustomerEntity entity = new JpaCustomerEntity();
        Customer customer = new Customer();
        when(jpaCustomerRepository.findByDocumentNumber(documentNumber)).thenReturn(Optional.of(entity));
        when(customerMapper.jpaToDomain(entity)).thenReturn(customer);

        Optional<Customer> result = jpaCustomerReposityImpl.findByDocumentNumber(documentNumber);

        assertTrue(result.isPresent());
        assertEquals(customer, result.get());
    }

    @Test
    void findByDocumentNumberThrowsExceptionWhenNotFound() {
        String documentNumber = "123456789";
        when(jpaCustomerRepository.findByDocumentNumber(documentNumber)).thenReturn(Optional.empty());

        CustomerException exception = assertThrows(CustomerException.class, () -> jpaCustomerReposityImpl.findByDocumentNumber(documentNumber));

        assertEquals("Cliente não encontrado com o documento: " + documentNumber, exception.getMessage());
    }

    @Test
    void deleteByIdReturnsTrueWhenCustomerExists() {
        Integer id = 1;
        JpaCustomerEntity entity = new JpaCustomerEntity();
        when(jpaCustomerRepository.findById(id)).thenReturn(Optional.of(entity));

        Boolean result = jpaCustomerReposityImpl.deleteById(id);

        assertTrue(result);
        verify(jpaCustomerRepository, times(1)).delete(entity);
    }

    @Test
    void deleteByIdReturnsFalseWhenCustomerDoesNotExist() {
        Integer id = 1;
        when(jpaCustomerRepository.findById(id)).thenReturn(Optional.empty());

        Boolean result = jpaCustomerReposityImpl.deleteById(id);

        assertFalse(result);
        verify(jpaCustomerRepository, never()).delete(any());
    }

    @Test
    void findAllReturnsMappedCustomers() {
        Customer customer1 = new Customer(null,"15826123907","Teste da Silva","email@teste.com.br");
        Customer customer2 = new Customer(null,"15826123901","Teste da Silva2","email2@teste.com.br");
        JpaCustomerEntity entity1 = new JpaCustomerEntity(customer1);
        JpaCustomerEntity entity2 = new JpaCustomerEntity(customer2);
        jpaCustomerRepository.save(entity1);
        jpaCustomerRepository.save(entity2);

        when(customerMapper.domainToJpa(customer1)).thenReturn(entity1);
        when(customerMapper.domainToJpa(customer2)).thenReturn(entity2);
        when(jpaCustomerRepository.findById(customer1.getId())).thenReturn(Optional.of(entity1));
        when(jpaCustomerRepository.findById(customer2.getId())).thenReturn(Optional.of(entity2));
        when(customerMapper.jpaToDomain(entity1)).thenReturn(customer1);
        when(customerMapper.jpaToDomain(entity2)).thenReturn(customer2);

        Page<JpaCustomerEntity> page = new PageImpl<>(List.of(entity1, entity2));
        when(jpaCustomerRepository.findAll(PageRequest.of(0,2))).thenReturn(page);
        when(customerMapper.jpaToDomain(entity1)).thenReturn(customer1);
        when(customerMapper.jpaToDomain(entity2)).thenReturn(customer2);

        List<Customer> result = jpaCustomerReposityImpl.findAll(2);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(customer1, result.get(0));
        assertEquals(customer2, result.get(1));
    }
}