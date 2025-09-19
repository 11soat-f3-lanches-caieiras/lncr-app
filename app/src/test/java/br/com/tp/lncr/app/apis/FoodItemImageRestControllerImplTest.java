package br.com.tp.lncr.app.apis;

import br.com.tp.lncr.app.apis.fooditem.FoodItemImageRestControllerImpl;
import br.com.tp.lncr.app.commons.model.ResponseModel;
import br.com.tp.lncr.app.configs.FoodItemConfig;
import br.com.tp.lncr.app.dataproxy.FoodItemDataProxy;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemImageController;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FoodItemImageRestControllerImplTest {

    private final String BASE64_PNG = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAIAAACQd1PeAAAADElEQVR4nGP4";

    @Mock
    private FoodItemDataProxy foodItemDataProxy;

    @Mock
    private FoodItemImageController foodItemImageController;

    @Mock
    private FoodItemConfig foodItemConfig;

    @Mock
    private FoodItemConfig.ImageConfig imageConfig;

    @InjectMocks
    private FoodItemImageRestControllerImpl foodItemImageRestController;

    private FoodItemImageDTO foodItemImageDTO;

    @BeforeEach
    void setUp() {
        foodItemImageDTO = new FoodItemImageDTO();
        foodItemImageDTO.setId(1);
        foodItemImageDTO.setFoodItemId(1);
        foodItemImageDTO.setFileName("burger.jpg");
        Map<String, String> allowedExtensions = new HashMap<>();
        allowedExtensions.put(".jpg", "FFD8");
        allowedExtensions.put(".png", "89504E47");

        when(foodItemConfig.getImage()).thenReturn(imageConfig);
        when(imageConfig.getLocationPrefix()).thenReturn("/images");
        when(foodItemConfig.getMaxImages()).thenReturn(5);
        when(imageConfig.getMaxSize()).thenReturn(1024*512*1024); // 512 MB
        when(imageConfig.getLocationPrefix()).thenReturn("/images");
        when(imageConfig.getAllowedExtensions()).thenReturn(allowedExtensions);
    }

    @Test
    void deveRetornarFoodItemImagePorId() {
        when(foodItemImageController.getImageById(eq(1), eq(foodItemDataProxy), eq("/images"))).thenReturn(foodItemImageDTO);

        ResponseEntity<ResponseModel<FoodItemImageDTO>> response = foodItemImageRestController.getFoodItemImageById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(foodItemImageDTO, response.getBody().get_content());
        verify(foodItemImageController).getImageById(eq(1), eq(foodItemDataProxy), eq("/images"));
    }

    @Test
    void deveAtualizarFoodItemImagePorId() {
        FoodItemImageDTO updatedImage = new FoodItemImageDTO();
        updatedImage.setId(1);
        updatedImage.setFileName("updated_burger.jpg");
        when(foodItemImageController.updateImageById(eq(1), eq(foodItemImageDTO), eq(foodItemDataProxy), any(FoodItemImageRules.class))).thenReturn(updatedImage);

        ResponseEntity<ResponseModel<FoodItemImageDTO>> response = foodItemImageRestController.updateFoodItemImageById(1, foodItemImageDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedImage, response.getBody().get_content());
        verify(foodItemImageController).updateImageById(eq(1), eq(foodItemImageDTO), eq(foodItemDataProxy), any(FoodItemImageRules.class));
    }

    @Test
    void deveDeletarFoodItemImagePorId() {
        doNothing().when(foodItemImageController).deleteImageById(1, foodItemDataProxy);

        ResponseEntity<ResponseModel<FoodItemImageDTO>> response = foodItemImageRestController.deleteFoodItemImageById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().get_content());
        verify(foodItemImageController).deleteImageById(1, foodItemDataProxy);
    }

    @Test
    void deveRetornarImagemComDadosCompletos() {
        FoodItemImageDTO imageWithData = new FoodItemImageDTO();
        imageWithData.setId(1);
        imageWithData.setFileName("burger.jpg");
        imageWithData.set_data(BASE64_PNG);
        when(foodItemImageController.getImageById(eq(1), eq(foodItemDataProxy), eq("/images"))).thenReturn(imageWithData);

        ResponseEntity<ResponseModel<FoodItemImageDTO>> response = foodItemImageRestController.getFoodItemImageById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(imageWithData, response.getBody().get_content());
        assertEquals(BASE64_PNG, response.getBody().get_content().get_data());
    }

    @Test
    void deveAtualizarImagemComNovosDados() {
        FoodItemImageDTO newImageData = new FoodItemImageDTO();
        newImageData.setFileName("new_image.png");
        newImageData.set_data(BASE64_PNG);

        FoodItemImageDTO updatedImage = new FoodItemImageDTO();
        updatedImage.setId(1);
        updatedImage.setFileName("new_image.png");
        updatedImage.set_data(BASE64_PNG);

        when(foodItemImageController.updateImageById(eq(1), eq(newImageData), eq(foodItemDataProxy), any(FoodItemImageRules.class))).thenReturn(updatedImage);

        ResponseEntity<ResponseModel<FoodItemImageDTO>> response = foodItemImageRestController.updateFoodItemImageById(1, newImageData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedImage, response.getBody().get_content());
        assertEquals("new_image.png", response.getBody().get_content().getFileName());
        verify(foodItemImageController).updateImageById(eq(1), eq(newImageData), eq(foodItemDataProxy), any(FoodItemImageRules.class));
    }
}
