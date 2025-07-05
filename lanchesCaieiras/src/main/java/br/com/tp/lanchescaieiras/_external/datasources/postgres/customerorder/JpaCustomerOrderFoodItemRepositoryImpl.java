package br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder;

import br.com.tp.lanchescaieiras._core.applications.customerorder.mappers.CustomerOrderFoodItemMapper;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItemRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaCustomerOrderFoodItemRepositoryImpl implements CustomerOrderFoodItemRepository {

    public final JpaCustomerOrderFoodItemRepository jpaCustomerOrderFoodItemRepository;
    public final CustomerOrderFoodItemMapper customerOrderFoodItemMapper;

    public JpaCustomerOrderFoodItemRepositoryImpl(@Lazy JpaCustomerOrderFoodItemRepository jpaCustomerOrderFoodItemRepository, CustomerOrderFoodItemMapper customerOrderFoodItemMapper) {
        this.jpaCustomerOrderFoodItemRepository = jpaCustomerOrderFoodItemRepository;
        this.customerOrderFoodItemMapper = customerOrderFoodItemMapper;
    }

    @Override
    public CustomerOrderFoodItem save(CustomerOrderFoodItem foodItem, Integer foodItemId) {
        JpaCustomerOrderFoodItemEntity jpaCustomerOrderFoodItemEntity = customerOrderFoodItemMapper.domainToJpa(foodItem, foodItemId);
        return customerOrderFoodItemMapper.jpaToDomain(this.jpaCustomerOrderFoodItemRepository.save(jpaCustomerOrderFoodItemEntity));

    }

    @Override
    public List<CustomerOrderFoodItem> findByCustomerOrderId(Integer customerOrderId) {
        List<JpaCustomerOrderFoodItemEntity> jpaCustomerOrderFoodItemEntities = this.jpaCustomerOrderFoodItemRepository.findByCustomerOrderId(customerOrderId);
        return jpaCustomerOrderFoodItemEntities.stream()
                .map(customerOrderFoodItemMapper::jpaToDomain)
                .toList();
    }
}
