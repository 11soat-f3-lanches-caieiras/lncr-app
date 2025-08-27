package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.enums.FoodItemCategory;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.domain.fooditem.FoodItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class GetFoodItemUseCaseTest {

    private final String BASE64_PNG = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAIAAACQd1PeAAAADElEQVR4nGP4";
    private final FoodItemImageDTO IMAGE_DTO = new FoodItemImageDTO(null,1, BASE64_PNG, null, null, null, null);
    private final List<FoodItemImageDTO> IMAGES = new ArrayList<>(Collections.singletonList(IMAGE_DTO));
    private final FoodItemDTO ITEM_DTO = new FoodItemDTO(1, "X-SALADA", "DESCRIÇÃO", 25.99, FoodItemCategory.SANDWICH.getDescription(), IMAGES);

    private FoodItemGateway gateway;
    private GetFoodItemUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(FoodItemGateway.class);
        useCase = new GetFoodItemUseCase(gateway);
    }

    @Test
    void testGetFoodItem() {
        FoodItem item = new FoodItem(ITEM_DTO);
        when(gateway.getFoodItemById(anyInt(),anyBoolean())).thenReturn(item);
        FoodItem result = useCase.getById(1,true);
        assertNotNull(result);
        verify(gateway, times(1)).getFoodItemById(anyInt(),anyBoolean());
    }

    @Test
    void testGetAllFoodItems() {
        when(gateway.getAllFoodItems(10,"SANDWICH",false)).thenReturn(Collections.singletonList(new FoodItem()));
        assertFalse(useCase.getAll(10,"SANDWICH",false).isEmpty());
        verify(gateway, times(1)).getAllFoodItems(10,"SANDWICH",false);
    }
}

