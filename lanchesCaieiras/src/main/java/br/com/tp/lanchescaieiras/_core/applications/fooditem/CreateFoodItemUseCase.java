package br.com.tp.lanchescaieiras._core.applications.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItem;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImageRules;

import java.util.ArrayList;
import java.util.List;

public class CreateFoodItemUseCase {

    private final FoodItemGateway foodItemGateway;
    private final FoodItemImageRules foodItemImageRules;

    public CreateFoodItemUseCase(FoodItemGateway foodItemGateway, FoodItemImageRules foodItemImageRules) {
        this.foodItemGateway = foodItemGateway;
        this.foodItemImageRules = foodItemImageRules;
    }

    public FoodItem execute(FoodItemDTO foodItemDTO) {
        if (existsByName(foodItemDTO.getName())) {
            throw new FoodItemException("Item de Alimentação já cadastrado com o nome: " + foodItemDTO.getName(), 409);
        }

        if (foodItemDTO.getImages().size() > foodItemImageRules.getMaxNumberOfImages()) {
            throw new FoodItemException("Número máximo de imagens excedido. Máximo permitido: " + foodItemImageRules.getMaxNumberOfImages(), 400);
        }

        FoodItem foodItem = new FoodItem(foodItemDTO);
        List<FoodItemImage> invalidFoodItemImages = new ArrayList<>();

        splitValidAndInvalidIFoodItemList(foodItemDTO, foodItem, invalidFoodItemImages);

        foodItem = foodItemGateway.save(foodItem);

        if (!invalidFoodItemImages.isEmpty()) {
            foodItem.getImages().addAll(invalidFoodItemImages);
        }
        return foodItem;
    }

    private boolean existsByName(String name) {
        return foodItemGateway.existsByName(name);
    }

    private void splitValidAndInvalidIFoodItemList(FoodItemDTO foodItemDTO, FoodItem foodItem, List<FoodItemImage> invalidFoodItemImages) {
        for (FoodItemImageDTO imageDTO : foodItemDTO.getImages()) {
            try {
                foodItem.getImages().add(new FoodItemImage(imageDTO, foodItemImageRules));
            } catch (Exception e) {
                FoodItemImage invalidImage = new FoodItemImage();
                invalidImage.setImageError(e.getMessage());
                invalidFoodItemImages.add(invalidImage);
            }
        }
    }
}
