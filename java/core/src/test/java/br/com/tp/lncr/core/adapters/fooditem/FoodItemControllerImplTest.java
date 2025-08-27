package br.com.tp.lncr.core.adapters.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.enums.FoodItemCategory;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FoodItemControllerImplTest {
    private final String BASE64_PNG = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAIAAACQd1PeAAAADElEQVR4nGP4";
    private final FoodItemImageDTO IMAGE_DTO = new FoodItemImageDTO(null,1, BASE64_PNG, null, null, null, null);
    private final List<FoodItemImageDTO> IMAGES = new ArrayList<>(Collections.singletonList(IMAGE_DTO));
    private final FoodItemDTO ITEM_DTO = new FoodItemDTO(1, "X-SALADA", "DESCRIÇÃO", 25.99, FoodItemCategory.SANDWICH.getDescription(), IMAGES);
    private FoodItemControllerImpl controller;
    private FoodItemDatabase foodItemDatabase;
    private FoodItemImageRules foodItemImageRules;

    @BeforeEach
    void setUp() {
        foodItemDatabase = Mockito.mock(FoodItemDatabase.class);
        foodItemImageRules = Mockito.mock(FoodItemImageRules.class);
        controller = new FoodItemControllerImpl(foodItemDatabase);
        Map<String, String> allowedExtensions = new HashMap<>();
        allowedExtensions.put(".jpg", "FFD8");
        allowedExtensions.put(".png", "89504E47");
        Mockito.when(foodItemImageRules.getImageLocation()).thenReturn("/img");
        Mockito.when(foodItemImageRules.getImageMaxSize()).thenReturn(5 * 1024 * 1024); // 5 MB
        Mockito.when(foodItemImageRules.getMaxNumberOfImages()).thenReturn(5);
        Mockito.when(foodItemImageRules.getAllowedExtentions()).thenReturn(allowedExtensions);
        // Adiciona um FoodItemDTO válido com id 1 para os testes, com apenas 4 imagens usando BASE64_PNG
        List<FoodItemImageDTO> images = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            images.add(IMAGE_DTO);
        }
        FoodItemDTO foodItem = new FoodItemDTO(1, "X-SALADA", "DESCRIÇÃO", 25.99, FoodItemCategory.SANDWICH.getDescription(), images);
        Mockito.when(foodItemDatabase.findFoodItemById(1, false)).thenReturn(ITEM_DTO);
        Mockito.when(foodItemDatabase.findAllFoodItems(Mockito.anyInt(), Mockito.any(), Mockito.anyBoolean())).thenReturn(Collections.singletonList(foodItem));
        Mockito.when(foodItemDatabase.findFoodItemByIdList(Mockito.anyList())).thenReturn(Collections.singletonList(foodItem));
        Mockito.when(foodItemDatabase.create(Mockito.any(FoodItemDTO.class))).thenReturn(foodItem);
        Mockito.when(foodItemDatabase.save(Mockito.any(FoodItemDTO.class))).thenReturn(foodItem);
        Mockito.when(foodItemDatabase.save(Mockito.any(FoodItemImageDTO.class))).thenReturn(images.get(0));
        Mockito.when(foodItemDatabase.findFoodItemImageById(1)).thenReturn(images.get(0));
        Mockito.when(foodItemDatabase.findFoodItemImageById(1)).thenReturn(images.get(0));
        Mockito.when(foodItemDatabase.findAllFoodItemImagesByFoodItemId(1, false)).thenReturn(images);
        Mockito.doNothing().when(foodItemDatabase).delete(foodItem);
        Mockito.doNothing().when(foodItemDatabase).deleteImagesByFoodItemId(1);
    }

    @Test
    void testCreate() {
        FoodItemDTO dto = ITEM_DTO;
        FoodItemDTO result = controller.create(dto, foodItemDatabase, foodItemImageRules);
        assertNotNull(result);
    }

    @Test
    void testGetAll() {
        assertNotNull(controller.getAll(10, null, false, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testGetById() {
        assertNotNull(controller.getById(1, false, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testGetByIdList() {
        assertNotNull(controller.getByIdList(Collections.singletonList(1), foodItemDatabase));
    }

    @Test
    void testPartialUpdateById() {

        List<FoodItemImageDTO> list = new ArrayList<>();
        list.add(IMAGE_DTO);
        FoodItemDTO foodItemDTO = new FoodItemDTO(null, "X-SALADA", "DESCRIÇAO", 25.99, FoodItemCategory.SANDWICH.getDescription(),list);
        assertDoesNotThrow(() -> controller.partialUpdateById(1, foodItemDTO, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testDeleteById() {
        // Mock para encontrar o item antes de deletar
        Mockito.when(foodItemDatabase.findFoodItemById(1, true)).thenReturn(ITEM_DTO);
        // Mock para não fazer nada quando deletar
        Mockito.doNothing().when(foodItemDatabase).delete(Mockito.any(FoodItemDTO.class));
        // Mock adicional para o gateway interno do controller
        Mockito.doNothing().when(foodItemDatabase).deleteImagesByFoodItemId(1);

        assertDoesNotThrow(() -> controller.deleteById(1, foodItemDatabase));

        // Verificar se os métodos foram chamados
        Mockito.verify(foodItemDatabase).findFoodItemById(1, true);
        Mockito.verify(foodItemDatabase).delete(Mockito.any(FoodItemDTO.class));
    }

    @Test
    void testCreateImage() {
        FoodItemImageDTO dto = IMAGE_DTO;
        assertNotNull(controller.create(1, dto, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testGetFoodItemImagesByFoodItemId() {
        assertNotNull(controller.getFoodItemImagesByFoodItemId(1, false, foodItemDatabase, foodItemImageRules));
    }

    @Test
    void testDeleteImagesByFoodItemId() {
        assertDoesNotThrow(() -> controller.deleteImagesByFoodItemId(1, foodItemDatabase));
    }
}
