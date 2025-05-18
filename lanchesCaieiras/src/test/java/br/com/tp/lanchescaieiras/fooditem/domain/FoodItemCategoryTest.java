package br.com.tp.lanchescaieiras.fooditem.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FoodItemCategoryTest {

    @Test
    void deveConterCategoriaSandwich() {
        Assertions.assertNotNull(FoodItemCategory.valueOf("SANDWICH"));
    }

    @Test
    void deveConterCategoriaDrink() {
        Assertions.assertNotNull(FoodItemCategory.valueOf("DRINK"));
    }

    @Test
    void deveConterCategoriaDessert() {
        Assertions.assertNotNull(FoodItemCategory.valueOf("DESSERT"));
    }

    @Test
    void deveConterCategoriaSnack() {
        Assertions.assertNotNull(FoodItemCategory.valueOf("SNACK"));
    }

    @Test
    void deveLancarExcecaoParaCategoriaInvalida() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> FoodItemCategory.valueOf("INVALID_CATEGORY"));
    }
}