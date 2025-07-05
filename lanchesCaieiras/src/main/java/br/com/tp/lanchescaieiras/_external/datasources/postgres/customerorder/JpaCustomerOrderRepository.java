package br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface JpaCustomerOrderRepository extends JpaRepository<JpaCustomerOrderEntity, Integer> {

    @Query(value = "select * from customer_order where status_id = :statusId", nativeQuery = true)
    List<JpaCustomerOrderEntity> findByStatusId(@Param("statusId") Integer statusId);


}
