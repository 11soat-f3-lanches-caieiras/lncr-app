package br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderStatus;
import org.springframework.stereotype.Component;

@Component
public class JpaCustomerOrderMapper {

    public CustomerOrderDTO jpaCustomerOrderToDTO(JpaCustomerOrderEntity entity) {
        if (entity == null) return null;
        CustomerOrderDTO dto = new CustomerOrderDTO();
        dto.setId(entity.getId());
        dto.setTotalCost(entity.getTotalCost());
        dto.setStatus(CustomerOrderStatus.fromId(entity.getStatusId()).getDescription());
        dto.setCustomer(new CustomerOrderCustomerDTO(entity.getCustomerId(), entity.getCustomerName()));
        dto.set_created(entity.getCreated());
        dto.set_updated(entity.getUpdated());
        return dto;
    }

    public CustomerOrderFoodItemDTO jpaCustomerOrderFoodItemToDTO(JpaCustomerOrderFoodItemEntity entity) {
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

    public JpaCustomerOrderEntity customerOrderDtoToJpa(CustomerOrderDTO dto) {
        if (dto == null) return null;
        JpaCustomerOrderEntity entity = new JpaCustomerOrderEntity();
        entity.setId(dto.getId());
        entity.setTotalCost(dto.getTotalCost());
        entity.setStatusId(CustomerOrderStatus.fromDescription(dto.getStatus()).getId());
        entity.setCreated(dto.get_created());
        entity.setUpdated(dto.get_updated());
        if (dto.getCustomer() != null){
            entity.setCustomerId(dto.getCustomer().getId());
            entity.setCustomerName(dto.getCustomer().getName());
        }
        return entity;
    }

    public JpaCustomerOrderFoodItemEntity customerOrderFoodItemDtoToJpa(CustomerOrderFoodItemDTO dto) {
        if (dto == null) return null;
        JpaCustomerOrderFoodItemEntity entity = new JpaCustomerOrderFoodItemEntity();
        entity.setOrderId(dto.getOrderId());
        entity.setFoodItemId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setNotes(dto.getNotes());
        return entity;
    }
}
