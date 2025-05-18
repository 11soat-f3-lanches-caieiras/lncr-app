package br.com.tp.lanchescaieiras.fooditem.mappers;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemEntity;
import br.com.tp.lanchescaieiras.fooditem.application.mappers.FoodItemMapper;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class FoodItemMapperTest {

    private final FoodItemMapper mapper = Mappers.getMapper(FoodItemMapper.class);

    @Test
    void deveMapearDominioParaJpaComSucesso() {
        FoodItem foodItem = new FoodItem(1, "Sanduíche Natural", "Sanduíche com peito de peru", 15.0, FoodItemCategory.SANDWICH, null);
        JpaFoodItemEntity result = mapper.domainToJpa(foodItem);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("Sanduíche Natural", result.getName());
        Assertions.assertEquals("Sanduíche com peito de peru", result.getDescription());
        Assertions.assertEquals(15.0, result.getPrice());
        Assertions.assertEquals(FoodItemCategory.SANDWICH, result.getCategory());
    }

    @Test
    void deveMapearJpaParaDominioComSucesso() {
        JpaFoodItemEntity jpaEntity = new JpaFoodItemEntity();
        jpaEntity.setId(2);
        jpaEntity.setName("Suco de Laranja");
        jpaEntity.setDescription("Suco natural de laranja");
        jpaEntity.setPrice(8.0);
        jpaEntity.setCategory(FoodItemCategory.BEVERAGE);

        FoodItem result = mapper.jpaToDomain(jpaEntity);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.getId());
        Assertions.assertEquals("Suco de Laranja", result.getName());
        Assertions.assertEquals("Suco natural de laranja", result.getDescription());
        Assertions.assertEquals(8.0, result.getPrice());
        Assertions.assertEquals(FoodItemCategory.BEVERAGE, result.getCategory());
    }

    @Test
    void deveRetornarNuloAoMapearDominioNuloParaJpa() {
        JpaFoodItemEntity result = mapper.domainToJpa(null);

        Assertions.assertNull(result);
    }

    @Test
    void deveRetornarNuloAoMapearJpaNuloParaDominio() {
        FoodItem result = mapper.jpaToDomain(null);

        Assertions.assertNull(result);
    }
}