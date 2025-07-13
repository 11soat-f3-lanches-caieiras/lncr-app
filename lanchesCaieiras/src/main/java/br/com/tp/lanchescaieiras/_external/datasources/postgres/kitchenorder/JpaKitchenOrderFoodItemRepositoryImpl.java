package br.com.tp.lanchescaieiras._external.datasources.postgres.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderFoodItemDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaKitchenOrderFoodItemRepositoryImpl {

    public List<KitchenOrderFoodItemDTO> saveAll(List<KitchenOrderFoodItemDTO> foodItemsDTOList, JpaKitchenOrderFoodItemRepository jpaKitchenOrderFoodItemRepository, JpaKitchenOrderMapper jpaKitchenOrderMapper) {
        List<JpaKitchenOrderFoodItemEntity> jpaKitchenOrderFoodItemList = foodItemsDTOList.stream()
                .map(jpaKitchenOrderMapper::kitchenOrderFoodItemDtoToJpa)
                .toList();
        jpaKitchenOrderFoodItemList = jpaKitchenOrderFoodItemRepository.saveAll(jpaKitchenOrderFoodItemList);
        return jpaKitchenOrderFoodItemList.stream().map(jpaKitchenOrderMapper::jpaKitchenOrderFoodItemToDTO).toList();

    }
}
