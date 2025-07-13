package br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaFoodItemImageRepositoryImpl {

    public void saveAll(List<FoodItemImageDTO> foodItemImageDTOList, JpaFoodItemImageRepository jpaFoodItemImageRepository, JpaFoodItemMapper jpaFoodItemMapper) {
        List<JpaFoodItemImageEntity> jpaFoodItemImageEntityList = jpaFoodItemMapper.toJpaFoodItemImageEntityList(foodItemImageDTOList);
        jpaFoodItemImageRepository.saveAll(jpaFoodItemImageEntityList);
    }

    public List<FoodItemImageDTO> findAllByFoodItemId(Integer id, JpaFoodItemImageRepository jpaFoodItemImageRepository, JpaFoodItemMapper jpaFoodItemMapper) {
        return jpaFoodItemImageRepository.findAllByFoodItemId(id)
                .stream()
                .map(jpaFoodItemMapper::toFoodItemImageDTO)
                .toList();
    }

    public void deleteByFoodItemId(List<FoodItemImageDTO> foodItemImageDTOList, JpaFoodItemImageRepository jpaFoodItemImageRepository, JpaFoodItemMapper jpaFoodItemMapper) {
        jpaFoodItemImageRepository.deleteAll(jpaFoodItemMapper.toJpaFoodItemImageEntityList(foodItemImageDTOList));
    }

    public FoodItemImageDTO save(FoodItemImageDTO foodItemImageDTO, JpaFoodItemImageRepository jpaFoodItemImageRepository, JpaFoodItemMapper jpaFoodItemMapper) {
        JpaFoodItemImageEntity newJpaImage = jpaFoodItemMapper.toJpaFoodItemImageEntity(foodItemImageDTO);
        newJpaImage = jpaFoodItemImageRepository.save(newJpaImage);
        return jpaFoodItemMapper.toFoodItemImageDTO(newJpaImage);
    }

    public FoodItemImageDTO findById(Integer foodItemImageId, JpaFoodItemImageRepository jpaFoodItemImageRepository, JpaFoodItemMapper jpaFoodItemMapper) {
        return jpaFoodItemMapper.toFoodItemImageDTO(
                jpaFoodItemImageRepository.findById(foodItemImageId)
                        .orElse(null));
    }

    public List<FoodItemImageDTO> deleteImagesByFoodItemId(Integer foodItemId, JpaFoodItemImageRepository jpaFoodItemImageRepository, JpaFoodItemMapper jpaFoodItemMapper) {
        List<FoodItemImageDTO> listImagestoDelete = findAllByFoodItemId(foodItemId, jpaFoodItemImageRepository, jpaFoodItemMapper);
        jpaFoodItemImageRepository.deleteAll(jpaFoodItemMapper.toJpaFoodItemImageEntityList(listImagestoDelete));
        return listImagestoDelete;
    }

    public void delete(FoodItemImageDTO foodItemImageDTO, JpaFoodItemImageRepository jpaFoodItemImageRepository, JpaFoodItemMapper jpaFoodItemMapper) {
        jpaFoodItemImageRepository.delete(jpaFoodItemMapper.toJpaFoodItemImageEntity(foodItemImageDTO));
    }
}
