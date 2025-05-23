package br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.repositories;


import br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.entities.JpaKitchenOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface JpaKitchenOrderRepository extends JpaRepository<JpaKitchenOrderEntity, Integer> {

    @Query(value = "select * from customer_order where status_id = :statusId", nativeQuery = true)
    List<JpaKitchenOrderEntity> findByStatusId(@Param("statusId") Integer statusId);


}
