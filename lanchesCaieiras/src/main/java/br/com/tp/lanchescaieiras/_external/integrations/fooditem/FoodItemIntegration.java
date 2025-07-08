package br.com.tp.lanchescaieiras._external.integrations.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;

import java.util.List;

public interface FoodItemIntegration {

    List<CustomerOrderFoodItemDTO> getFoodItemDetailList(List<Integer> foodItemIdList);

}
