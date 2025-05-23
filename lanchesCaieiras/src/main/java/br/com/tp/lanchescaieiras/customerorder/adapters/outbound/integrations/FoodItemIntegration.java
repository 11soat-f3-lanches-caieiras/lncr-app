package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations;

import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface FoodItemIntegration {

    CustomerOrderFoodItem getFoodItemsDetails(Integer foodItemId) throws JsonProcessingException;
}
