package br.com.tp.lanchescaieiras._external.datasources.postgres.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface JpaPaymentRepository extends JpaRepository<JpaPaymentEntity, Integer> {

    @Query(value = "select * from payment p where p.order_id = :customerOrderId", nativeQuery = true)
    Optional<JpaPaymentEntity> findByCustomerOrOrderId(@Param("customerOrderId") Integer customerOrderId);
}
