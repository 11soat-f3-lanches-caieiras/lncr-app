package br.com.tp.lanchescaieiras.fooditem.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.fooditem.application.services.FoodItemServicesImpl;
import br.com.tp.lanchescaieiras.fooditem.domain.*;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.config.FoodItemImageConfig;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoodItemControllerImplTest {

    private FoodItemServicesImpl service;
    private FoodItemImageConfig imageConfig;
    private FoodItemConfig foodItemConfig;
    private FoodItemControllerImpl controller;

    @BeforeEach
    void setup() {
        service = mock(FoodItemServicesImpl.class);
        imageConfig = mock(FoodItemImageConfig.class);
        foodItemConfig = mock(FoodItemConfig.class);

        when(imageConfig.getExtensions()).thenReturn(Map.of("png", "89504E47", "jpg", "FFD8FF"));
        when(imageConfig.getMaxSize()).thenReturn(10000);
        when(imageConfig.getLocationPrefix()).thenReturn("/images");
        when(foodItemConfig.getLocationPrefix()).thenReturn("/foodItems");

        controller = new FoodItemControllerImpl(service, imageConfig, foodItemConfig);
    }

    @Test
    void testCreateFoodItem() {
        FoodItemImage img = new FoodItemImage();
        img.set_data("data");
        img.setFileExtension("png");
        FoodItem foodItem = new FoodItem();
        foodItem.setImages(List.of(img));

        FoodItem created = new FoodItem(1, null, null, null, null, List.of(img));
        when(service.createFoodItem(any())).thenReturn(created);

        ResponseEntity<FoodItemResponse> response = controller.createFoodItem(foodItem);

        assertEquals(201, response.getStatusCodeValue());
        assertTrue(response.getHeaders().get("Location").get(0).contains("/foodItems/1"));
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().get_content().getId());
    }

    @Test
    void testGetAllFoodItems() {
        when(service.getAllFoodItems(anyInt(), any())).thenReturn(List.of(new FoodItem()));
        ResponseEntity<FoodItemListResponse> response = controller.getAllFoodItems(Optional.of(5), Optional.of("snack"));
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().get_content().size());
    }

    @Test
    void testGetFoodItemById() {
        FoodItem item = new FoodItem();
        when(service.getFoodItem(1)).thenReturn(Optional.of(item));
        ResponseEntity<FoodItemResponse> response = controller.getFoodItemById(1);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(item, response.getBody().get_content());
    }

    @Test
    void testPartialUpdateFoodItemById() {
        FoodItem item = new FoodItem();
        when(service.partialUpdateFoodItemById(eq(1), any())).thenReturn(item);
        ResponseEntity<FoodItemResponse> response = controller.partialUpdateFoodItemById(1, item);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(item, response.getBody().get_content());
    }

    @Test
    void testDeleteFoodItemById() {
        doNothing().when(service).deleteFoodItemById(1);
        ResponseEntity<FoodItemResponse> response = controller.deleteFoodItemById(1);
        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody().get_content());
    }

    @Test
    void testGetImageData() {
        FoodItemImage img = new FoodItemImage();
        img.set_data("data");
        img.setFileName("file.png");
        when(service.getImageData(1)).thenReturn(img);
        ResponseEntity<FoodItemImageDataResponse> response = controller.getImageData(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("data", response.getBody().get_data());
        assertEquals("file.png", response.getBody().getFileName());
    }

    @Test
    void testUpdateImageById() {
        FoodItemImage img = new FoodItemImage();
        img.set_data("iVBORw0KGgoAAAANSUhEUgAAAGcAAACzCAYAAACU7gLAAAAAAXNSR0");
        img.setFileExtension("png");
        when(service.updateImageById(eq(1), any())).thenReturn(img);

        ResponseEntity<FoodItemImageDataResponse> response = controller.updateImageById(1, img);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getHeaders().get("Location").get(0).contains("/images/1"));
        assertNotNull(response.getBody());
    }

    @Test
    void testValidateImageThrows() {
        FoodItemImage img = new FoodItemImage();
        img.set_data(null);
        img.setFileExtension("invalid");
        when(imageConfig.getExtensions()).thenReturn(Map.of("png", "89504E47"));
        when(imageConfig.getMaxSize()).thenReturn(10000);

        assertThrows(FoodItemException.class, () -> controller.updateImageById(1, img));
    }
}
