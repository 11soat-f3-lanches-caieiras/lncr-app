package br.com.tp.lanchescaieiras._external.datasources.postgres.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaCustomerPostgresRepository extends JpaRepository<JpaCustomerPostgresEntity, Integer> {

    @Query(value = "SELECT * FROM customer WHERE document_number = :documentNumber", nativeQuery = true)
    Optional<JpaCustomerPostgresEntity> findByDocumentNumber(@Param("documentNumber") String documentNumber);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM customer WHERE document_number = :documentNumber", nativeQuery = true)
    Boolean existsByDocumentNumber(@Param("documentNumber") String documentNumber);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM customer WHERE email = :email", nativeQuery = true)
    Boolean existsByEmail(@Param("email") String documentNumber);

}
