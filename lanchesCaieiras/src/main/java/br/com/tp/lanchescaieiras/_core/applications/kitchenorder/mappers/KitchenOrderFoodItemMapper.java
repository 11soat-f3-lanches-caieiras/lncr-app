package br.com.tp.lanchescaieiras._core.applications.kitchenorder.mappers;

import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderFoodItem;
import br.com.tp.lanchescaieiras._external.datasources.postgres.kitchenorder.JpaKitchenOrderFoodItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface KitchenOrderFoodItemMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "kitchenOrderId", source = "kitchenOrderId"),
            @Mapping(target = "name", source = "kitchenOrderFoodItem.name"),
            @Mapping(target = "description", source = "kitchenOrderFoodItem.description"),
            @Mapping(target = "notes", source = "kitchenOrderFoodItem.notes")})
    JpaKitchenOrderFoodItemEntity domainToJpa(KitchenOrderFoodItem kitchenOrderFoodItem, Integer kitchenOrderId);

    @Mappings({
            @Mapping(target = "id", source = "jpaKitchenOrderFoodItemEntity.id"),
            @Mapping(target = "description", source = "jpaKitchenOrderFoodItemEntity.description"),
            @Mapping(target = "name", source = "jpaKitchenOrderFoodItemEntity.name"),
            @Mapping(target = "notes", source = "jpaKitchenOrderFoodItemEntity.notes")
    })
    KitchenOrderFoodItem jpaToDomain(JpaKitchenOrderFoodItemEntity jpaKitchenOrderFoodItemEntity);

}
