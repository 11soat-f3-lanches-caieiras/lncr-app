package br.com.tp.lanchescaieiras._core.adapters.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderCustomer;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItem;

import java.util.stream.Collectors;

public class CustomerOrderMapper {
    public  CustomerOrderDTO customerOrderToDTO(CustomerOrder order) {
        if (order == null) return null;
        CustomerOrderDTO dto = new CustomerOrderDTO();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setTotalCost(order.getTotalCost());
        dto.set_created(order.get_created());
        dto.set_updated(order.get_updated());
        if (order.getCustomer() != null) {
            dto.setCustomer(customerInOrderToDTO(order.getCustomer()));
        }
        if (order.getFoodItems() != null) {
            dto.setFoodItems(order.getFoodItems().stream().map(this::foodItemInOrderToDTO).collect(Collectors.toList()));
        }
        return dto;
    }

    public CustomerOrder customerOrderToDomain(CustomerOrderDTO dto) {
        if (dto == null) return null;
        CustomerOrder order = new CustomerOrder();
        order.setId(dto.getId());
        order.setStatus(dto.getStatus());
        order.set_created(dto.get_created());
        order.set_updated(dto.get_updated());
        if (dto.getCustomer() != null) {
            order.setCustomer(customerInOrderToDomain(dto.getCustomer()));
        }
        if (dto.getFoodItems() != null) {
            order.setFoodItems(dto.getFoodItems().stream().map(this::foodItemInOrderToDomain).collect(Collectors.toList()));
            order.setTotalCost();
        }
        if (dto.getTotalCost() != null || order.getTotalCost() != null){
            order.setTotalCost(dto.getTotalCost());
        }
        return order;
    }

    public CustomerOrderCustomerDTO customerInOrderToDTO(CustomerOrderCustomer customer) {
        if (customer == null) return null;
        CustomerOrderCustomerDTO dto = new CustomerOrderCustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        return dto;
    }

    public CustomerOrderCustomer customerInOrderToDomain(CustomerOrderCustomerDTO dto) {
        if (dto == null) return null;
        CustomerOrderCustomer customer = new CustomerOrderCustomer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        return customer;
    }

    public CustomerOrderFoodItemDTO foodItemInOrderToDTO(CustomerOrderFoodItem item) {
        if (item == null) return null;
        CustomerOrderFoodItemDTO dto = new CustomerOrderFoodItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setPrice(item.getPrice());
        dto.setNotes(item.getNotes());
        return dto;
    }

    public CustomerOrderFoodItem foodItemInOrderToDomain(CustomerOrderFoodItemDTO dto) {
        if (dto == null) return null;
        CustomerOrderFoodItem item = new CustomerOrderFoodItem();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setNotes(dto.getNotes());
        return item;
    }
}
