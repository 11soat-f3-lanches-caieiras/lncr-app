package br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemDatabase;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.external.storage.FoodItemImageStorageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaFoodItemImagePostgresDatabaseImpl {

    public final JpaFoodItemImagePostgresRepository jpaFoodItemImagePostgresRepository;
    public final FoodItemImageStorageImpl foodItemImageStorage;
    public final JpaFoodItemPostgresMapper foodItemMapper;
    public final FoodItemConfig foodItemConfig;

    public JpaFoodItemImagePostgresDatabaseImpl(JpaFoodItemImagePostgresRepository jpaFoodItemImagePostgresRepository,
                                                FoodItemImageStorageImpl foodItemImageStorage,
                                                JpaFoodItemPostgresMapper foodItemMapper,
                                                FoodItemConfig foodItemConfig) {
        this.jpaFoodItemImagePostgresRepository = jpaFoodItemImagePostgresRepository;
        this.foodItemImageStorage = foodItemImageStorage;
        this.foodItemMapper = foodItemMapper;
        this.foodItemConfig = foodItemConfig;
    }

    public void saveAll(List<FoodItemImageDTO> foodItemImageDTOList) {
        List<JpaFoodItemImagePostgresEntity> jpaFoodItemImageEntityList = foodItemMapper.toJpaFoodItemImageEntityList(foodItemImageDTOList);
        this.jpaFoodItemImagePostgresRepository.saveAll(jpaFoodItemImageEntityList);
    }

    public List<FoodItemImageDTO> findAllByFoodItemId(Integer id) {
        return this.jpaFoodItemImagePostgresRepository.findAllByFoodItemId(id)
                .stream()
                .map(foodItemMapper::toFoodItemImageDTO)
                .toList();
    }

    /*@Override
    public FoodItemDTO saveImages(br.com.tp.lanchescaieiras.fooditem.domain.FoodItem foodItem) {
        *//*int limit = 0;
        for (FoodItemImage foodItemImage : foodItemDTO.getImages()) {
            if (limit >= 4) {
                break;
            }
            foodItemDTO.getImages().set(limit, saveImageList(foodItemImage, foodItemDTO.getId(), limit));
            limit++;
        }*//*
        return null;
    }


    @Override
    public List<br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage> findAllImagesByFoodItemId(Integer foodItemId) {
       *//* List<JpaFoodItemImageEntity> jpaFoodItemImageEntityList = jpaFoodItemImagePostgresRepository.findAllByFoodItemId(foodItem.getId())
                .stream()
                .toList();
        for (JpaFoodItemImageEntity jpaFoodItemImageEntity : jpaFoodItemImageEntityList) {
            jpaFoodItemImageEntity.setLocation(foodItemConfig.getImage().getLocationPrefix() + "/" + jpaFoodItemImageEntity.getId());
        }
        foodItem.setImages(jpaFoodItemImageEntityList.stream()
                .map(foodItemImageMapper::jpaToDomain)
                .toList());
        return foodItem;*//*
        return null;
    }


    @Override
    public void deleteFoodItemImagesByFoodItemId(Integer foodItemId) {
        *//*List<JpaFoodItemImageEntity> jpaFoodItemImageEntityList = jpaFoodItemImagePostgresRepository.findAllByFoodItemId(foodItemId)
                .stream()
                .toList();
        for (JpaFoodItemImageEntity jpaFoodItemImageEntity : jpaFoodItemImageEntityList) {
            foodItemImageStorage.deleteImageFile(jpaFoodItemImageEntity.getFileName());
            jpaFoodItemImagePostgresRepository.deleteById(jpaFoodItemImageEntity.getId());
        }*//*
    }

    @Override
    public Optional<FoodItemImageDTO> findImageById(Integer id) {
       *//* JpaFoodItemImageEntity jpaFoodItemImageEntity = jpaFoodItemImagePostgresRepository.findById(id)
                .orElseThrow(() -> new FoodItemException("Imagem não encontrada com o ID: " + id, 404));
        jpaFoodItemImageEntity.set_data(foodItemImageStorage.getImgaeData(jpaFoodItemImageEntity.getFileName()));
        return foodItemImageMapper.jpaToImageData(jpaFoodItemImageEntity);*//*
        return null;
    }

    @Override
    public FoodItemImageDTO updateImageById(Integer id, FoodItemImageDTO foodItemImageDTO) {
        *//* JpaFoodItemImageEntity jpaFoodItemImageEntity = new JpaFoodItemImageEntity();
        try {
            jpaFoodItemImageEntity = jpaFoodItemImagePostgresRepository.findById(id).get();
        } catch (Exception e) {
            throw new FoodItemException("Imagem não encontrada com o ID: " + id, 404);
        }

        if (foodItemImage.get_data() != null) {
            foodItemImageStorage.deleteImageFile(jpaFoodItemImageEntity.getFileName());
            jpaFoodItemImageEntity.setFileName(id + "." + foodItemImage.getFileExtension());
            jpaFoodItemImageEntity.setLocation(foodItemConfig.getImage().getLocationPrefix() + "/" + jpaFoodItemImageEntity.getId());
            jpaFoodItemImageEntity.set_data(foodItemImage._data);
            jpaFoodItemImagePostgresRepository.save(jpaFoodItemImageEntity);
            foodItemImageStorage.saveImageFile(jpaFoodItemImageEntity);
        }

        return foodItemImageMapper.jpaToDomain(saveJpaImageEntity(jpaFoodItemImageEntity));*//*
        return null;
    }

    *//*private FoodItemImage saveImageList(FoodItemImage foodItemImage, Integer foodItemId, Integer index) {
        if (foodItemImage.get_data() != null) {
            JpaFoodItemImageEntity jpaFoodItemImageEntity = new JpaFoodItemImageEntityfromImage(foodItemImage, foodItemId, index);
            try {
                if (jpaFoodItemImageEntity.getFileExtension() != null) {
                    foodItemImageStorage.saveImageFile(jpaFoodItemImageEntity);
                    jpaFoodItemImagePostgresRepository.save(jpaFoodItemImageEntity);
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
    }*//*

    *//*private JpaFoodItemImageEntity newJpaFoodItemImageEntityfromImage(FoodItemImage foodItemImage, Integer foodItemId, Integer index) {
        JpaFoodItemImageEntity jpaFoodItemImageEntity = new JpaFoodItemImageEntity();
        jpaFoodItemImageEntity.setFoodItemId(foodItemId);
        jpaFoodItemImageEntity = foodItemImageMapper.domainToJpa(foodItemImage, foodItemId, index + 1);
        jpaFoodItemImageEntity.setLocation(foodItemConfig.getImage().getLocationPrefix() + "/" + jpaFoodItemImageEntity.getId());
        return jpaFoodItemImageEntity;
    }*//*

    *//*private JpaFoodItemImageEntity saveJpaImageEntity(JpaFoodItemImageEntity jpaFoodItemImageEntity) {
        try {
            if (jpaFoodItemImageEntity.getFileExtension() != null) {
                foodItemImageStorage.saveImageFile(jpaFoodItemImageEntity);
                jpaFoodItemImagePostgresRepository.save(jpaFoodItemImageEntity);
            }
            return jpaFoodItemImageEntity;
        } catch (Exception e) {
            if (e instanceof FoodItemException) {
                throw e;
            } else {
                throw new FoodItemException("Erro ao salvar a imagem", 500);
            }
        }
    }*/

}
