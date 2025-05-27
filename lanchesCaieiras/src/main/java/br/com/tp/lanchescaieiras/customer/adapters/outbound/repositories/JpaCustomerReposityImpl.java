package br.com.tp.lanchescaieiras.customer.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.entities.JpaCustomerEntity;
import br.com.tp.lanchescaieiras.customer.application.mappers.CustomerMapper;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import br.com.tp.lanchescaieiras.customer.domain.CustomerRepository;
import br.com.tp.lanchescaieiras.customer.infraestructure.exceptions.CustomerException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        JpaCustomerEntity jpaCustomerEntity = this.jpaCustomerRepository.save(customerMapper.domainToJpa(customer));
        return customerMapper.jpaToDomain(jpaCustomerEntity);
    }

    @Override
    public Optional<Customer> findByDocumentNumber(String documentNumber) {
        return this.jpaCustomerRepository.findByDocumentNumber(documentNumber)
                .map(customerMapper::jpaToDomain)
                .or(() -> {
                    throw new CustomerException("Cliente não encontrado com o documento: " + documentNumber, 404);
                });
    }

    @Override
    public Optional<Customer> partialUpdateById(Customer customer, Integer id) {
        return this.jpaCustomerRepository.findById(id).map(
                existingEntity ->
                {
                    if (customer.getName() != null) {
                        existingEntity.setName(customer.getName());
                    }
                    if (customer.getEmail() != null) {

                        existingEntity.setEmail(customer.getEmail());
                    }
                    if (customer.getDocumentNumber() != null) {
                        existingEntity.setDocumentNumber(customer.getDocumentNumber());
                    }
                    JpaCustomerEntity updatedEntity = this.jpaCustomerRepository.save(existingEntity);
                    return customerMapper.jpaToDomain(updatedEntity);
                });
    }

    @Override
    public void deleteById(Integer id) {
        Optional<JpaCustomerEntity> jpaCustomerEntity = this.jpaCustomerRepository.findById(id);
        if (jpaCustomerEntity.isPresent()) {
            try {
                this.jpaCustomerRepository.delete(jpaCustomerEntity.get());
            } catch (Exception e) {
                throw new CustomerException("Erro ao deletar o cliente com ID: " + id, 500);
            }
        } else {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
    }


    @Override
    public Optional<Customer> findById(Integer id) {
        return this.jpaCustomerRepository.findById(id)
                .map(customerMapper::jpaToDomain);
    }

    @Override
    public List<Customer> findAll(Integer _limit) {
        return this.jpaCustomerRepository.findAll(Pageable.ofSize(_limit))
                .stream()
                .map(customerMapper::jpaToDomain)
                .toList();
    }

    public Boolean existsByDocumentNumber(String documentNumber) {
        return this.jpaCustomerRepository.existsByDocumentNumber(documentNumber);
    }

    public Boolean existsByEmail(String email) {
        return this.jpaCustomerRepository.existsByEmail(email);
    }
}
