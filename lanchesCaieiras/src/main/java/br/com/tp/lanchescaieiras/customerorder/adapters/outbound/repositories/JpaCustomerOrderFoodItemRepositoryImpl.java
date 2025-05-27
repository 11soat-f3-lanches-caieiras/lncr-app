package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.entities.JpaCustomerOrderFoodItemEntity;
import br.com.tp.lanchescaieiras.customerorder.application.mappers.CustomerOrderFoodItemMapper;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItemRepository;
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
