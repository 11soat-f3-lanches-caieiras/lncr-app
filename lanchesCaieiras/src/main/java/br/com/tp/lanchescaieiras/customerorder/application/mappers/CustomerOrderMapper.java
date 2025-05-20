package br.com.tp.lanchescaieiras.customerorder.application.mappers;

import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.entities.JpaCustomerOrderEntity;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrder;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports =  {CustomerOrderStatus.class})
public interface CustomerOrderMapper {
    @Mappings({
        @Mapping(target = "id", source = "jpaCustomerOrderEntity.id"),
        @Mapping(target = "customer.id", source = "jpaCustomerOrderEntity.customerId"),
        @Mapping(target = "status", expression = "java(CustomerOrderStatus.fromId(jpaCustomerOrderEntity.getStatusId()).getDescription())"),
        @Mapping(target = "totalCost", source = "jpaCustomerOrderEntity.totalCost"),
        @Mapping(target = "customer", ignore = true),
        @Mapping(target = "foodItems", ignore = true)})
    CustomerOrder jpatoDomain(JpaCustomerOrderEntity jpaCustomerOrderEntity);

    @Mappings({
        @Mapping(target = "id", source = "customerOrder.id"),
        @Mapping(target = "totalCost", source = "customerOrder.totalCost"),
        @Mapping(target = "customerId", source = "customerOrder.customer.id"),
        @Mapping(target = "statusId", expression = "java(CustomerOrderStatus.fromDescription(customerOrder.getStatus()).getId())"),})
    JpaCustomerOrderEntity domainToJpa(CustomerOrder customerOrder);


}
