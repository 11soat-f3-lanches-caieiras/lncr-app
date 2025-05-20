package br.com.tp.lanchescaieiras.customerorder.application.mappers;

import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.entities.JpaCustomerOrderFoodItemEntity;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;


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
