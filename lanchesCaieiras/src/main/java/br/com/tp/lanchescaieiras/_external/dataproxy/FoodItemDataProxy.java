package br.com.tp.lanchescaieiras._external.dataproxy;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem.JpaFoodItemImagePostgresDatabaseImpl;
import br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem.JpaFoodItemPostgresDatabaseImpl;
import br.com.tp.lanchescaieiras._external.datasources.storage.fooditem.FoodItemImageStorageImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FoodItemDataProxy implements FoodItemDatabase {

    private static final Logger log = LoggerFactory.getLogger(FoodItemDataProxy.class);

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
    public FoodItemDTO create(FoodItemDTO foodItemDTO) {
        FoodItemDTO savedFoodItemDTO = jpaFoodItemDatabase.save(foodItemDTO);
        savedFoodItemDTO.setImages(foodItemDTO.getImages());
        if (savedFoodItemDTO.getImages() != null && !savedFoodItemDTO.getImages().isEmpty()) {
            for (int i = 0; i < savedFoodItemDTO.getImages().size(); i++) {
                savedFoodItemDTO.getImages().get(i).setFoodItemId(savedFoodItemDTO.getId());
                int imageIndex = i + 1;
                String imageId = String.valueOf(savedFoodItemDTO.getId()) + imageIndex;
                savedFoodItemDTO.getImages().get(i).setId(Integer.parseInt(imageId));
                savedFoodItemDTO.getImages().get(i).setFileName(imageId + "." + savedFoodItemDTO.getImages().get(i).getFileExtension());
            }
            jpaFoodItemImageDatabase.saveAll(savedFoodItemDTO.getImages());
            foodItemImageStorage.saveImagesFiles(savedFoodItemDTO.getImages());
            return savedFoodItemDTO;
        }
        return savedFoodItemDTO;
    }

    @Override
    public boolean existsByName(String foodItemName) {
        return jpaFoodItemDatabase.existsByName(foodItemName);
    }

    @Override
    public List<FoodItemDTO> findAllFoodItems(Integer _limit, Integer categoryId, Boolean includeImages) {
        List<FoodItemDTO> foodItemsDTOList;

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

    @Override
    public FoodItemDTO findFoodItemById(Integer foodItemId, Boolean includeImages) {
        FoodItemDTO foodItemDTO = this.jpaFoodItemDatabase.findById(foodItemId);
        if (includeImages != false && foodItemDTO != null) {
            foodItemDTO.setImages(this.jpaFoodItemImageDatabase.findAllByFoodItemId(foodItemId));
            return foodItemDTO;
        }
        return foodItemDTO;
    }

    @Override
    public List<FoodItemDTO> findFoodItemByIdList(List<Integer> foodItemIds) {
        return this.jpaFoodItemDatabase.findByIdList(foodItemIds);
    }

    @Override
    public FoodItemImageDTO findFoodItemImageById(Integer foodItemImageId) {
        return jpaFoodItemImageDatabase.findById(foodItemImageId);
    }

    @Override
    public List<FoodItemImageDTO> findAllFoodItemImagesByFoodItemId(Integer foodItemId, Boolean includeData) {
        return jpaFoodItemImageDatabase.findAllByFoodItemId(foodItemId);
    }

    @Override
    public FoodItemDTO save(FoodItemDTO foodItemDTO) {
        return this.jpaFoodItemDatabase.save(foodItemDTO);
    }

    @Override
    public FoodItemImageDTO save(FoodItemImageDTO foodItemImageDTO) {
        FoodItemImageDTO newFoodItemImageDTO = this.jpaFoodItemImageDatabase.save(foodItemImageDTO);
        if (newFoodItemImageDTO != null) {
            this.foodItemImageStorage.saveImageFile(foodItemImageDTO);
        }
        return foodItemImageDTO;
    }

    @Override
    public void delete(FoodItemDTO foodItemDTO) {
        this.jpaFoodItemDatabase.deleteById(foodItemDTO.getId());
        if (!foodItemDTO.getImages().isEmpty()) {
            this.jpaFoodItemImageDatabase.deleteByFoodItemId(foodItemDTO.getImages());
            this.foodItemImageStorage.deleteImagesFiles(foodItemDTO.getImages());
        }
    }

    @Override
    public void delete(FoodItemImageDTO foodItemImageDTO) {
        this.jpaFoodItemImageDatabase.delete(foodItemImageDTO);
        this.foodItemImageStorage.deleteImageFile(foodItemImageDTO.getFileName());
    }

    @Override
    public void deleteImageFile(String fileName) {
        this.foodItemImageStorage.deleteImageFile(fileName);
    }

    @Override
    public void create(FoodItemImageDTO foodItemImageDTO) {
        this.jpaFoodItemImageDatabase.save(foodItemImageDTO);
        this.foodItemImageStorage.saveImageFile(foodItemImageDTO);
    }

    @Override
    public void deleteImagesByFoodItemId(Integer foodItemId) {
        List<FoodItemImageDTO> listImagesToDelete = this.jpaFoodItemImageDatabase.deleteImagesByFoodItemId(foodItemId);
        if (listImagesToDelete != null) {
            this.foodItemImageStorage.deleteImagesFiles(listImagesToDelete);
        }
    }
}
