package br.com.tp.lncr.core.adapters.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.enums.FoodItemCategory;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;
import br.com.tp.lncr.core.domain.fooditem.FoodItem;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class FoodItemGatewayImplTest {
    private final String BASE64_PNG = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAIAAACQd1PeAAAADElEQVR4nGP4";
    private final FoodItemImageDTO IMAGE_DTO = new FoodItemImageDTO(null,1, BASE64_PNG, null, null, null, null);
    private final List<FoodItemImageDTO> IMAGES = new ArrayList<>(Collections.singletonList(IMAGE_DTO));
    private final FoodItemDTO ITEM_DTO = new FoodItemDTO(1, "X-SALADA", "DESCRIÇÃO", 25.99, FoodItemCategory.SANDWICH.getDescription(), IMAGES);

    private FoodItemGatewayImpl gateway;
    private FoodItemDatabase foodItemDatabase;
    private FoodItemMapper mapper;
    private FoodItemImageRules foodItemImageRules;

    @BeforeEach
    void setUp() {
        foodItemDatabase = Mockito.mock(FoodItemDatabase.class);
        mapper = new FoodItemMapper();
        gateway = new FoodItemGatewayImpl(foodItemDatabase, mapper);
        foodItemImageRules = Mockito.mock(FoodItemImageRules.class);
        Map<String, String> allowedExtensions = new HashMap<>();
        allowedExtensions.put(".jpg", "FFD8");
        allowedExtensions.put(".png", "89504E47");
        Mockito.when(foodItemImageRules.getImageLocation()).thenReturn("/img");
        Mockito.when(foodItemImageRules.getImageMaxSize()).thenReturn(5 * 1024 * 1024); // 5 MB
        Mockito.when(foodItemImageRules.getMaxNumberOfImages()).thenReturn(5);
        Mockito.when(foodItemImageRules.getAllowedExtentions()).thenReturn(allowedExtensions);
        Mockito.when(foodItemDatabase.save(Mockito.any(FoodItemImageDTO.class))).thenReturn(IMAGE_DTO);
    }

    @Test
    void testExistsByName() {
        Mockito.when(foodItemDatabase.existsByName("test")).thenReturn(true);
        assertTrue(gateway.existsByName("test"));
    }

    @Test
    void testGetAllFoodItems() {
        Mockito.when(foodItemDatabase.findAllFoodItems(any(), any(), Mockito.anyBoolean())).thenReturn(Collections.emptyList());
        assertNotNull(gateway.getAllFoodItems(10, null, false));
    }

    @Test
    void testGetFoodItemById() {
        Mockito.when(foodItemDatabase.findFoodItemById(Mockito.anyInt(), Mockito.anyBoolean())).thenReturn(null);
        assertNull(gateway.getFoodItemById(1, false));
    }

    @Test
    void testGetFoodItemByIdList() {
        Mockito.when(foodItemDatabase.findFoodItemByIdList(Mockito.anyList())).thenReturn(Collections.emptyList());
        assertNotNull(gateway.getFoodItemByIdList(Collections.singletonList(1)));
    }

    @Test
    void testSaveFoodItem_Create() {
        FoodItemDTO createdDto = new FoodItemDTO(1, "X-SALADA", "DESCRIÇÃO", 25.99, "SANDWICH", null);
        Mockito.when(foodItemDatabase.save(any(FoodItemDTO.class))).thenReturn(createdDto);
        FoodItem item = new FoodItem(createdDto);
        assertNotNull(gateway.saveFoodItem(item));
    }

    @Test
    void testSaveFoodItem_Save() {
        FoodItem item = new FoodItem(ITEM_DTO);
        Mockito.when(foodItemDatabase.save(any(FoodItemDTO.class))).thenReturn(ITEM_DTO);
        assertNotNull(gateway.saveFoodItem(item));
    }

    @Test
    void testSaveFoodItemImage() {
        FoodItemImage image = new FoodItemImage(IMAGE_DTO, foodItemImageRules);
        Mockito.when(foodItemDatabase.save(any(FoodItemImageDTO.class))).thenReturn(new FoodItemImageDTO());
        assertNotNull(gateway.createFoodItem(image));
    }
}
