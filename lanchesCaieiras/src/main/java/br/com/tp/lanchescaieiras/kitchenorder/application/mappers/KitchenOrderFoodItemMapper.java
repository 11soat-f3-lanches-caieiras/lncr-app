package br.com.tp.lanchescaieiras.kitchenorder.application.mappers;

import br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.entities.JpaKitchenOrderFoodItemEntity;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderFoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface KitchenOrderFoodItemMapper {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "orderId", source = "kitchenOrderId"),
        @Mapping(target = "foodItemId", source = "kitchenOrderFoodItem.id"),
        @Mapping(target = "price", source = "kitchenOrderFoodItem.price"),
        @Mapping(target = "notes", source = "kitchenOrderFoodItem.notes")})
    JpaKitchenOrderFoodItemEntity domainToJpa(KitchenOrderFoodItem kitchenOrderFoodItem, Integer kitchenOrderId);

    @Mappings({
            @Mapping(target = "id", source = "jpaKitchenOrderFoodItemEntity.foodItemId"),
            @Mapping(target = "description", ignore = true),
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "price", source = "jpaKitchenOrderFoodItemEntity.price"),
            @Mapping(target = "notes", source = "jpaKitchenOrderFoodItemEntity.notes")
    })
    KitchenOrderFoodItem jpaToDomain(JpaKitchenOrderFoodItemEntity jpaKitchenOrderFoodItemEntity);

}
