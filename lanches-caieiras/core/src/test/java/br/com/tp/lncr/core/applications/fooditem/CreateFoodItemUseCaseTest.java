package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.enums.FoodItemCategory;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;
import br.com.tp.lncr.core.domain.fooditem.FoodItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateFoodItemUseCaseTest {
    private FoodItemGateway gateway;
    private CreateFoodItemUseCase useCase;
    private FoodItemImageRules imageRules;

    @BeforeEach
    void setUp() {
        gateway = mock(FoodItemGateway.class);
        imageRules = mock(FoodItemImageRules.class);
        useCase = new CreateFoodItemUseCase(gateway, imageRules);
    }

    @Test
    void testCreateFoodItem() {
        // Mock das regras de imagem
        when(imageRules.getMaxNumberOfImages()).thenReturn(5);

        FoodItem item = new FoodItem();
        item.setId(1);
        item.setName("X-SALADA");
        item.setDescription("DESCRIÇÃO");
        item.setPrice(25.99);
        item.setCategory(FoodItemCategory.SANDWICH);

        List<FoodItemImageDTO> list = new ArrayList<>();
        list.add(new FoodItemImageDTO(null, 1, "iVBORw0KGgoAAAANSUhEUgAAAEAAAAAqCAYAAAADBl3iAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAACxEAAAsRAX9kX5EAAAGHaVRYdFhNTDpjb20uYWRvYmUueG1wAAAAAAA8P3hwYWNrZXQgYmVnaW49J++7vycgaWQ9J1c1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCc/Pg0KPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLy",
                null, null, null, null));
        FoodItemDTO foodItemDTO = new FoodItemDTO(null, "X-SALADA", "DESCRIÇÃO", 25.99, FoodItemCategory.SANDWICH.getDescription(), list);

        when(gateway.saveFoodItem(any(FoodItem.class))).thenReturn(item);

        FoodItem result = useCase.execute(foodItemDTO);
        assertNotNull(result);
        verify(gateway, times(1)).saveFoodItem(any(FoodItem.class));
        verify(imageRules, times(1)).getMaxNumberOfImages();
    }
}
