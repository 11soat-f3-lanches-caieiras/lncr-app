package br.com.tp.lanchescaieiras._core.applications.customerorder.mappers;

import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder.JpaCustomerOrderFoodItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface CustomerOrderFoodItemMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "orderId", source = "customerOrderId"),
            @Mapping(target = "foodItemId", source = "customerOrderFoodItem.id"),
            @Mapping(target = "price", source = "customerOrderFoodItem.price"),
            @Mapping(target = "notes", source = "customerOrderFoodItem.notes")})
    JpaCustomerOrderFoodItemEntity domainToJpa(CustomerOrderFoodItem customerOrderFoodItem, Integer customerOrderId);

    @Mappings({
            @Mapping(target = "id", source = "jpaCustomerOrderFoodItemEntity.foodItemId"),
            @Mapping(target = "description", ignore = true),
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "price", source = "jpaCustomerOrderFoodItemEntity.price"),
            @Mapping(target = "notes", source = "jpaCustomerOrderFoodItemEntity.notes")
    })
    CustomerOrderFoodItem jpaToDomain(JpaCustomerOrderFoodItemEntity jpaCustomerOrderFoodItemEntity);

}
