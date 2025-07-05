package br.com.tp.lanchescaieiras._external.integrations.fooditem;

import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItem;

public interface FoodItemIntegration {

    CustomerOrderFoodItem getFoodItemsDetails(Integer foodItemId);


}
