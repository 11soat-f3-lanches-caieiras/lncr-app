package br.com.tp.lncr.external.integrations.fooditem;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;

import java.util.List;

public interface FoodItemIntegration {

    List<CustomerOrderFoodItemDTO> getFoodItemDetailList(List<Integer> foodItemIdList);

}
