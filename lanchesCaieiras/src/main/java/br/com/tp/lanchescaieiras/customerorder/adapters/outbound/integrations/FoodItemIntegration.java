package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations;

import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;

public interface FoodItemIntegration {

    CustomerOrderFoodItem getFoodItemsDetails(Integer foodItemId);


}
