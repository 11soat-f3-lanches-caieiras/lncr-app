package br.com.tp.lanchescaieiras._external.integrations.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;

public interface FoodItemIntegration {

    CustomerOrderFoodItemDTO getFoodItemDetailsFromCustomerOrder(Integer foodItemId);

}
