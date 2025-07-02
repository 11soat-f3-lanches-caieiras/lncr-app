package br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaFoodItemImagePostgresRepository extends JpaRepository<JpaFoodItemImagePostgresEntity, Integer> {

    @Query(value = "select * from food_item_image where food_item_id = :foodItemId", nativeQuery = true)
    List<JpaFoodItemImagePostgresEntity> findAllByFoodItemId(Integer foodItemId);

}
