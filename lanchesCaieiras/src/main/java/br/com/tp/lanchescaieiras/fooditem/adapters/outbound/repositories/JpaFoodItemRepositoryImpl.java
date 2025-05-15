package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemEntity;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemRepository;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JpaFoodItemRepositoryImpl implements FoodItemRepository {

    public final JpaFoodItemReposity jpaFoodItemReposity;
    public final FoodItemMapper foodItemMapper;

    public JpaFoodItemRepositoryImpl(JpaFoodItemReposity jpaFoodItemReposity, FoodItemMapper foodItemMapper) {
        this.jpaFoodItemReposity = jpaFoodItemReposity;
        this.foodItemMapper = foodItemMapper;
    }

    @Override
    public FoodItem save(FoodItem foodItem) {
        JpaFoodItemEntity jpaFoodItemEntity = foodItemMapper.domainToJpa(foodItem);
        JpaFoodItemEntity savedEntity = jpaFoodItemReposity.save(jpaFoodItemEntity);
        return foodItemMapper.jpaToDomain(savedEntity);
    }
}
