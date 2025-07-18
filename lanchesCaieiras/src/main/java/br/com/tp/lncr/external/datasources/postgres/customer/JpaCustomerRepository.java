package br.com.tp.lncr.external.datasources.postgres.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface JpaCustomerRepository extends JpaRepository<JpaCustomerEntity, Integer> {

    @Query(value = "SELECT * FROM customer WHERE document_number = :documentNumber", nativeQuery = true)
    Optional<JpaCustomerEntity> findByDocumentNumber(@Param("documentNumber") String documentNumber);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM customer WHERE document_number = :documentNumber", nativeQuery = true)
    Boolean existsByDocumentNumber(@Param("documentNumber") String documentNumber);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM customer WHERE email = :email", nativeQuery = true)
    Boolean existsByEmail(@Param("email") String documentNumber);

    @Query(value = "SELECT * FROM customer WHERE id in(:customerIdList)", nativeQuery = true)
    List<JpaCustomerEntity> findByCustomerIdList(@Param("customerIdList") List<Integer> customerIdList);
}
