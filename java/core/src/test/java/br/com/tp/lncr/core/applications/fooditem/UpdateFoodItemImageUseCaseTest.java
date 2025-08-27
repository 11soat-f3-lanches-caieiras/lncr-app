package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UpdateFoodItemImageUseCaseTest {
    private FoodItemGateway gateway;
    private UpdateFoodItemImageUseCase useCase;
    private FoodItemImageRules imageRules;

    @BeforeEach
    void setUp() {
        gateway = mock(FoodItemGateway.class);
        imageRules = mock(FoodItemImageRules.class);
        useCase = new UpdateFoodItemImageUseCase(gateway);

        // Mock das regras de imagem com extensões válidas
        Map<String, String> allowedExtensions = new HashMap<>();
        allowedExtensions.put(".png", "89504E47");
        allowedExtensions.put(".jpg", "FFD8");
        when(imageRules.getAllowedExtentions()).thenReturn(allowedExtensions);
        when(imageRules.getImageMaxSize()).thenReturn(5 * 1024 * 1024); // 5MB
        when(imageRules.getImageLocation()).thenReturn("/images");
    }

    @Test
    void testUpdateFoodItemImage() {
        // Usar um base64 válido de PNG que corresponde ao header 89504E47
        String validPngBase64 = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAIAAACQd1PeAAAADElEQVR4nGP4//8/AAX+Av7czFnnAAAAAElFTkSuQmCC";

        FoodItemImageDTO imageDTO = new FoodItemImageDTO(11, 1, validPngBase64,
                null, "updated-image.png", null, null);

        FoodItemImage existingImage = mock(FoodItemImage.class);
        when(existingImage.getFileName()).thenReturn("old-image.png");
        when(existingImage.getId()).thenReturn(11);
        when(existingImage.getFoodItemId()).thenReturn(1);

        FoodItemImage updatedImage = mock(FoodItemImage.class);
        when(updatedImage.getFileName()).thenReturn("updated-image.png");
        when(updatedImage.getId()).thenReturn(11);

        // Mock para retornar a imagem existente
        when(gateway.getFoodItemImageById(11)).thenReturn(existingImage);
        when(gateway.createFoodItem(any(FoodItemImage.class))).thenReturn(updatedImage);

        FoodItemImage result = useCase.updateImageById(11, imageDTO, imageRules);

        assertNotNull(result);
        verify(gateway, times(1)).getFoodItemImageById(11);
        verify(gateway, times(1)).createFoodItem(any(FoodItemImage.class));
    }
}
