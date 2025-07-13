package br.com.tp.lanchescaieiras._external.datasources.postgres.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customer.CustomerDatabase;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCustomerReposityImpl implements CustomerDatabase {

    public final JpaCustomerRepository jpaCustomerRepository;
    public final JpaCustomerMapper jpaCustomerMapper;

    public JpaCustomerReposityImpl(JpaCustomerRepository jpaCustomerRepository, JpaCustomerMapper jpaCustomerMapper) {
        this.jpaCustomerRepository = jpaCustomerRepository;
        this.jpaCustomerMapper = jpaCustomerMapper;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDto) {
        JpaCustomerEntity jpaCustomerEntity = this.jpaCustomerRepository.save(jpaCustomerMapper.toJpaCustomerPostgresEntity(customerDto));
        return jpaCustomerMapper.toCustomerDTO(jpaCustomerEntity);
    }

    @Override
    public Optional<CustomerDTO> findById(Integer id) {
        return this.jpaCustomerRepository.findById(id)
                .map(jpaCustomerMapper::toCustomerDTO);
    }

    @Override
    public List<CustomerDTO> findAll(Integer _limit) {
        return jpaCustomerRepository.findAll(Pageable.ofSize(_limit))
                .stream()
                .map(jpaCustomerMapper::toCustomerDTO)
                .toList();
    }

    public Optional<CustomerDTO> findByDocumentNumber(String documentNumber) {
        return this.jpaCustomerRepository.findByDocumentNumber(documentNumber)
                .map(jpaCustomerMapper::toCustomerDTO);
    }

    @Override
    public void deleteById(Integer id) {
        this.jpaCustomerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDTO> findByIdList(List<Integer> customerIdList) {
        List<JpaCustomerEntity> jpaCustomerList = this.jpaCustomerRepository.findByCustomerIdList(customerIdList);
        return jpaCustomerList.stream().map(jpaCustomerMapper::toCustomerDTO).toList();

    }

    public boolean existsByDocumentNumber(String documentNumber) {
        return this.jpaCustomerRepository.existsByDocumentNumber(documentNumber);
    }

    public boolean existsByEmail(String email) {
        return this.jpaCustomerRepository.existsByEmail(email);
    }
}
