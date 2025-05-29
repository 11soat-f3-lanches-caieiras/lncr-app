package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemEntity;
import br.com.tp.lanchescaieiras.fooditem.application.mappers.FoodItemMapper;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JpaFoodItemRepositoryImplTest {

    @Test
    void testSaveSuccess() {
        JpaFoodItemReposity repo = mock(JpaFoodItemReposity.class);
        FoodItemMapper mapper = mock(FoodItemMapper.class);
        JpaFoodItemEntity entity = new JpaFoodItemEntity();
        FoodItem foodItem = new FoodItem();
        when(mapper.domainToJpa(foodItem)).thenReturn(entity);
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.jpaToDomain(entity)).thenReturn(foodItem);

        JpaFoodItemRepositoryImpl impl = new JpaFoodItemRepositoryImpl(repo, mapper, null);
        FoodItem result = impl.save(foodItem);

        assertEquals(foodItem, result);
    }

    @Test
    void testSaveThrowsDataIntegrityViolationException() {
        JpaFoodItemReposity repo = mock(JpaFoodItemReposity.class);
        FoodItemMapper mapper = mock(FoodItemMapper.class);
        JpaFoodItemEntity entity = new JpaFoodItemEntity();
        FoodItem foodItem = new FoodItem();
        when(mapper.domainToJpa(foodItem)).thenReturn(entity);
        when(repo.save(entity)).thenThrow(new DataIntegrityViolationException("duplicate"));

        JpaFoodItemRepositoryImpl impl = new JpaFoodItemRepositoryImpl(repo, mapper, null);

        FoodItemException ex = assertThrows(FoodItemException.class, () -> impl.save(foodItem));
        assertEquals(409, ex.getCode());
    }

    @Test
    void testSaveThrowsGenericException() {
        JpaFoodItemReposity repo = mock(JpaFoodItemReposity.class);
        FoodItemMapper mapper = mock(FoodItemMapper.class);
        JpaFoodItemEntity entity = new JpaFoodItemEntity();
        FoodItem foodItem = new FoodItem();
        when(mapper.domainToJpa(foodItem)).thenReturn(entity);
        when(repo.save(entity)).thenThrow(new RuntimeException("fail"));

        JpaFoodItemRepositoryImpl impl = new JpaFoodItemRepositoryImpl(repo, mapper, null);

        FoodItemException ex = assertThrows(FoodItemException.class, () -> impl.save(foodItem));
        assertEquals(500, ex.getCode());
    }

    @Test
    void testFindAllByCategoryWithCategory() {
        JpaFoodItemReposity repo = mock(JpaFoodItemReposity.class);
        FoodItemMapper mapper = mock(FoodItemMapper.class);
        JpaFoodItemEntity entity = new JpaFoodItemEntity();
        FoodItem foodItem = new FoodItem();
        when(repo.findAllByCategory(5, "snack")).thenReturn(List.of(entity));
        when(mapper.jpaToDomain(entity)).thenReturn(foodItem);

        JpaFoodItemRepositoryImpl impl = new JpaFoodItemRepositoryImpl(repo, mapper, null);
        List<FoodItem> result = impl.findAllByCategory(5, "snack");

        assertEquals(1, result.size());
        assertEquals(foodItem, result.get(0));
    }

    @Test
    void testFindAllByCategoryWithoutCategory() {
        JpaFoodItemReposity repo = mock(JpaFoodItemReposity.class);
        FoodItemMapper mapper = mock(FoodItemMapper.class);
        JpaFoodItemEntity entity = new JpaFoodItemEntity();
        FoodItem foodItem = new FoodItem();
        when(repo.findAll(Pageable.ofSize(3))).thenReturn(new PageImpl<>(List.of(entity)));
        when(mapper.jpaToDomain(entity)).thenReturn(foodItem);

        JpaFoodItemRepositoryImpl impl = new JpaFoodItemRepositoryImpl(repo, mapper, null);
        List<FoodItem> result = impl.findAllByCategory(3, null);

        assertEquals(1, result.size());
        assertEquals(foodItem, result.get(0));
    }

    @Test
    void testFindAll() {
        JpaFoodItemReposity repo = mock(JpaFoodItemReposity.class);
        FoodItemMapper mapper = mock(FoodItemMapper.class);
        JpaFoodItemEntity entity = new JpaFoodItemEntity();
        FoodItem foodItem = new FoodItem();
        when(repo.findAll(Pageable.ofSize(2))).thenReturn(new PageImpl<>(List.of(entity)));
        when(mapper.jpaToDomain(entity)).thenReturn(foodItem);

        JpaFoodItemRepositoryImpl impl = new JpaFoodItemRepositoryImpl(repo, mapper, null);
        List<FoodItem> result = impl.findAll(2);

        assertEquals(1, result.size());
        assertEquals(foodItem, result.get(0));
    }

    @Test
    void testFindByIdFound() {
        JpaFoodItemReposity repo = mock(JpaFoodItemReposity.class);
        FoodItemMapper mapper = mock(FoodItemMapper.class);
        JpaFoodItemEntity entity = new JpaFoodItemEntity();
        FoodItem foodItem = new FoodItem();
        when(repo.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.jpaToDomain(entity)).thenReturn(foodItem);

        JpaFoodItemRepositoryImpl impl = new JpaFoodItemRepositoryImpl(repo, mapper, null);
        Optional<FoodItem> result = impl.findById(1);

        assertTrue(result.isPresent());
        assertEquals(foodItem, result.get());
    }

    @Test
    void testFindByIdNotFound() {
        JpaFoodItemReposity repo = mock(JpaFoodItemReposity.class);
        FoodItemMapper mapper = mock(FoodItemMapper.class);
        when(repo.findById(2)).thenReturn(Optional.empty());

        JpaFoodItemRepositoryImpl impl = new JpaFoodItemRepositoryImpl(repo, mapper, null);
        Optional<FoodItem> result = impl.findById(2);

        assertTrue(result.isEmpty());
    }

    @Test
    void testPartialUpdateFoodItemById() {
        JpaFoodItemReposity repo = mock(JpaFoodItemReposity.class);
        FoodItemMapper mapper = mock(FoodItemMapper.class);
        JpaFoodItemEntity entity = new JpaFoodItemEntity();
        FoodItem foodItem = new FoodItem();
        when(repo.findById(1)).thenReturn(Optional.of(entity));
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.jpaToDomain(entity)).thenReturn(foodItem);

        JpaFoodItemRepositoryImpl impl = new JpaFoodItemRepositoryImpl(repo, mapper, null);
        Optional<FoodItem> result = impl.partialUpdateFoodItemById(1, foodItem);

        assertTrue(result.isPresent());
        assertEquals(foodItem, result.get());
    }

    @Test
    void testPartialUpdateFoodItemByIdNotFound() {
        JpaFoodItemReposity repo = mock(JpaFoodItemReposity.class);
        FoodItemMapper mapper = mock(FoodItemMapper.class);
        when(repo.findById(2)).thenReturn(Optional.empty());

        JpaFoodItemRepositoryImpl impl = new JpaFoodItemRepositoryImpl(repo, mapper, null);
        Optional<FoodItem> result = impl.partialUpdateFoodItemById(2, new FoodItem());

        assertTrue(result.isEmpty());
    }

    @Test
    void testDeleteFoodItemByIdSuccess() {
        JpaFoodItemReposity repo = mock(JpaFoodItemReposity.class);
        FoodItemMapper mapper = mock(FoodItemMapper.class);
        JpaFoodItemEntity entity = new JpaFoodItemEntity();
        when(repo.findById(1)).thenReturn(Optional.of(entity));
        doNothing().when(repo).delete(entity);

        JpaFoodItemRepositoryImpl impl = new JpaFoodItemRepositoryImpl(repo, mapper, null);
        assertDoesNotThrow(() -> impl.deleteFoodItemById(1));
    }

    @Test
    void testDeleteFoodItemByIdThrows() {
        JpaFoodItemReposity repo = mock(JpaFoodItemReposity.class);
        FoodItemMapper mapper = mock(FoodItemMapper.class);
        when(repo.findById(2)).thenReturn(Optional.of(new JpaFoodItemEntity()));
        doThrow(new RuntimeException("fail")).when(repo).delete(any());

        JpaFoodItemRepositoryImpl impl = new JpaFoodItemRepositoryImpl(repo, mapper, null);
        assertThrows(FoodItemException.class, () -> impl.deleteFoodItemById(2));
    }
}
