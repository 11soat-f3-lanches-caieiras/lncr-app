package br.com.tp.lanchescaieiras.fooditem.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.fooditem.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface FoodItemController {

    ResponseEntity<FoodItemResponse> createFoodItem(@RequestBody FoodItem foodItem);

    ResponseEntity<FoodItemListResponse> getAllFoodItems(Optional<Integer> _limit, Optional<String> category);

    ResponseEntity<FoodItemResponse> getFoodItemById(@PathVariable Integer id);

    ResponseEntity<FoodItemResponse> partialUpdateFoodItemById(@PathVariable Integer id, @RequestBody FoodItem foodItem);

    ResponseEntity<FoodItemResponse> deleteFoodItemById(@PathVariable Integer id);

    ResponseEntity<FoodItemImageResponse> getImageData(@PathVariable Integer id);

    ResponseEntity<FoodItemImageResponse> updateImageById(@PathVariable Integer id, @RequestBody FoodItemImage foodItemImage);
}
