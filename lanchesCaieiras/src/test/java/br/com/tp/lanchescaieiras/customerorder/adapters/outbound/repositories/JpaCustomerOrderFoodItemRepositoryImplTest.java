package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.entities.JpaCustomerOrderFoodItemEntity;
import br.com.tp.lanchescaieiras.customerorder.application.mappers.CustomerOrderFoodItemMapper;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class JpaCustomerOrderFoodItemRepositoryImplTest {

    @Test
    void testSave() {
        JpaCustomerOrderFoodItemRepository repo = mock(JpaCustomerOrderFoodItemRepository.class);
        CustomerOrderFoodItemMapper mapper = mock(CustomerOrderFoodItemMapper.class);
        CustomerOrderFoodItem domain = new CustomerOrderFoodItem();
        JpaCustomerOrderFoodItemEntity entity = new JpaCustomerOrderFoodItemEntity();
        when(mapper.domainToJpa(domain, 1)).thenReturn(entity);
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.jpaToDomain(entity)).thenReturn(domain);

        JpaCustomerOrderFoodItemRepositoryImpl impl = new JpaCustomerOrderFoodItemRepositoryImpl(repo, mapper);
        CustomerOrderFoodItem result = impl.save(domain, 1);

        assertEquals(domain, result);
    }

    @Test
    void testFindByCustomerOrderId() {
        JpaCustomerOrderFoodItemRepository repo = mock(JpaCustomerOrderFoodItemRepository.class);
        CustomerOrderFoodItemMapper mapper = mock(CustomerOrderFoodItemMapper.class);
        JpaCustomerOrderFoodItemEntity entity = new JpaCustomerOrderFoodItemEntity();
        CustomerOrderFoodItem domain = new CustomerOrderFoodItem();
        when(repo.findByCustomerOrderId(2)).thenReturn(List.of(entity));
        when(mapper.jpaToDomain(entity)).thenReturn(domain);

        JpaCustomerOrderFoodItemRepositoryImpl impl = new JpaCustomerOrderFoodItemRepositoryImpl(repo, mapper);
        List<CustomerOrderFoodItem> result = impl.findByCustomerOrderId(2);

        assertEquals(1, result.size());
        assertEquals(domain, result.get(0));
    }
}
