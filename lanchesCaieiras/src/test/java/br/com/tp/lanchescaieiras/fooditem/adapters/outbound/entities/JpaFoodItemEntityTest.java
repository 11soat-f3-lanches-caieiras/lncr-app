package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities;

import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JpaFoodItemEntityTest {

    @Test
    void shouldCreateEntityWithAllFields() {
        JpaFoodItemEntity entity = new JpaFoodItemEntity(1, "Burger", "Delicious beef burger", 10.99, FoodItemCategory.SANDWICH);

        Assertions.assertEquals(1, entity.getId());
        Assertions.assertEquals("Burger", entity.getName());
        Assertions.assertEquals("Delicious beef burger", entity.getDescription());
        Assertions.assertEquals(10.99, entity.getPrice());
        Assertions.assertEquals(FoodItemCategory.SANDWICH, entity.getCategory());
    }

    @Test
    void shouldAllowUpdatingFields() {
        JpaFoodItemEntity entity = new JpaFoodItemEntity();
        entity.setId(2);
        entity.setName("Sandwich");
        entity.setDescription("Sandwich");
        entity.setPrice(15.49);
        entity.setCategory(FoodItemCategory.SANDWICH);

        Assertions.assertEquals(2, entity.getId());
        Assertions.assertEquals("Sandwich", entity.getName());
        Assertions.assertEquals("Sandwich", entity.getDescription());
        Assertions.assertEquals(15.49, entity.getPrice());
        Assertions.assertEquals(FoodItemCategory.SANDWICH, entity.getCategory());
    }

    @Test
    void shouldHandleNullValues() {
        JpaFoodItemEntity entity = new JpaFoodItemEntity(null, null, null, null, null);

        Assertions.assertNull(entity.getId());
        Assertions.assertNull(entity.getName());
        Assertions.assertNull(entity.getDescription());
        Assertions.assertNull(entity.getPrice());
        Assertions.assertNull(entity.getCategory());
    }

    @Test
    void shouldHandleNegativePrice() {
        JpaFoodItemEntity entity = new JpaFoodItemEntity(3, "Coca", "Coca lata", -5.0, FoodItemCategory.DRINK);

        Assertions.assertEquals(-5.0, entity.getPrice());
    }
}