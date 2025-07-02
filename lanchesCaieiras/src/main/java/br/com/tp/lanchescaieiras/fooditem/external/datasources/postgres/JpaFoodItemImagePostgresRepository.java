package br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaFoodItemImagePostgresRepository extends JpaRepository<JpaFoodItemImagePostgresEntity, Integer> {

    @Query(value = "select * from food_item_image where food_item_id = :foodItemId", nativeQuery = true)
    List<JpaFoodItemImagePostgresEntity> findAllByFoodItemId(Integer foodItemId);

    @Query(value = "SELECT COUNT(*) from food_item_image where food_item_id = :foodItemId", nativeQuery = true)
    Integer getCountImagesByFoodItemId(@Param("foodItemId") Integer foodItemId);

    @Query(value = "DELETE FROM food_item_image where food_item_id = :foodItemId", nativeQuery = true)
    void deleteFoodItemImagesByFoodItemId(@Param("foodItemId") Integer foodItemId);

}
