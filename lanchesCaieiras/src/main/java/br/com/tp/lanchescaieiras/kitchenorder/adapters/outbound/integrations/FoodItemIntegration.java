package br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.integrations;

import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderFoodItem;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface FoodItemIntegration {

    KitchenOrderFoodItem getFoodItemsDetails(Integer foodItemId) throws JsonProcessingException;
}
