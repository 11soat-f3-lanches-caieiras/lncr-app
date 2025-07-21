package br.com.tp.lncr.core.adapters.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.enums.FoodItemCategory;
import br.com.tp.lncr.core.domain.fooditem.FoodItem;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FoodItemMapperTest {
    private final String BASE64_PNG = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAIAAACQd1PeAAAADElEQVR4nGP4";
    private final FoodItemImageDTO IMAGE_DTO = new FoodItemImageDTO(null,1, BASE64_PNG, null, null, null, null);
    private final FoodItemImage IMAGE = new FoodItemImage(null,1, BASE64_PNG, null, null, null, null);
    private final List<FoodItemImage> IMAGES = new ArrayList<>(Collections.singletonList(IMAGE));
    private final List<FoodItemImageDTO> IMAGES_DTO = new ArrayList<>(Collections.singletonList(IMAGE_DTO));
    private final FoodItemDTO ITEM_DTO = new FoodItemDTO(1, "X-SALADA", "DESCRIÇÃO", 25.99, FoodItemCategory.SANDWICH.getDescription(), IMAGES_DTO);
    private final FoodItem ITEM = new FoodItem(1, "X-SALADA", "DESCRIÇÃO", 25.99, FoodItemCategory.SANDWICH, IMAGES);

    @Test
    void testFoodItemToDTOAndBack() {
        FoodItemMapper mapper = new FoodItemMapper();
        FoodItem item = ITEM;
        FoodItemDTO dto = mapper.foodItemToDTO(item);
        assertNotNull(dto);
        assertNotNull(mapper.foodItemToDomain(dto));
    }

    @Test
    void testFoodItemImageToDTOAndBack() {
        FoodItemMapper mapper = new FoodItemMapper();
        FoodItemImage image = IMAGE;
        FoodItemImageDTO dto = mapper.foodItemImageToDTO(image);
        assertNotNull(dto);
        assertNotNull(mapper.foodItemImageToDomain(dto));
    }

    @Test
    void testFoodItemImageToDtoList() {
        FoodItemMapper mapper = new FoodItemMapper();
        assertNotNull(mapper.foodItemImageToDtoList(Collections.emptyList()));
    }

    @Test
    void testFoodItemImageToDomainList() {
        FoodItemMapper mapper = new FoodItemMapper();
        assertNotNull(mapper.foodItemImageToDomainList(Collections.emptyList()));
    }
}

