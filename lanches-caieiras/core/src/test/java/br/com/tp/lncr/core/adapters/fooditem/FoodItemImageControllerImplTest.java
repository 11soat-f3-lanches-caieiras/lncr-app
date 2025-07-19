package br.com.tp.lncr.core.adapters.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemImageControllerImplTest {
    private FoodItemImageControllerImpl controller;
    private FoodItemDatabase foodItemDatabase;
    private FoodItemImageRules foodItemImageRules;

    @BeforeEach
    void setUp() {
        foodItemDatabase = Mockito.mock(FoodItemDatabase.class);
        foodItemImageRules = Mockito.mock(FoodItemImageRules.class);
        controller = new FoodItemImageControllerImpl(foodItemDatabase);
        Mockito.when(foodItemImageRules.getImageLocation()).thenReturn("/img");
    }

    @Test
    void testCreate() {
        FoodItemImageDTO dto = new FoodItemImageDTO();
        assertNotNull(controller.create(1, dto, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testGetImageById() {
        assertNull(controller.getImageById(1, foodItemDatabase, "/img"));
    }

    @Test
    void testUpdateImageById() {
        FoodItemImageDTO dto = new FoodItemImageDTO();
        assertNotNull(controller.updateImageById(1, dto, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testDeleteImageById() {
        assertDoesNotThrow(() -> controller.deleteImageById(1, foodItemDatabase));
    }
}

