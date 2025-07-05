package br.com.tp.lanchescaieiras._external.datasources.postgres.kitchenorder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface JpaKitchenOrderFoodItemRepository extends JpaRepository<JpaKitchenOrderFoodItemEntity, Integer> {

    @Query(value = "select * from kitchen_order_food_item where kitchen_order_id = :orderId", nativeQuery = true)
    List<JpaKitchenOrderFoodItemEntity> findByKitchenOrderId(@Param("orderId") Integer orderId);
}
