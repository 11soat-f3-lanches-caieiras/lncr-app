package br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.entities.JpaKitchenOrderFoodItemEntity;
import br.com.tp.lanchescaieiras.kitchenorder.application.mappers.KitchenOrderFoodItemMapper;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderFoodItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JpaKitchenOrderFoodItemRepositoryImplTest {

    private JpaKitchenOrderFoodItemRepository jpaKitchenOrderFoodItemRepository;
    private KitchenOrderFoodItemMapper kitchenOrderFoodItemMapper;
    private JpaKitchenOrderFoodItemRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        jpaKitchenOrderFoodItemRepository = mock(JpaKitchenOrderFoodItemRepository.class);
        kitchenOrderFoodItemMapper = mock(KitchenOrderFoodItemMapper.class);
        repository = new JpaKitchenOrderFoodItemRepositoryImpl(jpaKitchenOrderFoodItemRepository, kitchenOrderFoodItemMapper);
    }

    @Test
    void testSave() {
        KitchenOrderFoodItem domain = new KitchenOrderFoodItem();
        JpaKitchenOrderFoodItemEntity entity = new JpaKitchenOrderFoodItemEntity();
        when(kitchenOrderFoodItemMapper.domainToJpa(domain, 1)).thenReturn(entity);
        when(jpaKitchenOrderFoodItemRepository.save(entity)).thenReturn(entity);
        when(kitchenOrderFoodItemMapper.jpaToDomain(entity)).thenReturn(domain);

        KitchenOrderFoodItem result = repository.save(domain, 1);
        assertEquals(domain, result);
    }

    @Test
    void testFindByKitchenOrderId() {
        JpaKitchenOrderFoodItemEntity entity = new JpaKitchenOrderFoodItemEntity();
        KitchenOrderFoodItem domain = new KitchenOrderFoodItem();
        when(jpaKitchenOrderFoodItemRepository.findByKitchenOrderId(2)).thenReturn(List.of(entity));
        when(kitchenOrderFoodItemMapper.jpaToDomain(entity)).thenReturn(domain);

        List<KitchenOrderFoodItem> result = repository.findByKitchenOrderId(2);
        assertEquals(1, result.size());
        assertEquals(domain, result.get(0));
    }
}
