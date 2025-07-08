package br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaCustomerOrderFoodItemPostgresRepositoryImpl {

    public final JpaCustomerOrderFoodItemPostgresRepository jpaCustomerOrderFoodItemPostgresRepository;
    public final JpaCustomerOrderPostgresMapper jpaCustomerOrderPostgresMapper;

    public JpaCustomerOrderFoodItemPostgresRepositoryImpl(@Lazy JpaCustomerOrderFoodItemPostgresRepository jpaCustomerOrderFoodItemPostgresRepository, JpaCustomerOrderPostgresMapper jpaCustomerOrderPostgresMapper) {
        this.jpaCustomerOrderFoodItemPostgresRepository = jpaCustomerOrderFoodItemPostgresRepository;
        this.jpaCustomerOrderPostgresMapper = jpaCustomerOrderPostgresMapper;
    }

    public List<CustomerOrderFoodItemDTO> saveAll(List<CustomerOrderFoodItemDTO> foodItems) {
        List<JpaCustomerOrderFoodItemPostgresEntity> jpaItemList = foodItems.stream().map(jpaCustomerOrderPostgresMapper::customerOrderFoodItemDtoToJpa).toList();
        jpaItemList = this.jpaCustomerOrderFoodItemPostgresRepository.saveAll(jpaItemList);
        return jpaItemList.stream().map(jpaCustomerOrderPostgresMapper::jpaCustomerOrderFoodItemToDTO).toList();
    }

    public List<CustomerOrderFoodItemDTO> findByCustomerOrderId(Integer customerOrderId) {
        return this.jpaCustomerOrderFoodItemPostgresRepository.findByCustomerOrderId(customerOrderId)
                .stream()
                .map(jpaCustomerOrderPostgresMapper::jpaCustomerOrderFoodItemToDTO)
                .toList();
    }

    public List<CustomerOrderFoodItemDTO> findByCustomerOrderIdList(List<Integer> customerOrdersIdsList) {
        List<JpaCustomerOrderFoodItemPostgresEntity> jpaCustomerOrderFoodItemList = this.jpaCustomerOrderFoodItemPostgresRepository.findByCustomerOrderIdList(customerOrdersIdsList);
        return jpaCustomerOrderFoodItemList.stream().map(jpaCustomerOrderPostgresMapper::jpaCustomerOrderFoodItemToDTO).toList();
    }
}
