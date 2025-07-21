package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateFoodItemImageUseCaseTest {
    private FoodItemGateway gateway;
    private FoodItemImageRules foodItemImageRules;
    private CreateFoodItemImageUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(FoodItemGateway.class);
        foodItemImageRules = mock(FoodItemImageRules.class);
        useCase = new CreateFoodItemImageUseCase(gateway, foodItemImageRules);
    }

    @Test
    void testCreateFoodItemImage() {
        Integer foodItemId = 1;
        FoodItemImageDTO foodItemDTOImage = new FoodItemImageDTO(null,1,"iVBORw0KGgoAAAANSUhEUgAAAEAAAAAqCAYAAAADBl3iAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAACxEAAAsRAX9kX5EAAAGHaVRYdFhNTDpjb20uYWRvYmUueG1wAAAAAAA8P3hwYWNrZXQgYmVnaW49J++7vycgaWQ9J1c1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCc/Pg0KPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLy",
                null,null,null,null);

        // Mock das dependências necessárias
        List<FoodItemImage> existingImages = new ArrayList<>(); // Lista vazia - sem imagens existentes
        when(gateway.getAllImagesByFoodItemId(foodItemId, false)).thenReturn(existingImages);
        when(foodItemImageRules.getMaxNumberOfImages()).thenReturn(5); // Permite até 5 imagens

        // Mock das extensões permitidas - PNG header
        Map<String, String> allowedExtensions = new HashMap<>();
        allowedExtensions.put("png", "89504E47"); // Header para PNG
        allowedExtensions.put("jpg", "FFD8FF"); // Header para JPG
        when(foodItemImageRules.getAllowedExtentions()).thenReturn(allowedExtensions);

        FoodItemImage image = new FoodItemImage();
        when(gateway.createFoodItem(any(FoodItemImage.class))).thenReturn(image);

        FoodItemImage result = useCase.execute(foodItemId,foodItemDTOImage);

        assertNotNull(result);
        verify(gateway, times(1)).createFoodItem(any(FoodItemImage.class));
        verify(gateway, times(1)).getAllImagesByFoodItemId(foodItemId, false);
        verify(foodItemImageRules, times(1)).getMaxNumberOfImages();
        verify(foodItemImageRules, atLeast(1)).getAllowedExtentions(); // Permite múltiplas chamadas
    }
}
