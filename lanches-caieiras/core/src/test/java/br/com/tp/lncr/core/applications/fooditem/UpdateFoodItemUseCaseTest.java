package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.enums.FoodItemCategory;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.domain.fooditem.FoodItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UpdateFoodItemUseCaseTest {
    private FoodItemGateway gateway;
    private UpdateFoodItemUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(FoodItemGateway.class);
        useCase = new UpdateFoodItemUseCase(gateway);
    }

    @Test
    void testUpdateFoodItem() {
        // Criar FoodItem existente para ser retornado pelo gateway
        FoodItem existingItem = new FoodItem();
        existingItem.setId(1);
        existingItem.setName("X-BURGUER");
        existingItem.setDescription("DESCRIÇÃO ORIGINAL");
        existingItem.setPrice(20.99);
        existingItem.setCategory(FoodItemCategory.SANDWICH);

        // Mock para encontrar o item existente
        when(gateway.getFoodItemById(1)).thenReturn(existingItem);

        // Criar FoodItem atualizado para ser retornado após save
        FoodItem updatedItem = new FoodItem();
        updatedItem.setId(1);
        updatedItem.setName("X-SALADA");
        updatedItem.setDescription("DESCRIÇÃO ATUALIZADA");
        updatedItem.setPrice(25.99);
        updatedItem.setCategory(FoodItemCategory.SANDWICH);

        List<FoodItemImageDTO> list = new ArrayList<>();
        list.add(new FoodItemImageDTO(null, 1, "iVBORw0KGgoAAAANSUhEUgAAAEAAAAAqCAYAAAADBl3iAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAACxEAAAsRAX9kX5EAAAGHaVRYdFhNTDpjb20uYWRvYmUueG1wAAAAAAA8P3hwYWNrZXQgYmVnaW49J++7vycgaWQ9J1c1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCc/Pg0KPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLy",
                null, null, null, null));
        FoodItemDTO foodItemDTO = new FoodItemDTO(null, "X-SALADA", "DESCRIÇÃO ATUALIZADA", 25.99, FoodItemCategory.SANDWICH.getDescription(), list);

        when(gateway.saveFoodItem(any(FoodItem.class))).thenReturn(updatedItem);

        FoodItem result = useCase.partialUpdateById(1, foodItemDTO);
        assertNotNull(result);
        verify(gateway, times(1)).getFoodItemById(1);
        verify(gateway, times(1)).saveFoodItem(any(FoodItem.class));
    }
}
