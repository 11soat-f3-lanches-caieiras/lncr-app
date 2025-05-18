package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.storage.FoodItemImageStorage;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImageRepository;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.config.FoodItemImageConfig;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras.fooditem.application.mappers.FoodItemImageMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaFoodItemImageRepositoryImpl implements FoodItemImageRepository {

    public final JpaFoodItemImageRepository jpaFoodItemImageRepository;
    public final FoodItemImageStorage foodItemImageStorage;
    public final FoodItemImageMapper foodItemImageMapper;
    public final FoodItemImageConfig foodItemImageConfig;


    public JpaFoodItemImageRepositoryImpl(@Lazy JpaFoodItemImageRepository jpaFoodItemImageRepository, FoodItemImageMapper foodItemImageMapper,
                                          FoodItemImageStorage foodItemImageStorage, FoodItemImageConfig foodItemImageConfig) {
        this.jpaFoodItemImageRepository = jpaFoodItemImageRepository;
        this.foodItemImageMapper = foodItemImageMapper;
        this.foodItemImageStorage = foodItemImageStorage;
        this.foodItemImageConfig = foodItemImageConfig;
    }

    @Override
    public FoodItem saveImages(FoodItem foodItem) {
        int limit = 0;
        for (FoodItemImage foodItemImage : foodItem.getImages()) {
            if (limit >= 4) { break; }
            foodItem.getImages().set(limit, saveImageList(foodItemImage, foodItem.getId(), limit));
            limit++;
        }
        return foodItem;
    }


    @Override
    public FoodItem findAllImagesByFoodItemId(FoodItem foodItem) {
        List<JpaFoodItemImageEntity> jpaFoodItemImageEntityList = jpaFoodItemImageRepository.findAllByFoodItemId(foodItem.getId())
                .stream()
                .toList();
        for (JpaFoodItemImageEntity jpaFoodItemImageEntity : jpaFoodItemImageEntityList) {
            jpaFoodItemImageEntity.setLocation(foodItemImageConfig.getLocationPrefix() + "/" + jpaFoodItemImageEntity.getId());
        }
        foodItem.setImages(jpaFoodItemImageEntityList.stream()
                .map(foodItemImageMapper::jpaToDomain)
                .toList());
        return foodItem;
    }


    @Override
    public void deleteFoodItemImagesByFoodItemId(Integer foodItemId) {
        List<JpaFoodItemImageEntity> jpaFoodItemImageEntityList = jpaFoodItemImageRepository.findAllByFoodItemId(foodItemId)
                .stream()
                .toList();
        for (JpaFoodItemImageEntity jpaFoodItemImageEntity : jpaFoodItemImageEntityList) {
            foodItemImageStorage.deleteImageFile(jpaFoodItemImageEntity.getFileName());
            jpaFoodItemImageRepository.deleteById(jpaFoodItemImageEntity.getId());
        }
    }

    @Override
    public FoodItemImage findImageById(Integer id) {
        JpaFoodItemImageEntity jpaFoodItemImageEntity = jpaFoodItemImageRepository.findById(id)
                .orElseThrow(() -> new FoodItemException("Imagem não encontrada com o ID: " + id, 404));
        jpaFoodItemImageEntity.set_data(foodItemImageStorage.getImgaeData(jpaFoodItemImageEntity.getFileName()));
        return foodItemImageMapper.jpaToImageData(jpaFoodItemImageEntity);
    }

    @Override
    public FoodItemImage updateImageById(Integer id, FoodItemImage foodItemImage) {
        JpaFoodItemImageEntity jpaFoodItemImageEntity = new JpaFoodItemImageEntity();
        try {
            jpaFoodItemImageEntity = jpaFoodItemImageRepository.findById(id).get();
        }catch (Exception e){
            throw new FoodItemException("Imagem não encontrada com o ID: " + id, 404);
        }

        if (foodItemImage.get_data() != null) {
            foodItemImageStorage.deleteImageFile(jpaFoodItemImageEntity.getFileName());
            jpaFoodItemImageEntity.setFileName(id + "." + foodItemImage.getFileExtension());
            jpaFoodItemImageEntity.setLocation(foodItemImageConfig.getLocationPrefix() + "/" + jpaFoodItemImageEntity.getId());
            jpaFoodItemImageEntity.set_data(foodItemImage._data);
            jpaFoodItemImageRepository.save(jpaFoodItemImageEntity);
            foodItemImageStorage.saveImageFile(jpaFoodItemImageEntity);
        }

        return foodItemImageMapper.jpaToDomain(saveJpaImageEntity(jpaFoodItemImageEntity));
    }

    private FoodItemImage saveImageList(FoodItemImage foodItemImage, Integer foodItemId, Integer index) {
        if (foodItemImage.get_data() != null) {
            JpaFoodItemImageEntity jpaFoodItemImageEntity = newJpaFoodItemImageEntityfromImage(foodItemImage, foodItemId, index);
            try {
                if (jpaFoodItemImageEntity.getFileExtension() != null) {
                    foodItemImageStorage.saveImageFile(jpaFoodItemImageEntity);
                    jpaFoodItemImageRepository.save(jpaFoodItemImageEntity);
                }
                return foodItemImageMapper.jpaToDomain(saveJpaImageEntity(jpaFoodItemImageEntity));
            } catch (Exception e) {
                if (e instanceof FoodItemException) {
                    throw e;
                } else {
                    throw new FoodItemException("Erro ao salvar a imagem", 500);
                }
            }
        }
        return foodItemImage;
    }

    private JpaFoodItemImageEntity newJpaFoodItemImageEntityfromImage(FoodItemImage foodItemImage, Integer foodItemId, Integer index) {
        JpaFoodItemImageEntity jpaFoodItemImageEntity = new JpaFoodItemImageEntity();
        jpaFoodItemImageEntity.setFoodItemId(foodItemId);
        jpaFoodItemImageEntity = foodItemImageMapper.domainToJpa(foodItemImage, foodItemId, index + 1);
        jpaFoodItemImageEntity.setLocation(foodItemImageConfig.getLocationPrefix() + "/" + jpaFoodItemImageEntity.getId());
        return jpaFoodItemImageEntity;
    }

    private JpaFoodItemImageEntity saveJpaImageEntity(JpaFoodItemImageEntity jpaFoodItemImageEntity) {
        try {
            if (jpaFoodItemImageEntity.getFileExtension() != null) {
                foodItemImageStorage.saveImageFile(jpaFoodItemImageEntity);
                jpaFoodItemImageRepository.save(jpaFoodItemImageEntity);
            }
            return jpaFoodItemImageEntity;
        } catch (Exception e) {
            if (e instanceof FoodItemException) {
                throw e;
            } else {
                throw new FoodItemException("Erro ao salvar a imagem", 500);
            }
        }
    }
}
