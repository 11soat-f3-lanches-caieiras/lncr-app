package br.com.tp.lncr.app.integrations;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import org.springframework.stereotype.Component;

@Component
public class IntegrationMapper {

    public CustomerOrderFoodItemDTO toCustomerOrderFoodItemDTO(FoodItemDTO foodItemDTO){
        if (foodItemDTO == null) return  null;
        CustomerOrderFoodItemDTO customerOrderFoodItemDTO = new CustomerOrderFoodItemDTO();
        customerOrderFoodItemDTO.setId(foodItemDTO.getId());
        customerOrderFoodItemDTO.setDescription(foodItemDTO.getDescription());
        customerOrderFoodItemDTO.setName(foodItemDTO.getName());
        customerOrderFoodItemDTO.setPrice(foodItemDTO.getPrice());
        return customerOrderFoodItemDTO;
    }

    public CustomerOrderCustomerDTO toCustomerOrderCustomerDTO(CustomerDTO customerDTO) {
        if (customerDTO==null) return null;
        return new CustomerOrderCustomerDTO(customerDTO.getId(), customerDTO.getName());
    }
}
