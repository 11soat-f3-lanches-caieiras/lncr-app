package br.com.tp.lanchescaieiras._core.adapters.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItem;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemCategory;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;

import java.util.List;

public class FoodItemGatewayImpl implements FoodItemGateway {

    private final FoodItemDatabase foodItemDatabase;
    private final FoodItemMapper foodItemMapper;

    public FoodItemGatewayImpl(FoodItemDatabase foodItemDatabase, FoodItemMapper foodItemMapper) {
        this.foodItemDatabase = foodItemDatabase;
        this.foodItemMapper = foodItemMapper;
    }

    @Override
    public void delete(FoodItem foodItem) {
        this.foodItemDatabase.delete(this.foodItemMapper.domainToDto(foodItem));
    }

    @Override
    public void delete(FoodItemImage foodItemImage) {
        this.foodItemDatabase.delete(this.foodItemMapper.imageDomainToDto(foodItemImage));
    }

    @Override
    public void deleteImagesByFoodItemId(Integer foodItemId) {
        this.foodItemDatabase.deleteImagesByFoodItemId(foodItemId);
    }

    @Override
    public void deleteImageFile(String fileName) {
        this.foodItemDatabase.deleteImageFile(fileName);
    }

    @Override
    public boolean existsByName(String foodItemName) {
        return foodItemDatabase.existsByName(foodItemName);
    }

    @Override
    public List<FoodItemImage> getAllImagesByFoodItemId(Integer foodItemId, Boolean includeData) {
        List<FoodItemImageDTO> foodItemImageDTOList = this.foodItemDatabase.findAllFoodItemImagesByFoodItemId(foodItemId, includeData);
        return foodItemMapper.imageDtoListToDomainList(foodItemImageDTOList);
    }

    @Override
    public List<FoodItem> getAllFoodItems(Integer _limit, String category, Boolean includeImages) {
        Integer categoryId = category == null ? null : FoodItemCategory.fromDescription(category).getId();
        return this.foodItemDatabase.findAllFoodItems(_limit, categoryId, includeImages)
                .stream()
                .map(foodItemMapper::dtoToDomain)
                .toList();
    }

    @Override
    public FoodItem getFoodItemById(Integer foodItemId) {
        return getFoodItemById(foodItemId, false);
    }

    @Override
    public FoodItem getFoodItemById(Integer foodItemId, Boolean includeImages) {
        return this.foodItemMapper.dtoToDomain(this.foodItemDatabase.findFoodItemById(foodItemId, includeImages));
    }

    @Override
    public FoodItemImage getFoodItemImageById(Integer foodItemImageId) {
        return foodItemMapper.imageDtoToDomain(foodItemDatabase.findFoodItemImageById(foodItemImageId));
    }

    @Override
    public FoodItem save(FoodItem foodItem) {
        FoodItemDTO foodItemDTO = this.foodItemMapper.domainToDto(foodItem);
        foodItemDTO = this.foodItemDatabase.create(foodItemDTO);
        return this.foodItemMapper.dtoToDomain(foodItemDTO);
    }

    @Override
    public FoodItem saveFoodItem(FoodItem foodItem) {
        FoodItemDTO foodItemDTO = this.foodItemMapper.domainToDto(foodItem);
        foodItemDTO = this.foodItemDatabase.save(foodItemDTO);
        return this.foodItemMapper.dtoToDomain(foodItemDTO);
    }

    @Override
    public FoodItemImage save(FoodItemImage foodItemImage) {
        FoodItemImageDTO foodItemImageDTO = foodItemMapper.imageDomainToDto(foodItemImage);
        foodItemImageDTO = this.foodItemDatabase.save(foodItemImageDTO);
        return foodItemMapper.imageDtoToDomain(foodItemImageDTO);
    }
}
