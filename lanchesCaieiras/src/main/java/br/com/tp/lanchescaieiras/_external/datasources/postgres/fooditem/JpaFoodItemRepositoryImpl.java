package br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaFoodItemRepositoryImpl {


    public FoodItemDTO save(FoodItemDTO foodItemDTO, JpaFoodItemReposity jpaFoodItemReposity, JpaFoodItemMapper jpaFoodItemMapper) {
        JpaFoodItemEntity jpaFoodItemEntity = jpaFoodItemMapper.toJpaFoodItemPostgresEntity(foodItemDTO);
        jpaFoodItemEntity = jpaFoodItemReposity.save(jpaFoodItemEntity);
        return jpaFoodItemMapper.toFoodItemDTO(jpaFoodItemEntity);
    }

    public boolean existsByName(String foodItemName, JpaFoodItemReposity jpaFoodItemRepository, JpaFoodItemMapper jpaFoodItemMapper) {
        return jpaFoodItemRepository.existsByName(foodItemName);
    }

    public List<FoodItemDTO> getAllFoodItems(Integer _limit, JpaFoodItemReposity jpaFoodItemReposity, JpaFoodItemMapper jpaFoodItemMapper) {
        return jpaFoodItemReposity.findAll(Pageable.ofSize(_limit))
                .stream()
                .map(jpaFoodItemMapper::toFoodItemDTO)
                .toList();
    }

    public List<FoodItemDTO> getAllFoodItemsByCategory(Integer _limit, Integer categoryId, JpaFoodItemReposity jpaFoodItemRepository, JpaFoodItemMapper jpaFoodItemMapper) {
        return jpaFoodItemRepository.findAllByCategory(_limit, categoryId)
                .stream()
                .map(jpaFoodItemMapper::toFoodItemDTO)
                .toList();
    }

    public FoodItemDTO findById(Integer foodItemId, JpaFoodItemReposity jpaFoodItemRepository, JpaFoodItemMapper jpaFoodItemMapper) {
        return jpaFoodItemMapper.toFoodItemDTO(
                jpaFoodItemRepository.findById(foodItemId)
                        .orElse(null));
    }

    public List<FoodItemDTO> findByIdList(List<Integer> foodItemIds, JpaFoodItemReposity jpaFoodItemRepository, JpaFoodItemMapper jpaFoodItemMapper) {
        List<JpaFoodItemEntity> jpaFoodItemList = jpaFoodItemRepository.findByIdList(foodItemIds);
        return jpaFoodItemList.stream().map(jpaFoodItemMapper::toFoodItemDTO).toList();
    }

    public void deleteById(Integer foodItemId, JpaFoodItemReposity jpaFoodItemRepository) {
        jpaFoodItemRepository.deleteById(foodItemId);
    }


}
