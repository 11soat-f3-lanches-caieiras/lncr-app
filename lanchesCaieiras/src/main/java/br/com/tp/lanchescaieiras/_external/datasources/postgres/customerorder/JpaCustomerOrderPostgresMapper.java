package br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderStatus;

public class JpaCustomerOrderPostgresMapper {

    public CustomerOrderDTO jpaCustomerOrderToDTO(JpaCustomerOrderPostgresEntity entity) {
        if (entity == null) return null;
        CustomerOrderDTO dto = new CustomerOrderDTO();
        dto.setId(entity.getId());
        dto.setTotalCost(entity.getTotalCost());
        dto.setStatus(CustomerOrderStatus.fromId(entity.getStatusId()).getDescription());
        dto.setCustomer(new CustomerOrderCustomerDTO(entity.getCustomerId(), entity.getCustomerName()));
        dto.set_created(entity.getCreated());
        return dto;
    }

    public CustomerOrderFoodItemDTO jpaCustomerOrderFoodItemToDTO(JpaCustomerOrderFoodItemPostgresEntity entity) {
        if (entity == null) return null;
        return new CustomerOrderFoodItemDTO(
            entity.getFoodItemId(),
            entity.getOrderId(),
            entity.getName(),
            entity.getDescription(),
            entity.getPrice(),
            entity.getNotes()
        );
    }

    public JpaCustomerOrderPostgresEntity customerOrderDtoToJpa(CustomerOrderDTO dto) {
        if (dto == null) return null;
        JpaCustomerOrderPostgresEntity entity = new JpaCustomerOrderPostgresEntity();
        entity.setId(dto.getId());
        entity.setTotalCost(dto.getTotalCost());
        entity.setStatusId(CustomerOrderStatus.fromDescription(dto.getStatus()).getId());
        if (dto.getCustomer() != null){
            entity.setCustomerId(dto.getCustomer().getId());
            entity.setCustomerName(dto.getCustomer().getName());
        }
        return entity;
    }

    public JpaCustomerOrderFoodItemPostgresEntity customerOrderFoodItemDtoToJpa(CustomerOrderFoodItemDTO dto) {
        if (dto == null) return null;
        JpaCustomerOrderFoodItemPostgresEntity entity = new JpaCustomerOrderFoodItemPostgresEntity();
        entity.setOrderId(dto.getOrderId());
        entity.setFoodItemId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setNotes(dto.getNotes());
        return entity;
    }
}
