package br.com.tp.lanchescaieiras._external.datasources.postgres.kitchenorder;

import br.com.tp.lanchescaieiras._core.applications.kitchenorder.mappers.KitchenOrderFoodItemMapper;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderFoodItem;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderFoodItemRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaKitchenOrderFoodItemRepositoryImpl implements KitchenOrderFoodItemRepository {

    public final JpaKitchenOrderFoodItemRepository jpaKitchenOrderFoodItemRepository;
    public final KitchenOrderFoodItemMapper kitchenOrderFoodItemMapper;

    public JpaKitchenOrderFoodItemRepositoryImpl(@Lazy JpaKitchenOrderFoodItemRepository jpaKitchenOrderFoodItemRepository, KitchenOrderFoodItemMapper kitchenOrderFoodItemMapper) {
        this.jpaKitchenOrderFoodItemRepository = jpaKitchenOrderFoodItemRepository;
        this.kitchenOrderFoodItemMapper = kitchenOrderFoodItemMapper;
    }

    @Override
    public KitchenOrderFoodItem save(KitchenOrderFoodItem foodItem, Integer kitchenOrderId) {
        JpaKitchenOrderFoodItemEntity jpaKitchenOrderFoodItemEntity = kitchenOrderFoodItemMapper.domainToJpa(foodItem, kitchenOrderId);
        return kitchenOrderFoodItemMapper.jpaToDomain(this.jpaKitchenOrderFoodItemRepository.save(jpaKitchenOrderFoodItemEntity));
    }

    @Override
    public List<KitchenOrderFoodItem> findByKitchenOrderId(Integer kitchenOrderId) {
        List<JpaKitchenOrderFoodItemEntity> jpaKitchenOrderFoodItemEntities = this.jpaKitchenOrderFoodItemRepository.findByKitchenOrderId(kitchenOrderId);
        return jpaKitchenOrderFoodItemEntities.stream()
                .map(kitchenOrderFoodItemMapper::jpaToDomain)
                .toList();
    }
}
