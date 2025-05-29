package br.com.tp.lanchescaieiras.fooditem.application.services;

import br.com.tp.lanchescaieiras.customer.infraestructure.exceptions.CustomerException;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories.JpaFoodItemImageRepositoryImpl;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories.JpaFoodItemRepositoryImpl;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoodItemServicesImplTest {

    private JpaFoodItemRepositoryImpl foodItemRepo;
    private JpaFoodItemImageRepositoryImpl imageRepo;
    private FoodItemServicesImpl service;

    @BeforeEach
    void setup() {
        foodItemRepo = mock(JpaFoodItemRepositoryImpl.class);
        imageRepo = mock(JpaFoodItemImageRepositoryImpl.class);
        service = new FoodItemServicesImpl(foodItemRepo, imageRepo);
    }

    @Test
    void testCreateFoodItem() {
        FoodItem item = new FoodItem();
        FoodItem itemWithImages = new FoodItem();
        when(foodItemRepo.save(item)).thenReturn(item);
        when(imageRepo.saveImages(item)).thenReturn(itemWithImages);

        FoodItem result = service.createFoodItem(item);

        assertEquals(itemWithImages, result);
        verify(foodItemRepo, times(1)).save(item);
        verify(imageRepo, times(1)).saveImages(item);
    }

    @Test
    void testGetAllFoodItems() {
        FoodItem item = new FoodItem();
        when(foodItemRepo.findAllByCategory(5, "snack")).thenReturn(List.of(item));
        when(imageRepo.findAllImagesByFoodItemId(item)).thenReturn(item);

        List<FoodItem> result = service.getAllFoodItems(5, "snack");

        assertEquals(1, result.size());
        verify(foodItemRepo, times(1)).findAllByCategory(5, "snack");
        verify(imageRepo, times(1)).findAllImagesByFoodItemId(item);
    }

    @Test
    void testGetFoodItemFound() {
        FoodItem item = new FoodItem();
        FoodItem itemWithImages = new FoodItem();
        when(foodItemRepo.findById(1)).thenReturn(Optional.of(item));
        when(imageRepo.findAllImagesByFoodItemId(item)).thenReturn(itemWithImages);

        Optional<FoodItem> result = service.getFoodItem(1);

        assertTrue(result.isPresent());
        assertEquals(itemWithImages, result.get());
    }

    @Test
    void testGetFoodItemNotFoundThrows() {
        when(foodItemRepo.findById(2)).thenReturn(Optional.empty());
        assertThrows(FoodItemException.class, () -> service.getFoodItem(2));
    }

    @Test
    void testPartialUpdateFoodItemByIdFound() {
        FoodItem item = new FoodItem();
        when(foodItemRepo.partialUpdateFoodItemById(1, item)).thenReturn(Optional.of(item));

        FoodItem result = service.partialUpdateFoodItemById(1, item);

        assertEquals(item, result);
    }

    @Test
    void testPartialUpdateFoodItemByIdNotFoundThrows() {
        when(foodItemRepo.partialUpdateFoodItemById(2, null)).thenReturn(Optional.empty());
        assertThrows(CustomerException.class, () -> service.partialUpdateFoodItemById(2, null));
    }

    @Test
    void testDeleteFoodItemByIdFound() {
        FoodItem item = new FoodItem();
        when(foodItemRepo.findById(1)).thenReturn(Optional.of(item));
        doNothing().when(imageRepo).deleteFoodItemImagesByFoodItemId(1);
        doNothing().when(foodItemRepo).deleteFoodItemById(1);

        assertDoesNotThrow(() -> service.deleteFoodItemById(1));
        verify(imageRepo, times(1)).deleteFoodItemImagesByFoodItemId(1);
        verify(foodItemRepo, times(1)).deleteFoodItemById(1);
    }

    @Test
    void testDeleteFoodItemByIdNotFoundThrows() {
        when(foodItemRepo.findById(2)).thenReturn(Optional.empty());
        assertThrows(FoodItemException.class, () -> service.deleteFoodItemById(2));
    }

    @Test
    void testGetImageData() {
        FoodItemImage img = new FoodItemImage();
        when(imageRepo.findImageById(1)).thenReturn(img);

        FoodItemImage result = service.getImageData(1);

        assertEquals(img, result);
    }

    @Test
    void testUpdateImageById() {
        FoodItemImage img = new FoodItemImage();
        when(imageRepo.updateImageById(1, img)).thenReturn(img);

        FoodItemImage result = service.updateImageById(1, img);

        assertEquals(img, result);
    }
}
