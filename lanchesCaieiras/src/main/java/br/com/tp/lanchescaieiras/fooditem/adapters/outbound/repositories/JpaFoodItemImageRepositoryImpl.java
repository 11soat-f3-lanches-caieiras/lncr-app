package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.commons.domain.Image;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImageRepository;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemImageMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class JpaFoodItemImageRepositoryImpl implements FoodItemImageRepository {

    public final JpaFoodItemImageRepository jpaFoodItemImageRepository;
    public final FoodItemImageMapper foodItemImageMapper;

    public JpaFoodItemImageRepositoryImpl(@Lazy JpaFoodItemImageRepository jpaFoodItemImageRepository, FoodItemImageMapper foodItemImageMapper) {
        this.jpaFoodItemImageRepository = jpaFoodItemImageRepository;
        this.foodItemImageMapper = foodItemImageMapper;
    }

    @Override
    public Image save(Image image) {
        return new Image();
    }

    @Override
    public FoodItem saveImages(FoodItem foodItem) {
        JpaFoodItemImageEntity jpaFoodItemImageEntity = new JpaFoodItemImageEntity();
        jpaFoodItemImageEntity.setFoodItemId(foodItem.getId());
        int limit = 0;
        for (Image image : foodItem.getImages()) {
            if (limit >= 4) {
                break;
            }
            jpaFoodItemImageEntity = foodItemImageMapper.domainToJpa(image, foodItem.getId(), limit + 1);
            jpaFoodItemImageRepository.save(jpaFoodItemImageEntity);
            foodItem.getImages().set(limit, foodItemImageMapper.jpaToDomain(jpaFoodItemImageEntity));
            limit++;
        }
        return foodItem;
    }


}