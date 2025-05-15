package br.com.tp.lanchescaieiras.fooditem.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.fooditem.application.services.FoodItemServicesImpl;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodItems")
public class FoodItemControllerImpl implements FoodItemController {

    public final FoodItemServicesImpl foodItemServices;

    public FoodItemControllerImpl(FoodItemServicesImpl foodItemServices) {
        this.foodItemServices = foodItemServices;
    }

    @Override
    @PostMapping
    public FoodItemResponse createFoodItem(@RequestBody FoodItem foodItem) {

        this.foodItemServices.createFoodItem(foodItem);
        return new FoodItemResponse();
    }
}
