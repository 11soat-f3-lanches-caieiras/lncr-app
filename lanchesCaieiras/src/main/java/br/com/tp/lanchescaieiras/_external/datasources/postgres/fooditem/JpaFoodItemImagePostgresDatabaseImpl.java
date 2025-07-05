package br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._external.datasources.storage.fooditem.FoodItemImageStorageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaFoodItemImagePostgresDatabaseImpl {

    public final JpaFoodItemImagePostgresRepository jpaFoodItemImagePostgresRepository;
    public final FoodItemImageStorageImpl foodItemImageStorage;
    public final JpaFoodItemPostgresMapper foodItemMapper;

    public JpaFoodItemImagePostgresDatabaseImpl(JpaFoodItemImagePostgresRepository jpaFoodItemImagePostgresRepository,
                                                FoodItemImageStorageImpl foodItemImageStorage,
                                                JpaFoodItemPostgresMapper foodItemMapper) {
        this.jpaFoodItemImagePostgresRepository = jpaFoodItemImagePostgresRepository;
        this.foodItemImageStorage = foodItemImageStorage;
        this.foodItemMapper = foodItemMapper;
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

    public void deleteByFoodItemId(List<FoodItemImageDTO> foodItemImageDTOList) {
        this.jpaFoodItemImagePostgresRepository.deleteAll(this.foodItemMapper.toJpaFoodItemImageEntityList(foodItemImageDTOList));
    }

    public FoodItemImageDTO save(FoodItemImageDTO foodItemImageDTO) {
        JpaFoodItemImagePostgresEntity newJpaImage = this.foodItemMapper.toJpaFoodItemImageEntity(foodItemImageDTO);
        newJpaImage = this.jpaFoodItemImagePostgresRepository.save(newJpaImage);
        return foodItemMapper.toFoodItemImageDTO(newJpaImage);
    }

    public FoodItemImageDTO findById(Integer foodItemImageId) {
        return this.foodItemMapper.toFoodItemImageDTO(
                this.jpaFoodItemImagePostgresRepository.findById(foodItemImageId)
                        .orElse(null));
    }

    public List<FoodItemImageDTO> deleteImagesByFoodItemId(Integer foodItemId) {
        List<FoodItemImageDTO> listImagestoDelete = findAllByFoodItemId(foodItemId);
        this.jpaFoodItemImagePostgresRepository.deleteAll(foodItemMapper.toJpaFoodItemImageEntityList(listImagestoDelete));
        return listImagestoDelete;
    }

    public void delete(FoodItemImageDTO foodItemImageDTO) {
        this.jpaFoodItemImagePostgresRepository.delete(foodItemMapper.toJpaFoodItemImageEntity(foodItemImageDTO));
    }
}
