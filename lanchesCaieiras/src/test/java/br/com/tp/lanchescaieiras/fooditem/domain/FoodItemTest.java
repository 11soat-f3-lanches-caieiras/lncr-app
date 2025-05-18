package br.com.tp.lanchescaieiras.fooditem.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class FoodItemTest {

    @Test
    void deveCriarFoodItemComCategoriaSandwich() {
        FoodItem foodItem = new FoodItem(1, "Sanduíche Natural", "Sanduíche com peito de peru", 15.0, FoodItemCategory.SANDWICH, null);

        Assertions.assertEquals(1, foodItem.getId());
        Assertions.assertEquals("Sanduíche Natural", foodItem.getName());
        Assertions.assertEquals(FoodItemCategory.SANDWICH, foodItem.getCategory());
    }

    @Test
    void deveCriarFoodItemComCategoriaBebida() {
        FoodItem foodItem = new FoodItem(2, "Suco de Laranja", "Suco natural de laranja", 8.0, FoodItemCategory.BEVERAGE, null);

        Assertions.assertEquals(2, foodItem.getId());
        Assertions.assertEquals("Suco de Laranja", foodItem.getName());
        Assertions.assertEquals(FoodItemCategory.DRINK, foodItem.getCategory());
    }

    @Test
    void deveCriarFoodItemComCategoriaSobremesa() {
        FoodItem foodItem = new FoodItem(3, "Pudim", "Pudim de leite condensado", 10.0, FoodItemCategory.DESSERT, null);

        Assertions.assertEquals(3, foodItem.getId());
        Assertions.assertEquals("Pudim", foodItem.getName());
        Assertions.assertEquals(FoodItemCategory.DESSERT, foodItem.getCategory());
    }

    @Test
    void deveAtualizarCategoriaDeFoodItem() {
        FoodItem foodItem = new FoodItem(4, "Café", "Café preto", 5.0, FoodItemCategory.DRINK, null);
        foodItem.setCategory(FoodItemCategory.DESSERT);

        Assertions.assertEquals(FoodItemCategory.DESSERT, foodItem.getCategory());
    }

    @Test
    void deveAdicionarImagensAoFoodItem() {
        FoodItemImage image = new FoodItemImage("dadosBase64", "imagem.jpg", "jpg");
        FoodItem foodItem = new FoodItem(5, "Milkshake", "Milkshake de chocolate", 12.0, FoodItemCategory.DRINK, null);
        foodItem.setImages(List.of(image));

        Assertions.assertNotNull(foodItem.getImages());
        Assertions.assertEquals(1, foodItem.getImages().size());
        Assertions.assertEquals("imagem.jpg", foodItem.getImages().get(0).getFileName());
    }

    @Test
    void deveLancarExcecaoParaCategoriaInvalida() {
        FoodItem foodItem = new FoodItem();
        Assertions.assertThrows(IllegalArgumentException.class, () -> foodItem.setCategory("INVALID_CATEGORY"));
    }
}