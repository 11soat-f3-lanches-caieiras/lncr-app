package br.com.tp.lanchescaieiras.customer.external.datasources.postgres;

import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.customer.CustomerDatabase;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCustomerPostgresReposityImpl implements CustomerDatabase {

    public final JpaCustomerPostgresRepository jpaCustomerRepository;
    public final JpaCustomerPostgresMapper jpaCustomerPostgresMapper;

    public JpaCustomerPostgresReposityImpl(JpaCustomerPostgresRepository jpaCustomerRepository, JpaCustomerPostgresMapper jpaCustomerPostgresMapper) {
        this.jpaCustomerRepository = jpaCustomerRepository;
        this.jpaCustomerPostgresMapper = jpaCustomerPostgresMapper;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDto) {
        JpaCustomerPostgresEntity jpaCustomerPostgresEntity = this.jpaCustomerRepository.save(jpaCustomerPostgresMapper.toJpaCustomerPostgresEntity(customerDto));
        return jpaCustomerPostgresMapper.toCustomerDTO(jpaCustomerPostgresEntity);
    }

    @Override
    public Optional<CustomerDTO> findById(Integer id) {
        return this.jpaCustomerRepository.findById(id)
                .map(jpaCustomerPostgresMapper::toCustomerDTO);
    }

    @Override
    public List<CustomerDTO> findAll(Integer _limit) {
        return jpaCustomerRepository.findAll(Pageable.ofSize(_limit))
                .stream()
                .map(jpaCustomerPostgresMapper::toCustomerDTO)
                .toList();
    }

    public Optional<CustomerDTO> findByDocumentNumber(String documentNumber) {
        return this.jpaCustomerRepository.findByDocumentNumber(documentNumber)
                 .map(jpaCustomerPostgresMapper::toCustomerDTO);
    }

    @Override
    public void deleteById(Integer id) {
        this.jpaCustomerRepository.deleteById(id);
    }
    public boolean existsByDocumentNumber(String documentNumber) {
        return this.jpaCustomerRepository.existsByDocumentNumber(documentNumber);
    }

    public boolean existsByEmail(String email) {
        return this.jpaCustomerRepository.existsByEmail(email);
    }
}
