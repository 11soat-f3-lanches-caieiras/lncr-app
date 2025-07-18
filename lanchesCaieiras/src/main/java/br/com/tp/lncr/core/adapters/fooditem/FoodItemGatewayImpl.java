package br.com.tp.lncr.core.adapters.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.enums.FoodItemCategory;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.domain.fooditem.FoodItem;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;

import java.util.List;

public class FoodItemGatewayImpl implements FoodItemGateway {

    private final FoodItemDatabase foodItemDatabase;
    private final FoodItemMapper foodItemMapper;

    public FoodItemGatewayImpl(FoodItemDatabase foodItemDatabase, FoodItemMapper foodItemMapper) {
        this.foodItemDatabase = foodItemDatabase;
        this.foodItemMapper = foodItemMapper;
    }

    @Override
    public void deleteFoodItemImage(FoodItem foodItem) {
        this.foodItemDatabase.delete(this.foodItemMapper.foodItemToDTO(foodItem));
    }

    @Override
    public void deleteFoodItemImage(FoodItemImage foodItemImage) {
        this.foodItemDatabase.delete(this.foodItemMapper.foodItemImageToDTO(foodItemImage));
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
        return foodItemMapper.foodItemImageToDomainList(foodItemImageDTOList);
    }

    @Override
    public List<FoodItem> getAllFoodItems(Integer _limit, String category, Boolean includeImages) {
        Integer categoryId = category == null ? null : FoodItemCategory.fromDescription(category).getId();
        return this.foodItemDatabase.findAllFoodItems(_limit, categoryId, includeImages)
                .stream()
                .map(foodItemMapper::foodItemToDomain)
                .toList();
    }

    @Override
    public FoodItem getFoodItemById(Integer foodItemId, Boolean includeImages) {
        return this.foodItemMapper.foodItemToDomain(this.foodItemDatabase.findFoodItemById(foodItemId,includeImages));
    }

    @Override
    public FoodItem getFoodItemById(Integer foodItemId) {
        return getFoodItemById(foodItemId, false);
    }

    @Override
    public List<FoodItem> getFoodItemByIdList(List<Integer> foodItemIds) {
        List<FoodItemDTO> foodItemDTOList =  this.foodItemDatabase.findFoodItemByIdList(foodItemIds);
        return foodItemDTOList.stream().map(foodItemMapper::foodItemToDomain).toList();
    }

    @Override
    public FoodItemImage getFoodItemImageById(Integer foodItemImageId) {
        return foodItemMapper.foodItemImageToDomain(foodItemDatabase.findFoodItemImageById(foodItemImageId));
    }

    @Override
    public FoodItem createFoodItemImage(FoodItem foodItem) {
        FoodItemDTO foodItemDTO = this.foodItemMapper.foodItemToDTO(foodItem);
        foodItemDTO = this.foodItemDatabase.create(foodItemDTO);
        return this.foodItemMapper.foodItemToDomain(foodItemDTO);
    }

    @Override
    public FoodItem saveFoodItem(FoodItem foodItem) {
        FoodItemDTO foodItemDTO = this.foodItemMapper.foodItemToDTO(foodItem);
        foodItemDTO = this.foodItemDatabase.save(foodItemDTO);
        return this.foodItemMapper.foodItemToDomain(foodItemDTO);
    }

    @Override
    public FoodItemImage createFoodItemImage(FoodItemImage foodItemImage) {
        FoodItemImageDTO foodItemImageDTO = foodItemMapper.foodItemImageToDTO(foodItemImage);
        foodItemImageDTO = this.foodItemDatabase.save(foodItemImageDTO);
        return foodItemMapper.foodItemImageToDomain(foodItemImageDTO);
    }
}
