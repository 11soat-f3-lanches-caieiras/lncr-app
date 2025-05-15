package br.com.tp.lanchescaieiras.fooditem.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface FoodItemController {

    FoodItemResponse createFoodItem(@RequestBody FoodItem foodItem);
    
}
