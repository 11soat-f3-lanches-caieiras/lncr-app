package br.com.tp.lanchescaieiras.fooditem.external;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemDatabase;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemPostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemImagePostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.storage.FoodItemImageStorageImpl;

import java.util.ArrayList;
import java.util.List;

public class FoodItemDataProxy implements FoodItemDatabase {

    private final JpaFoodItemPostgresDatabaseImpl jpaFoodItemDatabase;
    private final JpaFoodItemImagePostgresDatabaseImpl jpaFoodItemImageDatabase;
    private final FoodItemImageStorageImpl foodItemImageStorage;

    public FoodItemDataProxy(JpaFoodItemPostgresDatabaseImpl jpaFoodItemDatabase,
                             JpaFoodItemImagePostgresDatabaseImpl jpaFoodItemImageDatabase,
                             FoodItemImageStorageImpl foodItemImageStorage) {
        this.jpaFoodItemDatabase = jpaFoodItemDatabase;
        this.jpaFoodItemImageDatabase = jpaFoodItemImageDatabase;
        this.foodItemImageStorage = foodItemImageStorage;
    }

    @Override
    public FoodItemDTO save(FoodItemDTO foodItemDTO) {
        return null;
    }

    @Override
    public FoodItemDTO create(FoodItemDTO foodItemDTO) {
        FoodItemDTO savedFoodItemDTO = jpaFoodItemDatabase.save(foodItemDTO);
        savedFoodItemDTO.setImages(foodItemDTO.getImages());
        if (savedFoodItemDTO.getImages() != null && !savedFoodItemDTO.getImages().isEmpty()) {
            for (int i = 0; i < savedFoodItemDTO.getImages().size(); i++) {
                savedFoodItemDTO.getImages().get(i).setFoodItemId(savedFoodItemDTO.getId());
                String imageId = String.valueOf(savedFoodItemDTO.getId()) + i;
                savedFoodItemDTO.getImages().get(i).setId(Integer.parseInt(imageId));
                savedFoodItemDTO.getImages().get(i).setFileName(imageId + "." + savedFoodItemDTO.getImages().get(i).getFileExtension());
            }
            jpaFoodItemImageDatabase.saveAll(savedFoodItemDTO.getImages());
            foodItemImageStorage.saveImagesFiles(savedFoodItemDTO.getImages());
            return savedFoodItemDTO;
        }



        return null;
    }

    @Override
    public boolean existsByName(String foodItemName) {
        return jpaFoodItemDatabase.existsByName(foodItemName);
    }

    @Override
    public void saveImages(List<FoodItemImageDTO> foodItemImageDTOList) {
        jpaFoodItemImageDatabase.saveAll(foodItemImageDTOList);
    }

    @Override
    public void saveImageFiles(List<FoodItemImageDTO> foodItemImageDTOList) {
            foodItemImageStorage.saveImagesFiles(null);
    }

    @Override
    public List<FoodItemDTO> getAllFoodItems(Integer _limit, Integer categoryId, Boolean includeImages) {
        List<FoodItemDTO> foodItemsDTOList = new ArrayList<>();

        if (categoryId == null) {
            foodItemsDTOList = jpaFoodItemDatabase.getAllFoodItems(_limit);
        } else {
            foodItemsDTOList = jpaFoodItemDatabase.getAllFoodItemsByCategory(_limit, categoryId);
        }

        if (includeImages != null && includeImages) {
            for (FoodItemDTO foodItemDTO : foodItemsDTOList) {
                List<FoodItemImageDTO> foodItemImages = jpaFoodItemImageDatabase.findAllByFoodItemId(foodItemDTO.getId());
                foodItemDTO.setImages(foodItemImages);
            }
        } else {
            for (FoodItemDTO foodItemDTO : foodItemsDTOList) {
                foodItemDTO.setImages(null);
            }
        }
        return foodItemsDTOList;
    }
}
