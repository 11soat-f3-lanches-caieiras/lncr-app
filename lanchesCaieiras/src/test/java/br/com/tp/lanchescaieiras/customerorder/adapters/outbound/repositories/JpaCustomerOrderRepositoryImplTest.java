package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.entities.JpaCustomerOrderEntity;
import br.com.tp.lanchescaieiras.customerorder.application.mappers.CustomerOrderMapper;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrder;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class JpaCustomerOrderRepositoryImplTest {

    @Test
    void testSave() {
        JpaCustomerOrderRepository repo = mock(JpaCustomerOrderRepository.class);
        CustomerOrderMapper mapper = mock(CustomerOrderMapper.class);
        CustomerOrder domain = new CustomerOrder();
        JpaCustomerOrderEntity entity = new JpaCustomerOrderEntity();
        when(mapper.domainToJpa(domain)).thenReturn(entity);
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.jpatoDomain(entity)).thenReturn(domain);

        JpaCustomerOrderRepositoryImpl impl = new JpaCustomerOrderRepositoryImpl(repo, mapper);
        CustomerOrder result = impl.save(domain);

        assertEquals(domain, result);
    }

    @Test
    void testFindByIdFound() {
        JpaCustomerOrderRepository repo = mock(JpaCustomerOrderRepository.class);
        CustomerOrderMapper mapper = mock(CustomerOrderMapper.class);
        CustomerOrder domain = new CustomerOrder();
        JpaCustomerOrderEntity entity = new JpaCustomerOrderEntity();
        when(repo.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.jpatoDomain(entity)).thenReturn(domain);

        JpaCustomerOrderRepositoryImpl impl = new JpaCustomerOrderRepositoryImpl(repo, mapper);
        CustomerOrder result = impl.findById(1);

        assertEquals(domain, result);
    }

    @Test
    void testFindByIdNotFound() {
        JpaCustomerOrderRepository repo = mock(JpaCustomerOrderRepository.class);
        CustomerOrderMapper mapper = mock(CustomerOrderMapper.class);
        when(repo.findById(2)).thenReturn(Optional.empty());

        JpaCustomerOrderRepositoryImpl impl = new JpaCustomerOrderRepositoryImpl(repo, mapper);
        CustomerOrder result = impl.findById(2);

        assertNull(result);
    }

    @Test
    void testFindByStatusId() {
        JpaCustomerOrderRepository repo = mock(JpaCustomerOrderRepository.class);
        CustomerOrderMapper mapper = mock(CustomerOrderMapper.class);
        JpaCustomerOrderEntity entity = new JpaCustomerOrderEntity();
        CustomerOrder domain = new CustomerOrder();
        when(repo.findByStatusId(3)).thenReturn(List.of(entity));
        when(mapper.jpatoDomain(entity)).thenReturn(domain);

        JpaCustomerOrderRepositoryImpl impl = new JpaCustomerOrderRepositoryImpl(repo, mapper);
        List<CustomerOrder> result = impl.findByStatusId(3);

        assertEquals(1, result.size());
        assertEquals(domain, result.get(0));
    }

    @Test
    void testUpdateStatusByIdFound() {
        JpaCustomerOrderRepository repo = mock(JpaCustomerOrderRepository.class);
        CustomerOrderMapper mapper = mock(CustomerOrderMapper.class);
        JpaCustomerOrderEntity entity = new JpaCustomerOrderEntity();
        CustomerOrder domain = new CustomerOrder();
        when(repo.findById(4)).thenReturn(Optional.of(entity));
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.jpatoDomain(entity)).thenReturn(domain);

        JpaCustomerOrderRepositoryImpl impl = new JpaCustomerOrderRepositoryImpl(repo, mapper);
        CustomerOrder result = impl.updateStatusById(4, 2);

        assertEquals(domain, result);
    }

    @Test
    void testUpdateStatusByIdNotFound() {
        JpaCustomerOrderRepository repo = mock(JpaCustomerOrderRepository.class);
        CustomerOrderMapper mapper = mock(CustomerOrderMapper.class);
        when(repo.findById(5)).thenReturn(Optional.empty());

        JpaCustomerOrderRepositoryImpl impl = new JpaCustomerOrderRepositoryImpl(repo, mapper);
        CustomerOrder result = impl.updateStatusById(5, 2);

        assertNull(result);
    }
}
