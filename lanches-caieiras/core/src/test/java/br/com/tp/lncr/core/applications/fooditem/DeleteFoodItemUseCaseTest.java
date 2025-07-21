package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.enums.FoodItemCategory;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.domain.fooditem.FoodItem;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class DeleteFoodItemUseCaseTest {
    private final String BASE64_PNG = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAIAAACQd1PeAAAADElEQVR4nGP4";
    private final FoodItemImageDTO IMAGE_DTO = new FoodItemImageDTO(11,1, BASE64_PNG, null, null, null, null);

    private FoodItemGateway gateway;
    private DeleteFoodItemUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(FoodItemGateway.class);
        useCase = new DeleteFoodItemUseCase(gateway);
    }

    @Test
    void testDeleteFoodItem() {
        FoodItemImage image = new FoodItemImage(IMAGE_DTO);
        // Criar FoodItem existente para ser encontrado
        FoodItem existingItem = new FoodItem();
        existingItem.setId(1);
        existingItem.setName("X-BURGUER");
        existingItem.setCategory(FoodItemCategory.SANDWICH);
        existingItem.setDescription("Delicious burger");
        existingItem.setPrice(25.99);
        existingItem.setImages(List.of(image));

        when(gateway.getFoodItemById(1,true)).thenReturn(existingItem);

        // Mock para as operações de deleção - usar métodos que existem na interface
        doNothing().when(gateway).deleteImagesByFoodItemId(1);
        doNothing().when(gateway).deleteFoodItemImage(existingItem);

        useCase.execute(1);

        // Verificar se os métodos foram chamados com os argumentos corretos
        verify(gateway, times(1)).getFoodItemById(1, true);
        verify(gateway, times(1)).deleteFoodItemImage(existingItem);
    }
}
