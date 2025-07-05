package br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemDatabase;
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
    public FoodItemDTO save(FoodItemDTO foodItemDTO) {
        return this.jpaFoodItemDatabase.save(foodItemDTO);
    }

    @Override
    public FoodItemImageDTO save(FoodItemImageDTO foodItemImageDTO) {
        FoodItemImageDTO newFoodItemImage = this.jpaFoodItemImageDatabase.save(foodItemImageDTO);
        if (newFoodItemImage != null) {
            this.foodItemImageStorage.saveImageFile(foodItemImageDTO);
        }
        return foodItemImageDTO;
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
    public void saveImages(List<FoodItemImageDTO> foodItemImageDTOList) {
        jpaFoodItemImageDatabase.saveAll(foodItemImageDTOList);
    }

    @Override
    public void saveImageFiles(List<FoodItemImageDTO> foodItemImageDTOList) {
        foodItemImageStorage.saveImagesFiles(null);
    }

    @Override
    public List<FoodItemDTO> getAllFoodItems(Integer _limit, Integer categoryId, Boolean includeImages) {
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
    public FoodItemDTO getFoodItemById(Integer foodItemId, Boolean includeImages) {
        FoodItemDTO foodItemDTO = this.jpaFoodItemDatabase.findById(foodItemId);
        if (includeImages != false && foodItemDTO != null) {
            foodItemDTO.setImages(this.jpaFoodItemImageDatabase.findAllByFoodItemId(foodItemId));
            return foodItemDTO;
        }
        return foodItemDTO;
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
    public List<FoodItemImageDTO> findAllFoodItemImagesByFoodItemId(Integer foodItemId, Boolean includeData) {
        List<FoodItemImageDTO> foodItemImageDTOList = this.jpaFoodItemImageDatabase.findAllByFoodItemId(foodItemId);
        //Validar se includeData não é false e se lista de imagens não é vazia
        if (includeData != false && !foodItemImageDTOList.isEmpty()) {
            for (FoodItemImageDTO image : foodItemImageDTOList) {
                try {
                    image.set_data(this.foodItemImageStorage.getImgaeData(image.getFileName()));
                } catch (Exception e) {
                    log.error("Erro ao buscar arquivo " + image.getFileName() + " no sistema de arquivos");
                }
            }
        }
        return foodItemImageDTOList;
    }

    @Override
    public void create(FoodItemImageDTO foodItemImageDTO) {
        this.jpaFoodItemImageDatabase.save(foodItemImageDTO);
        this.foodItemImageStorage.saveImageFile(foodItemImageDTO);
    }

    @Override
    public FoodItemImageDTO getFoodItemImageById(Integer foodItemImageId) {
        FoodItemImageDTO foodItemImageDTO = this.jpaFoodItemImageDatabase.findById(foodItemImageId);
        try {
            foodItemImageDTO.set_data(this.foodItemImageStorage.getImgaeData(foodItemImageDTO.getFileName()));
        } catch (Exception e) {
            return foodItemImageDTO;
        }
        return foodItemImageDTO;
    }

    @Override
    public void deleteImagesByFoodItemId(Integer foodItemId) {
        List<FoodItemImageDTO> listImagesToDelete = this.jpaFoodItemImageDatabase.deleteImagesByFoodItemId(foodItemId);
        if (listImagesToDelete != null) {
            this.foodItemImageStorage.deleteImagesFiles(listImagesToDelete);
        }
    }
}
