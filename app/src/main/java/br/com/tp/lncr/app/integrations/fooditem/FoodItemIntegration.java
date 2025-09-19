package br.com.tp.lncr.app.integrations.fooditem;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;

import java.util.List;

public interface FoodItemIntegration {

    List<CustomerOrderFoodItemDTO> getFoodItemDetailList(List<Integer> foodItemIdList);

}
