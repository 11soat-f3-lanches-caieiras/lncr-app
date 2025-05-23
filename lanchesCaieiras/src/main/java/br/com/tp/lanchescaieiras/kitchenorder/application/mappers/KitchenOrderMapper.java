package br.com.tp.lanchescaieiras.kitchenorder.application.mappers;

import br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.entities.JpaKitchenOrderEntity;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrder;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports =  {KitchenOrderStatus.class})
public interface KitchenOrderMapper {
    @Mappings({
        @Mapping(target = "id", source = "jpaKitchenOrderEntity.id"),
        @Mapping(target = "customer.id", source = "jpaKitchenOrderEntity.customerId"),
        @Mapping(target = "status", expression = "java(KitchenOrderStatus.fromId(jpaKitchenOrderEntity.getStatusId()).getDescription())"),
        @Mapping(target = "totalCost", source = "jpaKitchenOrderEntity.totalCost"),
        @Mapping(target = "customer", ignore = true),
        @Mapping(target = "foodItems", ignore = true)})
    KitchenOrder jpatoDomain(JpaKitchenOrderEntity jpaKitchenOrderEntity);

    @Mappings({
        @Mapping(target = "id", source = "kitchenOrder.id"),
        @Mapping(target = "totalCost", source = "kitchenOrder.totalCost"),
        @Mapping(target = "customerId", source = "kitchenOrder.customer.id"),
        @Mapping(target = "statusId", expression = "java(KitchenOrderStatus.fromDescription(kitchenOrder.getStatus()).getId())"),})
    JpaKitchenOrderEntity domainToJpa(KitchenOrder kitchenOrder);


}
