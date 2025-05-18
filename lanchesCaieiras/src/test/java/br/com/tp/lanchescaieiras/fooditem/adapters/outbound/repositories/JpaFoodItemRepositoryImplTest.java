package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemEntity;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemCategory;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras.fooditem.application.mappers.FoodItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

class JpaFoodItemRepositoryImplTest {

    private final JpaFoodItemReposity jpaRepository = Mockito.mock(JpaFoodItemReposity.class);
    private final FoodItemMapper mapper = Mockito.mock(FoodItemMapper.class);
    private final JpaFoodItemRepositoryImpl repository = new JpaFoodItemRepositoryImpl(jpaRepository, mapper, null);

    @Test
    void deveSalvarSandwichComSucesso() {
        FoodItem foodItem = new FoodItem(1, "Sanduíche de Frango", "Sanduíche com frango grelhado", 12.0, FoodItemCategory.SANDWICH, null);
        JpaFoodItemEntity entity = new JpaFoodItemEntity(1, "Sanduíche de Frango", "Sanduíche com frango grelhado", 12.0, FoodItemCategory.SANDWICH);

        Mockito.when(mapper.domainToJpa(foodItem)).thenReturn(entity);
        Mockito.when(jpaRepository.save(entity)).thenReturn(entity);
        Mockito.when(mapper.jpaToDomain(entity)).thenReturn(foodItem);

        FoodItem result = repository.save(foodItem);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Sanduíche de Frango", result.getName());
    }

    @Test
    void deveRetornarBebidasPorCategoria() {
        JpaFoodItemEntity entity = new JpaFoodItemEntity(1, "Refrigerante", "Refrigerante de cola", 5.0, FoodItemCategory.DRINK);
        Mockito.when(jpaRepository.findAllByCategory(10, "BEVERAGE")).thenReturn(List.of(entity));
        Mockito.when(mapper.jpaToDomain(entity)).thenReturn(new FoodItem(1, "Refrigerante", "Refrigerante de cola", 5.0, FoodItemCategory.DRINK, null));

        List<FoodItem> result = repository.findAllByCategory(10, "BEVERAGE");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Refrigerante", result.get(0).getName());
    }

    @Test
    void deveRetornarSobremesaPorId() {
        JpaFoodItemEntity entity = new JpaFoodItemEntity(1, "Sorvete", "Sorvete de chocolate", 8.0, FoodItemCategory.DESSERT);
        Mockito.when(jpaRepository.findById(1)).thenReturn(Optional.of(entity));
        Mockito.when(mapper.jpaToDomain(entity)).thenReturn(new FoodItem(1, "Sorvete", "Sorvete de chocolate", 8.0, FoodItemCategory.DESSERT, null));

        Optional<FoodItem> result = repository.findById(1);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Sorvete", result.get().getName());
    }

    @Test
    void deveAtualizarParcialmenteSandwich() {
        JpaFoodItemEntity existingEntity = new JpaFoodItemEntity(1, "Sanduíche de Atum", "Sanduíche com atum", 10.0, FoodItemCategory.SANDWICH);
        FoodItem updatedFoodItem = new FoodItem(null, null, "Sanduíche de Atum com Queijo", 11.0, null, null);

        Mockito.when(jpaRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        Mockito.when(jpaRepository.save(existingEntity)).thenReturn(existingEntity);
        Mockito.when(mapper.jpaToDomain(existingEntity)).thenReturn(new FoodItem(1, "Sanduíche de Atum", "Sanduíche de Atum com Queijo", 11.0, FoodItemCategory.SANDWICH, null));

        Optional<FoodItem> result = repository.partialUpdateFoodItemById(1, updatedFoodItem);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Sanduíche de Atum com Queijo", result.get().getDescription());
        Assertions.assertEquals(11.0, result.get().getPrice());
    }

    @Test
    void deveLancarExcecaoAoDeletarBebidaInexistente() {
        Mockito.when(jpaRepository.findById(99)).thenReturn(Optional.empty());

        FoodItemException exception = Assertions.assertThrows(FoodItemException.class, () -> repository.deleteFoodItemById(99));

        Assertions.assertEquals("Erro ao deletar o item de alimentação com ID: 99", exception.getMessage());
    }
}