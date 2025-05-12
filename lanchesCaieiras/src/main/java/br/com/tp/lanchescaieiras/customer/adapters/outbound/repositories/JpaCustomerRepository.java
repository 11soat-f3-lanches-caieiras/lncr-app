package br.com.tp.lanchescaieiras.customer.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.entities.JpaCustomerEntity;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

@EnableJpaRepositories
public interface JpaCustomerRepository extends JpaRepository<JpaCustomerEntity,Integer> {


    @Query(value = "SELECT * FROM customer WHERE documentNumber = :documentNumber", nativeQuery = true)
    Customer  findByDocumentNumber(@Param("documentNumber") String documentNumber);

    @Query(value = "SELECT * FROM customer WHERE documentNumber = :documentNumber", nativeQuery = true)
    boolean  existsByDocumentNumber(@Param("documentNumber") String documentNumber);

    @Query(value = "SELECT * FROM customer WHERE email = :email", nativeQuery = true)
    boolean  existsByEmail(@Param("email") String documentNumber);


    Customer save(Customer customer);
    //void findByDocumentNumber(Integer id, JpaCustomerEntity jpaCustomerEntity);
    //void updateById(Integer id, JpaCustomerEntity jpaCustomerEntity);
}
