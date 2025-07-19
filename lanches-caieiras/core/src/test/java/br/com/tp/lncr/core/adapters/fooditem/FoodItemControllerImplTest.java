package br.com.tp.lncr.core.adapters.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemControllerImplTest {
    private FoodItemControllerImpl controller;
    private FoodItemDatabase foodItemDatabase;
    private FoodItemImageRules foodItemImageRules;

    @BeforeEach
    void setUp() {
        foodItemDatabase = Mockito.mock(FoodItemDatabase.class);
        foodItemImageRules = Mockito.mock(FoodItemImageRules.class);
        controller = new FoodItemControllerImpl(foodItemDatabase);
        Mockito.when(foodItemImageRules.getImageLocation()).thenReturn("/img");
    }

    @Test
    void testCreate() {
        FoodItemDTO dto = new FoodItemDTO();
        FoodItemDTO result = controller.create(dto, foodItemDatabase, foodItemImageRules);
        assertNotNull(result);
    }

    @Test
    void testGetAll() {
        assertNotNull(controller.getAll(10, null, false, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testGetById() {
        assertNull(controller.getById(1, false, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testGetByIdList() {
        assertNotNull(controller.getByIdList(Collections.singletonList(1), foodItemDatabase));
    }

    @Test
    void testPartialUpdateById() {
        FoodItemDTO dto = new FoodItemDTO();
        assertNotNull(controller.partialUpdateById(1, dto, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testDeleteById() {
        assertDoesNotThrow(() -> controller.deleteById(1, foodItemDatabase));
    }

    @Test
    void testCreateImage() {
        FoodItemImageDTO dto = new FoodItemImageDTO();
        assertNotNull(controller.create(1, dto, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testGetFoodItemImagesByFoodItemId() {
        assertNotNull(controller.getFoodItemImagesByFoodItemId(1, false, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testDeleteImagesByFoodItemId() {
        assertDoesNotThrow(() -> controller.deleteImagesByFoodItemId(1, foodItemDatabase));
    }
}

