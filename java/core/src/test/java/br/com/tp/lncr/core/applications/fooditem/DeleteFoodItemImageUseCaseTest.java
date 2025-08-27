package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeleteFoodItemImageUseCaseTest {
    private FoodItemGateway gateway;
    private DeleteFoodItemImageUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(FoodItemGateway.class);
        useCase = new DeleteFoodItemImageUseCase(gateway);
    }

    @Test
    void testDeleteFoodItemImage() {
        // Criar FoodItemImage existente com ID válido
        FoodItemImage existingImage = mock(FoodItemImage.class);
        when(existingImage.getId()).thenReturn(1);
        when(existingImage.getFoodItemId()).thenReturn(1);
        when(existingImage.getFileName()).thenReturn("test-image.png");

        // Mock para encontrar a imagem antes de deletar
        when(gateway.getFoodItemImageById(1)).thenReturn(existingImage);

        // Mock para a operação de deleção
        doNothing().when(gateway).deleteFoodItemImage(any(FoodItemImage.class));

        useCase.deleteById(1);

        // Verificar se os métodos foram chamados
        verify(gateway, times(1)).getFoodItemImageById(1);
        verify(gateway, times(1)).deleteFoodItemImage(any(FoodItemImage.class));
    }
}
