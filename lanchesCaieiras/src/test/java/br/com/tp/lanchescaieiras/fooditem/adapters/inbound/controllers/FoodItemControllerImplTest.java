package br.com.tp.lanchescaieiras.fooditem.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.fooditem.application.services.FoodItemServicesImpl;
import br.com.tp.lanchescaieiras.fooditem.domain.*;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.config.FoodItemImageConfig;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoodItemControllerImplTest {

    @Mock
    private FoodItemServicesImpl foodItemServices;

    @Mock
    private FoodItemImageConfig imageConfig;

    @InjectMocks
    private FoodItemControllerImpl foodItemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class CreateFoodItem {

        @Test
        void shouldCreateFoodItemSuccessfully() {
            FoodItem foodItem = new FoodItem();
            FoodItemResponse expectedResponse = new FoodItemResponse(foodItem);

            when(foodItemServices.createFoodItem(foodItem)).thenReturn(foodItem);

            ResponseEntity<FoodItemResponse> response = foodItemController.createFoodItem(foodItem);

            assertEquals(201, response.getStatusCodeValue());
            assertEquals(expectedResponse, response.getBody());
            verify(foodItemServices, times(1)).createFoodItem(foodItem);
        }

        @Test
        void shouldThrowExceptionForInvalidImage() {
            FoodItem foodItem = new FoodItem();
            doThrow(new FoodItemException("Invalid image", 400)).when(imageConfig).getExtensions();

            assertThrows(FoodItemException.class, () -> foodItemController.createFoodItem(foodItem));
        }
    }

    @Nested
    class GetAllFoodItems {

        @Test
        void shouldReturnAllFoodItemsWithDefaultLimit() {
            List<FoodItem> foodItems = List.of(new FoodItem());
            FoodItemListResponse expectedResponse = new FoodItemListResponse(foodItems);

            when(foodItemServices.getAllFoodItems(10, null)).thenReturn(foodItems);

            ResponseEntity<FoodItemListResponse> response = foodItemController.getAllFoodItems(Optional.empty(), Optional.empty());

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(expectedResponse, response.getBody());
            verify(foodItemServices, times(1)).getAllFoodItems(10, null);
        }

        @Test
        void shouldThrowExceptionForInvalidLimit() {
            Optional<Integer> invalidLimit = Optional.of(0);

            assertThrows(IllegalArgumentException.class, () -> foodItemController.getAllFoodItems(invalidLimit, Optional.empty()));
        }
    }

    @Nested
    class GetFoodItemById {

        @Test
        void shouldReturnFoodItemById() {
            FoodItem foodItem = new FoodItem();
            FoodItemResponse expectedResponse = new FoodItemResponse(foodItem);

            when(foodItemServices.getFoodItem(1)).thenReturn(Optional.of(foodItem));

            ResponseEntity<FoodItemResponse> response = foodItemController.getFoodItemById(1);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(expectedResponse, response.getBody());
            verify(foodItemServices, times(1)).getFoodItem(1);
        }

        @Test
        void shouldThrowExceptionWhenFoodItemNotFound() {
            when(foodItemServices.getFoodItem(1)).thenReturn(Optional.empty());

            assertThrows(FoodItemException.class, () -> foodItemController.getFoodItemById(1));
        }
    }

    @Nested
    class PartialUpdateFoodItemById {

        @Test
        void shouldPartiallyUpdateFoodItemSuccessfully() {
            FoodItem foodItem = new FoodItem();
            FoodItem updatedFoodItem = new FoodItem();
            FoodItemResponse expectedResponse = new FoodItemResponse(updatedFoodItem);

            when(foodItemServices.partialUpdateFoodItemById(1, foodItem)).thenReturn(updatedFoodItem);

            ResponseEntity<FoodItemResponse> response = foodItemController.partialUpdateFoodItemById(1, foodItem);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(expectedResponse, response.getBody());
            verify(foodItemServices, times(1)).partialUpdateFoodItemById(1, foodItem);
        }
    }

    @Nested
    class DeleteFoodItemById {

        @Test
        void shouldDeleteFoodItemSuccessfully() {
            doNothing().when(foodItemServices).deleteFoodItemById(1);

            ResponseEntity<FoodItemResponse> response = foodItemController.deleteFoodItemById(1);

            assertEquals(200, response.getStatusCodeValue());
            verify(foodItemServices, times(1)).deleteFoodItemById(1);
        }
    }

    @Nested
    class GetImageData {

        @Test
        void shouldReturnImageDataSuccessfully() {
            FoodItemImage image = new FoodItemImage();
            FoodItemImageResponse expectedResponse = new FoodItemImageResponse(image);

            when(foodItemServices.getImageData(1)).thenReturn(image);

            ResponseEntity<FoodItemImageResponse> response = foodItemController.getImageData(1);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(expectedResponse, response.getBody());
            verify(foodItemServices, times(1)).getImageData(1);
        }
    }

    @Nested
    class UpdateImageById {

        @Test
        void shouldUpdateImageByIdSuccessfully() {
            FoodItemImage image = new FoodItemImage();
            FoodItemImageResponse expectedResponse = new FoodItemImageResponse(image);

            when(foodItemServices.updateImageById(1, image)).thenReturn(image);

            ResponseEntity<FoodItemImageResponse> response = foodItemController.updateImageById(1, image);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(expectedResponse, response.getBody());
            verify(foodItemServices, times(1)).updateImageById(1, image);
        }
    }
}