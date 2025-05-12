package br.com.tp.lanchescaieiras.customer.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.entities.JpaCustomerEntity;
import br.com.tp.lanchescaieiras.customer.mappers.CustomerMapper;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import br.com.tp.lanchescaieiras.customer.domain.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class JpaCustomerReposityImpl implements CustomerRepository {

    public final JpaCustomerRepository jpaCustomerRepository;
    public final CustomerMapper customerMapper;

    public JpaCustomerReposityImpl(JpaCustomerRepository jpaCustomerRepository, CustomerMapper customerMapper) {
        this.jpaCustomerRepository = jpaCustomerRepository;
        this.customerMapper = customerMapper;

    }

    @Override
    public Customer save(Customer customer) {
        JpaCustomerEntity jpaCustomerEntity = customerMapper.domainToJpa(customer);
        this.jpaCustomerRepository.save(jpaCustomerEntity);
        return customerMapper.jpaToDomain(jpaCustomerEntity);
    }

    @Override
    public void deletebyId(Integer id) {
        this.jpaCustomerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        Optional<JpaCustomerEntity> jpaCustomer = this.jpaCustomerRepository.findById(id);
        if (jpaCustomer.isPresent()) {
             Customer customer = jpaCustomer.map(customerMapper::jpaToDomain).orElse(null);
            return Optional.of(customer);
        } else {
            return Optional.empty();
        }
    }
    /*@Override
    public Customer findByDocumentNumber(String documentNumber) {
        this.jpaCustomerRepository.findByDocumentNumber(documentNumber);
    }*/

   /* @Override
    public List<Customer> findAll() {
        return List.of();
    }*/

    /*@Override
    public Customer updateById(Integer id, Customer customer) {
        JpaCustomerEntity jpaCustomerEntity = new JpaCustomerEntity(customer);
        this.jpaCustomerRepository.updateById(id,jpaCustomerEntity);
    }

    @Override
    public Customer updateByDocumentNumber(String documentNumber, Customer customer) {
        return null;
    }*/


}
