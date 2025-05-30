package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JpaFoodItemImageEntityTest {

    @Test
    void testConstructorAndGetters() {
        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity(1, 2, "data", "loc", "file.png", "png", null);
        assertEquals(1, entity.getId());
        assertEquals(2, entity.getFoodItemId());
        assertEquals("data", entity.get_data());
        assertEquals("loc", entity.location);
        assertEquals("file.png", entity.getFileName());
        assertEquals("png", entity.getFileExtension());
    }

    @Test
    void testSetters() {
        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity();
        entity.setId(3);
        entity.setFoodItemId(4);
        entity.set_data("abc");
        entity.setLocation("local");
        entity.setFileName("img.jpg");
        entity.setFileExtension("jpg");

        assertEquals(3, entity.getId());
        assertEquals(4, entity.getFoodItemId());
        assertEquals("abc", entity.get_data());
        assertEquals("local", entity.location);
        assertEquals("img.jpg", entity.getFileName());
        assertEquals("jpg", entity.getFileExtension());
    }

    @Test
    void testSetImageId() {
        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity();
        Integer result = entity.setImageId(5, 10);
        assertEquals(Integer.parseInt("105"), result);
    }

    @Test
    void testSetFileName() {
        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity();
        String fileName = entity.setFileName(123, "png");
        assertEquals("123.png", fileName);
    }
}
