package br.com.tp.lncr.core.commons.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FoodItemCategoryTest {
    @Test
    void testGetIdAndDescription() {
        Assertions.assertEquals(1, FoodItemCategory.SANDWICH.getId());
        Assertions.assertEquals("Sandwich", FoodItemCategory.SANDWICH.getDescription());
    }

    @Test
    void testFromIdValid() {
        Assertions.assertEquals(FoodItemCategory.DRINK, FoodItemCategory.fromId(2));
    }

    @Test
    void testFromIdInvalid() {
        Exception ex = Assertions.assertThrows(Exception.class, () -> FoodItemCategory.fromId(99));
        Assertions.assertTrue(ex.getMessage().contains("Id da categoria inválido"));
    }

    @Test
    void testFromDescriptionValid() {
        Assertions.assertEquals(FoodItemCategory.DESSERT, FoodItemCategory.fromDescription("Dessert"));
    }

    @Test
    void testFromDescriptionInvalid() {
        Exception ex = Assertions.assertThrows(Exception.class, () -> FoodItemCategory.fromDescription("INVALID"));
        Assertions.assertTrue(ex.getMessage().contains("Categoria inválida"));
    }

    @Test
    void testListOfAllowDescriptionsAndIds() {
        Assertions.assertTrue(FoodItemCategory.listOfAllowDescriptions().contains("Sandwich"));
        Assertions.assertTrue(FoodItemCategory.listOfAllowIds().contains("1"));
    }
}

