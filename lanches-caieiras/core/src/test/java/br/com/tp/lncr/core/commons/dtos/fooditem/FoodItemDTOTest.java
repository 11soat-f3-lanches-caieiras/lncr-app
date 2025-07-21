package br.com.tp.lncr.core.commons.dtos.fooditem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class FoodItemDTOTest {
    @Test
    void createFoodItemDTOWithAllFields() {
        FoodItemImageDTO image = new FoodItemImageDTO(1, 2, "data", "loc", "file.jpg", "jpg", null);
        List<FoodItemImageDTO> images = Collections.singletonList(image);
        FoodItemDTO dto = new FoodItemDTO(10, "Pizza", "Mussarela", 30.0, "Lanche", images);
        Assertions.assertEquals(10, dto.getId());
        Assertions.assertEquals("Pizza", dto.getName());
        Assertions.assertEquals("Mussarela", dto.getDescription());
        Assertions.assertEquals(30.0, dto.getPrice());
        Assertions.assertEquals("Lanche", dto.getCategory());
        Assertions.assertEquals(images, dto.getImages());
    }

    @Test
    void setAndGetFieldsIndividually() {
        FoodItemDTO dto = new FoodItemDTO();
        FoodItemImageDTO image = new FoodItemImageDTO();
        List<FoodItemImageDTO> images = List.of(image);
        dto.setId(20);
        dto.setName("Hamburguer");
        dto.setDescription("Carne");
        dto.setPrice(25.0);
        dto.setCategory("Lanche");
        dto.setImages(images);
        Assertions.assertEquals(20, dto.getId());
        Assertions.assertEquals("Hamburguer", dto.getName());
        Assertions.assertEquals("Carne", dto.getDescription());
        Assertions.assertEquals(25.0, dto.getPrice());
        Assertions.assertEquals("Lanche", dto.getCategory());
        Assertions.assertEquals(images, dto.getImages());
    }

    @Test
    void allowNullFields() {
        FoodItemDTO dto = new FoodItemDTO(null, null, null, null, null, null);
        Assertions.assertNull(dto.getId());
        Assertions.assertNull(dto.getName());
        Assertions.assertNull(dto.getDescription());
        Assertions.assertNull(dto.getPrice());
        Assertions.assertNull(dto.getCategory());
        Assertions.assertNull(dto.getImages());
    }
}

