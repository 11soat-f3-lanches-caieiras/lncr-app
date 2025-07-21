package br.com.tp.lncr.core.commons.dtos.fooditem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FoodItemImageDTOTest {
    @Test
    void createFoodItemImageDTOWithAllFields() {
        FoodItemImageDTO dto = new FoodItemImageDTO(1, 2, "data", "loc", "file.jpg", "jpg", "error");
        Assertions.assertEquals(1, dto.getId());
        Assertions.assertEquals(2, dto.getFoodItemId());
        Assertions.assertEquals("data", dto.get_data());
        Assertions.assertEquals("loc", dto.getLocation());
        Assertions.assertEquals("file.jpg", dto.getFileName());
        Assertions.assertEquals("jpg", dto.getFileExtension());
        Assertions.assertEquals("error", dto.getImageError());
    }

    @Test
    void setAndGetFieldsIndividually() {
        FoodItemImageDTO dto = new FoodItemImageDTO();
        dto.setId(3);
        dto.setFoodItemId(4);
        dto.set_data("imgdata");
        dto.setLocation("location");
        dto.setFileName("img.png");
        dto.setFileExtension("png");
        dto.setImageError("none");
        Assertions.assertEquals(3, dto.getId());
        Assertions.assertEquals(4, dto.getFoodItemId());
        Assertions.assertEquals("imgdata", dto.get_data());
        Assertions.assertEquals("location", dto.getLocation());
        Assertions.assertEquals("img.png", dto.getFileName());
        Assertions.assertEquals("png", dto.getFileExtension());
        Assertions.assertEquals("none", dto.getImageError());
    }

    @Test
    void allowNullFields() {
        FoodItemImageDTO dto = new FoodItemImageDTO(null, null, null, null, null, null, null);
        Assertions.assertNull(dto.getId());
        Assertions.assertNull(dto.getFoodItemId());
        Assertions.assertNull(dto.get_data());
        Assertions.assertNull(dto.getLocation());
        Assertions.assertNull(dto.getFileName());
        Assertions.assertNull(dto.getFileExtension());
        Assertions.assertNull(dto.getImageError());
    }
}

