package br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface JpaCustomerOrderFoodItemPostgresRepository extends JpaRepository<JpaCustomerOrderFoodItemPostgresEntity, Integer> {

    @Query(value = "select * from customer_order_food_item where order_id = :orderId", nativeQuery = true)
    List<JpaCustomerOrderFoodItemPostgresEntity> findByCustomerOrderId(@Param("orderId") Integer orderId);
}
