package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.exceptions.FoodItemException;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.fooditem.FoodItem;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;

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
        Logger.info("Iniciando criação de item de alimentação: " + foodItemDTO.getName());
        if (existsByName(foodItemDTO.getName())) {
            throw new FoodItemException("Item de Alimentação já cadastrado com o nome: " + foodItemDTO.getName(), 409);
        }

        if (foodItemDTO.getImages().size() > foodItemImageRules.getMaxNumberOfImages()) {
            throw new FoodItemException("Número máximo de imagens excedido. Máximo permitido: " + foodItemImageRules.getMaxNumberOfImages(), 400);
        }

        FoodItem foodItem = new FoodItem(foodItemDTO);
        List<FoodItemImage> invalidFoodItemImages = new ArrayList<>();
        splitValidAndInvalidIFoodItemList(foodItemDTO, foodItem, invalidFoodItemImages);
        foodItem = foodItemGateway.createFoodItemImage(foodItem);

        if (!invalidFoodItemImages.isEmpty()) {
            foodItem.getImages().addAll(invalidFoodItemImages);
        }
        Logger.info("Item de alimentação criado com sucesso: " + foodItem.getName());
        return foodItem;
    }

    private boolean existsByName(String name) {
        return foodItemGateway.existsByName(name);
    }

    private void splitValidAndInvalidIFoodItemList(FoodItemDTO foodItemDTO, FoodItem foodItem, List<FoodItemImage> invalidFoodItemImages) {
        Logger.debug("Separando imagens válidas e inválidas para o item de alimentação: " + foodItemDTO.getName());
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
