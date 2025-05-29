package br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.entities.JpaKitchenOrderEntity;
import br.com.tp.lanchescaieiras.kitchenorder.application.mappers.KitchenOrderMapper;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class JpaKitchenOrderRepositoryImplTest {

    private JpaKitchenOrderRepository jpaKitchenOrderRepository;
    private KitchenOrderMapper kitchenOrderMapper;
    private JpaKitchenOrderRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        jpaKitchenOrderRepository = mock(JpaKitchenOrderRepository.class);
        kitchenOrderMapper = mock(KitchenOrderMapper.class);
        repository = new JpaKitchenOrderRepositoryImpl(jpaKitchenOrderRepository, kitchenOrderMapper);
    }

    @Test
    void testSave() {
        KitchenOrder domain = new KitchenOrder();
        JpaKitchenOrderEntity entity = new JpaKitchenOrderEntity();
        when(kitchenOrderMapper.domainToJpa(domain)).thenReturn(entity);
        when(jpaKitchenOrderRepository.save(entity)).thenReturn(entity);
        when(kitchenOrderMapper.jpatoDomain(entity)).thenReturn(domain);

        KitchenOrder result = repository.save(domain);
        assertEquals(domain, result);
    }

    @Test
    void testFindByIdFound() {
        JpaKitchenOrderEntity entity = new JpaKitchenOrderEntity();
        KitchenOrder domain = new KitchenOrder();
        when(jpaKitchenOrderRepository.findById(1)).thenReturn(Optional.of(entity));
        when(kitchenOrderMapper.jpatoDomain(entity)).thenReturn(domain);

        KitchenOrder result = repository.findById(1);
        assertEquals(domain, result);
    }

    @Test
    void testFindByIdNotFound() {
        when(jpaKitchenOrderRepository.findById(1)).thenReturn(Optional.empty());
        KitchenOrder result = repository.findById(1);
        assertNull(result);
    }

    @Test
    void testFindByStatusId() {
        JpaKitchenOrderEntity entity = new JpaKitchenOrderEntity();
        KitchenOrder domain = new KitchenOrder();
        when(jpaKitchenOrderRepository.findByStatusId(2)).thenReturn(List.of(entity));
        when(kitchenOrderMapper.jpatoDomain(entity)).thenReturn(domain);

        List<KitchenOrder> result = repository.findByStatusId(2);
        assertEquals(1, result.size());
        assertEquals(domain, result.get(0));
    }

    @Test
    void testUpdateStatusByIdFound() {
        JpaKitchenOrderEntity entity = new JpaKitchenOrderEntity();
        KitchenOrder domain = new KitchenOrder();
        when(jpaKitchenOrderRepository.findById(1)).thenReturn(Optional.of(entity));
        when(jpaKitchenOrderRepository.save(entity)).thenReturn(entity);
        when(kitchenOrderMapper.jpatoDomain(entity)).thenReturn(domain);

        KitchenOrder result = repository.updateStatusById(1, 3);
        assertEquals(domain, result);
    }

    @Test
    void testUpdateStatusByIdNotFound() {
        when(jpaKitchenOrderRepository.findById(1)).thenReturn(Optional.empty());
        KitchenOrder result = repository.updateStatusById(1, 3);
        assertNull(result);
    }

    @Test
    void testFindByCustomerOrderId() {
        JpaKitchenOrderEntity entity = new JpaKitchenOrderEntity();
        KitchenOrder domain = new KitchenOrder();
        when(jpaKitchenOrderRepository.findByCustomerOrderId(5)).thenReturn(entity);
        when(kitchenOrderMapper.jpatoDomain(entity)).thenReturn(domain);

        KitchenOrder result = repository.findByCustomerOrderId(5);
        assertEquals(domain, result);
    }
}
