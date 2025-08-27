package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class GetFoodItemImageUseCaseTest {
    private FoodItemGateway gateway;
    private GetFoodItemImageUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(FoodItemGateway.class);
        useCase = new GetFoodItemImageUseCase(gateway);
    }

    @Test
    void testGetFoodItemImage() {
        FoodItemImage image = mock(FoodItemImage.class);
        when(image.getFileName()).thenReturn("test-image.png");
        when(image.get_data()).thenReturn("base64ImageData"); // Adiciona mock para _data
        when(gateway.getFoodItemImageById(anyInt())).thenReturn(image);

        FoodItemImage result = useCase.getById(1);

        assertNotNull(result);
        verify(gateway, times(1)).getFoodItemImageById(anyInt());
    }
}
