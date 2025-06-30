package br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaFoodItemPostgresReposity extends JpaRepository<JpaFoodItemPostgresEntity, Integer> {

    @Query(value = "SELECT * FROM food_item WHERE category_id = :categoryId LIMIT :limit", nativeQuery = true)
    List<JpaFoodItemPostgresEntity> findAllByCategory(@Param("limit") Integer limit, @Param("categoryId") Integer categoryId);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM food_item WHERE UPPER(name) = UPPER(:foodItemName)", nativeQuery = true)
    boolean existsByName(@Param("foodItemName") String foodItemName);

    /*@Query(value = "DELETE * FROM food_item where id = :id", nativeQuery = true)
    void deleteFoodItemById(@Param("id") Integer id);*/



}
