package br.com.tp.lanchescaieiras.kitchenorder.application.mappers;

import br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.entities.JpaKitchenOrderEntity;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrder;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = {KitchenOrderStatus.class})
public interface KitchenOrderMapper {
    @Mappings({
            @Mapping(target = "id", source = "jpaKitchenOrderEntity.id"),
            @Mapping(target = "customerOrderId", source = "jpaKitchenOrderEntity.customerOrderId"),
            @Mapping(target = "status", expression = "java(KitchenOrderStatus.fromId(jpaKitchenOrderEntity.getStatusId()).getDescription())"),
            @Mapping(target = "foodItems", ignore = true)})
    KitchenOrder jpatoDomain(JpaKitchenOrderEntity jpaKitchenOrderEntity);

    @Mappings({
            @Mapping(target = "id", source = "kitchenOrder.id"),
            @Mapping(target = "customerOrderId", source = "kitchenOrder.customerOrderId"),
            @Mapping(target = "statusId", expression = "java(KitchenOrderStatus.fromDescription(kitchenOrder.getStatus()).getId())"),})
    JpaKitchenOrderEntity domainToJpa(KitchenOrder kitchenOrder);


}
