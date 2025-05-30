package br.com.tp.lanchescaieiras.fooditem.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodItemTest {

    @Test
    void testConstructorAndGetters() {
        FoodItemImage img = new FoodItemImage(1, "data", "loc", "file.jpg", "jpg");
        FoodItem item = new FoodItem(10, "Coxinha", "Frango", 8.5, FoodItemCategory.SNACK, List.of(img));

        assertEquals(10, item.getId());
        assertEquals("Coxinha", item.getName());
        assertEquals("Frango", item.getDescription());
        assertEquals(8.5, item.getPrice());
        assertEquals(FoodItemCategory.SNACK, item.getCategory());
        assertEquals(1, item.getImages().size());
    }

    @Test
    void testSetters() {
        FoodItem item = new FoodItem();
        item.setId(2);
        item.setName("Pastel");
        item.setDescription("Carne");
        item.setPrice(7.0);
        item.setCategory(FoodItemCategory.SNACK);

        assertEquals(2, item.getId());
        assertEquals("Pastel", item.getName());
        assertEquals("Carne", item.getDescription());
        assertEquals(7.0, item.getPrice());
        assertEquals(FoodItemCategory.SNACK, item.getCategory());
    }

    @Test
    void testSetCategoryByString() {
        FoodItem item = new FoodItem();
        item.setCategory("drink");
        assertEquals(FoodItemCategory.DRINK, item.getCategory());
    }

    @Test
    void testSetImages() {
        FoodItem item = new FoodItem();
        FoodItemImage img = new FoodItemImage();
        item.setImages(List.of(img));
        assertEquals(1, item.getImages().size());
    }
}
