package br.com.tp.lanchescaieiras.customer.external.datasource.repositories.interfaces;

import br.com.tp.lanchescaieiras.customer.external.datasource.entities.JpaCustomerEntity;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@EnableJpaRepositories
public interface JpaCustomerRepository extends JpaRepository<JpaCustomerEntity, Integer> {

    Customer save(Customer customer);

    @Query(value = "SELECT * FROM customer WHERE document_number = :documentNumber", nativeQuery = true)
    Optional<JpaCustomerEntity> findByDocumentNumber(@Param("documentNumber") String documentNumber);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM customer WHERE document_number = :documentNumber", nativeQuery = true)
    Boolean existsByDocumentNumber(@Param("documentNumber") String documentNumber);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM customer WHERE email = :email", nativeQuery = true)
    Boolean existsByEmail(@Param("email") String documentNumber);

}
