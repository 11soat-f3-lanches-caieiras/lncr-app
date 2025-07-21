package br.com.tp.lncr.core.domain.fooditem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FoodItemImageTest {
    @Test
    void testConstructorAndGetters() {
        FoodItemImage image = new FoodItemImage(1, 2, "data", "location", "file.png", "png", null);
        Assertions.assertEquals(1, image.getId());
        Assertions.assertEquals(2, image.getFoodItemId());
        Assertions.assertEquals("data", image.get_data());
        Assertions.assertEquals("location", image.getLocation());
        Assertions.assertEquals("file.png", image.getFileName());
        Assertions.assertEquals("png", image.getFileExtension());
        Assertions.assertNull(image.getImageError());
    }

    @Test
    void testSetters() {
        FoodItemImage image = new FoodItemImage();
        image.setId(3);
        image.setFoodItemId(4);
        image.set_data("data2");
        image.setLocation("location2");
        image.setFileName("file2.jpg");
        image.setFileExtension("jpg");
        image.setImageError("erro");
        Assertions.assertEquals(3, image.getId());
        Assertions.assertEquals(4, image.getFoodItemId());
        Assertions.assertEquals("data2", image.get_data());
        Assertions.assertEquals("location2", image.getLocation());
        Assertions.assertEquals("file2.jpg", image.getFileName());
        Assertions.assertEquals("jpg", image.getFileExtension());
        Assertions.assertEquals("erro", image.getImageError());
    }
}

