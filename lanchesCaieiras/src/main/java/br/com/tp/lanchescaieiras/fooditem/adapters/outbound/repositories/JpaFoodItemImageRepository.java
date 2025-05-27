package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaFoodItemImageRepository extends JpaRepository<JpaFoodItemImageEntity, Integer> {

    @Query(value = "select * from food_item_image where food_item_id = :foodItemId", nativeQuery = true)
    List<JpaFoodItemImageEntity> findAllByFoodItemId(Integer foodItemId);

    @Query(value = "DELETE FROM food_item_image where food_item_id = :foodItemId", nativeQuery = true)
    void deleteFoodItemImagesByFoodItemId(@Param("foodItemId") Integer foodItemId);

}
