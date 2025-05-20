package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.repositories;


import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.entities.JpaCustomerOrderEntity;
import br.com.tp.lanchescaieiras.customerorder.application.mappers.CustomerOrderMapper;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrder;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderRepositoy;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderStatus;
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
        return null;
    }


}
