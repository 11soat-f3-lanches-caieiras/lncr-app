package br.com.tp.lanchescaieiras.fooditem.adapters;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.commons.enums.FoodItemCategory;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemDatabase;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemGateway;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;

import java.util.List;

public class FoodItemGatewayImpl implements FoodItemGateway {

    private final FoodItemDatabase foodItemDatabase;
    private final FoodItemMapper foodItemMapper;

    public FoodItemGatewayImpl(FoodItemDatabase foodItemDatabase, FoodItemMapper foodItemMapper) {
        this.foodItemDatabase = foodItemDatabase;
        this.foodItemMapper = foodItemMapper;
    }

    @Override
    public FoodItem create(FoodItem foodItem) {
        FoodItemDTO foodItemDTO = this.foodItemMapper.domainToDto(foodItem);
        foodItemDTO = this.foodItemDatabase.create(foodItemDTO);
        return this.foodItemMapper.dtoToDomain(foodItemDTO);
    }

    @Override
    public boolean existsByName(String foodItemName) {
        return foodItemDatabase.existsByName(foodItemName);
    }

    @Override
    public void saveImages(List<FoodItemImage> foodItemImages) {
        List<FoodItemImageDTO> foodItemImageDTOList = this.foodItemMapper.imageDomainListToDtoList(foodItemImages);
        this.foodItemDatabase.saveImages(foodItemImageDTOList);
    }

    @Override
    public void saveImagesFiles(List<FoodItemImage> foodItemImage){
        this.foodItemDatabase.saveImageFiles(this.foodItemMapper.imageDomainListToDtoList(foodItemImage));
    }

    @Override
    public List<FoodItem> getAllFoodItems(Integer _limit, String category, Boolean includeImages,
                                          FoodItemMapper foodItemMapper) {
        Integer categoryId = category == null ? null : FoodItemCategory.fromDescription(category).getId();

        return this.foodItemDatabase.getAllFoodItems(_limit, categoryId, includeImages)
                .stream()
                .map(foodItemMapper::dtoToDomain)
                .toList();
    }
}
