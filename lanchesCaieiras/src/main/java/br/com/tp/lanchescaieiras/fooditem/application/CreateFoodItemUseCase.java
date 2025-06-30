package br.com.tp.lanchescaieiras.fooditem.application;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemDatabase;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemGateway;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemPresenter;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;

import java.util.List;

public class CreateFoodItemUseCase {

    public CreateFoodItemUseCase() {
    }

    public FoodItemDTO execute(FoodItemDTO foodItemDTO, FoodItemGateway foodItemGateway, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper) {
        // Validar se existe um item de alimentação com o mesmo nome
        if (existsByName(foodItemDTO.getName(), foodItemGateway)) {
            throw new FoodItemException("Item de Alimentação já cadastrado com o nome: " + foodItemDTO.getName(), 409);
        }

        if (foodItemDTO.getImages().size() > foodItemConfig.getMaxImages()) {
            throw new FoodItemException("Número máximo de imagens excedido. Máximo permitido: " + foodItemConfig.getMaxImages(), 400);
        }

        FoodItem foodItem = foodItemMapper.dtoToDomain(foodItemDTO);
        foodItem.validadeFoodItemImages(foodItemConfig);

        List<FoodItemImage> invalidFoodItemImages = invalidFoodItemImages(foodItem.getImages());

        foodItem.getImages().removeIf(image -> image.getImageError() != null);
        foodItem = foodItemGateway.create(foodItem);

        if(invalidFoodItemImages!= null && !invalidFoodItemImages.isEmpty()) {
         foodItem.getImages().addAll(invalidFoodItemImages);
        }


        return foodItemMapper.domainToDto(foodItem);
    }

    private boolean existsByName(String name, FoodItemGateway foodItemGateway) {
        return foodItemGateway.existsByName(name);
    }

    private List<FoodItemImage> invalidFoodItemImages(List<FoodItemImage> foodItemImages) {
        return foodItemImages.stream()
                .filter(image -> image.getImageError() != null)
                .toList();
    }
}
