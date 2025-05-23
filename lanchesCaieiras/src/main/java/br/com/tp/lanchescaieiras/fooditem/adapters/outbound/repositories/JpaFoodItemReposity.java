package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface JpaFoodItemReposity extends JpaRepository<JpaFoodItemEntity,Integer> {

    JpaFoodItemEntity save(JpaFoodItemEntity jpaFoodItemEntity);

    @Query(value = "SELECT * FROM food_item WHERE category = UPPER(:category) LIMIT :limit", nativeQuery = true)
    List<JpaFoodItemEntity> findAllByCategory(@Param("limit") Integer limit, @Param("category") String category);

    @Query(value = "DELETE * FROM food_item where id = :id", nativeQuery = true)
    void deleteFoodItemById(@Param("id") Integer id);

}
