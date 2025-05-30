package br.com.tp.lanchescaieiras.fooditem.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodItemCategoryTest {

    @Test
    void testEnumValues() {
        assertEquals(FoodItemCategory.SANDWICH, FoodItemCategory.valueOf("SANDWICH"));
        assertEquals(FoodItemCategory.DRINK, FoodItemCategory.valueOf("DRINK"));
        assertEquals(FoodItemCategory.DESSERT, FoodItemCategory.valueOf("DESSERT"));
        assertEquals(FoodItemCategory.SNACK, FoodItemCategory.valueOf("SNACK"));
    }
}
