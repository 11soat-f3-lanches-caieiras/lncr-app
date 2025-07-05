package br.com.tp.lanchescaieiras._external.datasources.postgres.kitchenorder;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface JpaKitchenOrderRepository extends JpaRepository<JpaKitchenOrderEntity, Integer> {

    @Query(value = "select * from kitchen_order where status_id = :statusId", nativeQuery = true)
    List<JpaKitchenOrderEntity> findByStatusId(@Param("statusId") Integer statusId);

    @Query(value = "select * from kitchen_order where customer_order_id =:customerOrderId", nativeQuery = true)
    JpaKitchenOrderEntity findByCustomerOrderId(@Param("customerOrderId") Integer customerOrderId);


}
