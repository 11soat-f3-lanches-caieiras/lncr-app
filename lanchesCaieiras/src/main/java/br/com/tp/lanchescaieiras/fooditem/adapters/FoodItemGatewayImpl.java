package br.com.tp.lanchescaieiras.fooditem.adapters;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.commons.enums.FoodItemCategory;
import br.com.tp.lanchescaieiras.commons.interfaces.foodItem.FoodItemDatabase;
import br.com.tp.lanchescaieiras.commons.interfaces.foodItem.FoodItemGateway;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;

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

    @Override
    public FoodItem getFoodItemById(Integer foodItemId, Boolean includeImages) {
        return this.foodItemMapper.dtoToDomain(this.foodItemDatabase.getFoodItemById(foodItemId,includeImages));
    }

    @Override
    public FoodItem getFoodItemById(Integer foodItemId) {
        return getFoodItemById(foodItemId,false);
    }

    @Override
    public FoodItem saveFoodItem(FoodItem foodItem) {
        FoodItemDTO foodItemDTO = this.foodItemMapper.domainToDto(foodItem);
        foodItemDTO = this.foodItemDatabase.save(foodItemDTO);
        return this.foodItemMapper.dtoToDomain(foodItemDTO);
    }

    @Override
    public void delete(FoodItem foodItem) {
        this.foodItemDatabase.delete(this.foodItemMapper.domainToDto(foodItem));
    }

    @Override
    public List<FoodItemImage> findAllFoodItemImagesByFoodItemId(Integer foodItemId, Boolean includeData) {

        List<FoodItemImageDTO> foodItemImageDTOList = this.foodItemDatabase.findAllFoodItemImagesByFoodItemId(foodItemId,includeData);
        return foodItemMapper.imageDtoListToDomainList(foodItemImageDTOList);
    }

    @Override
    public FoodItemImage getFoodItemImageById(Integer foodItemImageId) {
        return foodItemMapper.imageDtoToDomain(foodItemDatabase.getFoodItemImageById(foodItemImageId));
    }

    public void create(FoodItemImage foodItemImage) {
        this.foodItemDatabase.create(this.foodItemMapper.imageDomainToDto(foodItemImage));
    }
}
