package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities;

import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaFoodItemEntityTest {

    @Test
    void testConstructorAndGetters() {
        JpaFoodItemEntity entity = new JpaFoodItemEntity(1, "Coxinha", "Frango", 10.0, FoodItemCategory.SNACK);
        assertEquals(1, entity.getId());
        assertEquals("Coxinha", entity.getName());
        assertEquals("Frango", entity.getDescription());
        assertEquals(10.0, entity.getPrice());
        assertEquals(FoodItemCategory.SNACK, entity.getCategory());
    }

    @Test
    void testSetters() {
        JpaFoodItemEntity entity = new JpaFoodItemEntity();
        entity.setId(2);
        entity.setName("Pastel");
        entity.setDescription("Carne");
        entity.setPrice(8.0);
        entity.setCategory(FoodItemCategory.SANDWICH);

        assertEquals(2, entity.getId());
        assertEquals("Pastel", entity.getName());
        assertEquals("Carne", entity.getDescription());
        assertEquals(8.0, entity.getPrice());
        assertEquals(FoodItemCategory.SANDWICH, entity.getCategory());
    }
}
