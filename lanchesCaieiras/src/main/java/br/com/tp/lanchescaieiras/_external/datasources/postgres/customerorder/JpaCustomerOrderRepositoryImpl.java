package br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder;


import br.com.tp.lanchescaieiras._core.applications.customerorder.mappers.CustomerOrderMapper;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderRepositoy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaCustomerOrderRepositoryImpl implements CustomerOrderRepositoy {

    private final JpaCustomerOrderRepository jpaCustomerOrderRepository;
    private final CustomerOrderMapper customerOrderMapper;

    public JpaCustomerOrderRepositoryImpl(@Lazy JpaCustomerOrderRepository jpaCustomerOrderRepository, CustomerOrderMapper customerOrderMapper) {
        this.jpaCustomerOrderRepository = jpaCustomerOrderRepository;
        this.customerOrderMapper = customerOrderMapper;
    }

    @Override
    public CustomerOrder save(CustomerOrder customerOrder) {
        JpaCustomerOrderEntity jpaCustomerOrderEntity = customerOrderMapper.domainToJpa(customerOrder);
        return customerOrderMapper.jpatoDomain(this.jpaCustomerOrderRepository.save(jpaCustomerOrderEntity));
    }

    @Override
    public CustomerOrder findById(Integer id) {
        JpaCustomerOrderEntity jpaCustomerOrderEntity = this.jpaCustomerOrderRepository.findById(id).orElse(null);
        if (jpaCustomerOrderEntity != null) {
            return customerOrderMapper.jpatoDomain(jpaCustomerOrderEntity);
        }
        return null;
    }

    @Override
    public List<CustomerOrder> findByStatusId(Integer statusId) {
        List<JpaCustomerOrderEntity> jpaCustomerOrderEntities = this.jpaCustomerOrderRepository.findByStatusId(statusId);
        return jpaCustomerOrderEntities.stream()
                .map(customerOrderMapper::jpatoDomain)
                .toList();
    }

    @Override
    public CustomerOrder updateStatusById(Integer id, Integer statusId) {
        JpaCustomerOrderEntity jpaCustomerOrderEntity = this.jpaCustomerOrderRepository.findById(id).orElse(null);
        if (jpaCustomerOrderEntity != null) {
            jpaCustomerOrderEntity.setStatusId(statusId);
            jpaCustomerOrderEntity = this.jpaCustomerOrderRepository.save(jpaCustomerOrderEntity);
            return customerOrderMapper.jpatoDomain(jpaCustomerOrderEntity);
        }
        return null;
    }
}
