package br.com.tp.lncr.core.domain.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.enums.FoodItemCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FoodItemTest {
    @Test
    void testConstructorAndGetters() {
        FoodItemImage image = new FoodItemImage(1, 1, "data", "location", "file.png", "png", null);
        FoodItem item = new FoodItem(1, "Coxinha", "Salgado", 7.5, FoodItemCategory.SNACK, List.of(image));
        Assertions.assertEquals(1, item.getId());
        Assertions.assertEquals("Coxinha", item.getName());
        Assertions.assertEquals("Salgado", item.getDescription());
        Assertions.assertEquals(7.5, item.getPrice());
        Assertions.assertEquals(FoodItemCategory.SNACK, item.getCategory());
        Assertions.assertEquals(1, item.getImages().size());
    }

    @Test
    void testSetters() {
        FoodItem item = new FoodItem();
        item.setId(2);
        item.setName("Pastel");
        item.setDescription("Pastel de queijo");
        item.setPrice(8.0);
        item.setCategory(FoodItemCategory.DESSERT);
        Assertions.assertEquals(2, item.getId());
        Assertions.assertEquals("Pastel", item.getName());
        Assertions.assertEquals("Pastel de queijo", item.getDescription());
        Assertions.assertEquals(8.0, item.getPrice());
        Assertions.assertEquals(FoodItemCategory.DESSERT, item.getCategory());
    }

    @Test
    void testDTOConstructor() {
        FoodItemDTO dto = new FoodItemDTO();
        dto.setId(3);
        dto.setName("Brigadeiro");
        dto.setDescription("Doce de chocolate");
        dto.setPrice(3.0);
        dto.setCategory("DESSERT");
        FoodItem item = new FoodItem(dto);
        Assertions.assertEquals(3, item.getId());
        Assertions.assertEquals("Brigadeiro", item.getName());
        Assertions.assertEquals("Doce de chocolate", item.getDescription());
        Assertions.assertEquals(3.0, item.getPrice());
        Assertions.assertEquals(FoodItemCategory.DESSERT, item.getCategory());
    }
}

