package br.com.tp.lanchescaieiras._external.datasources.postgres.kitchenorder;


import br.com.tp.lanchescaieiras._core.applications.kitchenorder.mappers.KitchenOrderMapper;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrder;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderRepositoy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaKitchenOrderRepositoryImpl implements KitchenOrderRepositoy {

    private final JpaKitchenOrderRepository jpaKitchenOrderRepository;
    private final KitchenOrderMapper kitchenOrderMapper;

    public JpaKitchenOrderRepositoryImpl(@Lazy JpaKitchenOrderRepository jpaKitchenOrderRepository, KitchenOrderMapper kitchenOrderMapper) {
        this.jpaKitchenOrderRepository = jpaKitchenOrderRepository;
        this.kitchenOrderMapper = kitchenOrderMapper;
    }

    @Override
    public KitchenOrder save(KitchenOrder kitchenOrder) {
        JpaKitchenOrderEntity jpaKitchenOrderEntity = kitchenOrderMapper.domainToJpa(kitchenOrder);
        return kitchenOrderMapper.jpatoDomain(this.jpaKitchenOrderRepository.save(jpaKitchenOrderEntity));
    }

    @Override
    public KitchenOrder findById(Integer id) {
        JpaKitchenOrderEntity jpaKitchenOrderEntity = this.jpaKitchenOrderRepository.findById(id).orElse(null);
        if (jpaKitchenOrderEntity != null) {
            return kitchenOrderMapper.jpatoDomain(jpaKitchenOrderEntity);
        }
        return null;
    }

    @Override
    public List<KitchenOrder> findByStatusId(Integer statusId) {
        List<JpaKitchenOrderEntity> jpaKitchenOrderEntities = this.jpaKitchenOrderRepository.findByStatusId(statusId);
        return jpaKitchenOrderEntities.stream()
                .map(kitchenOrderMapper::jpatoDomain)
                .toList();
    }

    @Override
    public KitchenOrder updateStatusById(Integer id, Integer statusId) {
        JpaKitchenOrderEntity jpaKitchenOrderEntity = this.jpaKitchenOrderRepository.findById(id).orElse(null);
        if (jpaKitchenOrderEntity != null) {
            jpaKitchenOrderEntity.setStatusId(statusId);
            jpaKitchenOrderEntity = this.jpaKitchenOrderRepository.save(jpaKitchenOrderEntity);
            return kitchenOrderMapper.jpatoDomain(jpaKitchenOrderEntity);
        }
        return null;
    }

    @Override
    public KitchenOrder findByCustomerOrderId(Integer customerOrderId) {
        JpaKitchenOrderEntity jpaKitchenOrderEntity = this.jpaKitchenOrderRepository.findByCustomerOrderId(customerOrderId);
        return kitchenOrderMapper.jpatoDomain(jpaKitchenOrderEntity);
    }
}
