package br.com.tp.lncr.core.adapters.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class FoodItemImageControllerImplTest {
    private final String BASE64_PNG = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAIAAACQd1PeAAAADElEQVR4nGP4";
    private final FoodItemImageDTO IMAGE_DTO = new FoodItemImageDTO(null,1, BASE64_PNG, null, null, null, null);
    private FoodItemImageControllerImpl controller;
    private FoodItemDatabase foodItemDatabase;
    private FoodItemImageRules foodItemImageRules;

    @BeforeEach
    void setUp() {
        foodItemDatabase = Mockito.mock(FoodItemDatabase.class);
        foodItemImageRules = Mockito.mock(FoodItemImageRules.class);
        controller = new FoodItemImageControllerImpl(foodItemDatabase);
        Map<String, String> allowedExtensions = new HashMap<>();
        allowedExtensions.put(".jpg", "FFD8");
        allowedExtensions.put(".png", "89504E47");
        Mockito.when(foodItemImageRules.getImageLocation()).thenReturn("/img");
        Mockito.when(foodItemImageRules.getImageMaxSize()).thenReturn(5 * 1024 * 1024); // 5 MB
        Mockito.when(foodItemImageRules.getMaxNumberOfImages()).thenReturn(5);
        Mockito.when(foodItemImageRules.getAllowedExtentions()).thenReturn(allowedExtensions);
        FoodItemImageDTO image = new FoodItemImageDTO(1, 1, BASE64_PNG, null, "11.png", ".png", null);
        FoodItemImageDTO oldimage = new FoodItemImageDTO(1, 1, BASE64_PNG, null, "11.jpg", ".jpg", null);
        Mockito.when(foodItemDatabase.findFoodItemImageById(1)).thenReturn(oldimage);
        Mockito.when(foodItemDatabase.save(Mockito.any(FoodItemImageDTO.class))).thenReturn(IMAGE_DTO);
    }

    @Test
    void testCreate() {
        FoodItemImageDTO dto = IMAGE_DTO;
        assertNotNull(controller.create(1, dto, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testGetImageById() {
        assertNotNull(controller.getImageById(1, foodItemDatabase, "/img"));
    }

    @Test
    void testUpdateImageById() {
        // Corrige para garantir que o mock retorna um FoodItemImageDTO válido ao atualizar
        FoodItemImageDTO dto = IMAGE_DTO;
        assertNotNull(controller.updateImageById(1, dto, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testDeleteImageById() {
        assertDoesNotThrow(() -> controller.deleteImageById(1, foodItemDatabase));
    }
}
