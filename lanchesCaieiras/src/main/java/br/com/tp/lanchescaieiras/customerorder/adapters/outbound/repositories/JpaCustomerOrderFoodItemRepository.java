package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.entities.JpaCustomerOrderFoodItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface JpaCustomerOrderFoodItemRepository extends JpaRepository<JpaCustomerOrderFoodItemEntity, Integer> {

    @Query(value = "select * from customer_order_food_item where order_id = :orderId", nativeQuery = true)
    List<JpaCustomerOrderFoodItemEntity> findByCustomerOrderId(@Param("orderId") Integer orderId);
}
