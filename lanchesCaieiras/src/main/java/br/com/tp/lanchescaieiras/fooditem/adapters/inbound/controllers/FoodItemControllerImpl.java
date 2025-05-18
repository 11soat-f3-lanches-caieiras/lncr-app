package br.com.tp.lanchescaieiras.fooditem.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.fooditem.domain.*;
import br.com.tp.lanchescaieiras.fooditem.application.services.FoodItemServicesImpl;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.config.FoodItemImageConfig;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foodItems")
public class FoodItemControllerImpl implements FoodItemController {


    public final FoodItemImageConfig imageConfig;
    public final FoodItemConfig foodItemConfig;
    public final FoodItemServicesImpl foodItemServices;

    public FoodItemControllerImpl(FoodItemServicesImpl foodItemServices, FoodItemImageConfig imageConfig, FoodItemConfig foodItemConfig) {
        this.foodItemServices = foodItemServices;
        this.imageConfig = imageConfig;
        this.foodItemConfig = foodItemConfig;
    }

    @Override
    @PostMapping
    public ResponseEntity<FoodItemResponse> createFoodItem(@RequestBody FoodItem foodItem) {
        validateImages(foodItem); //Valida se as imagens estão com tamanho e extensão corretas
        invalidateImages(foodItem); //Remove as os dados das imagens são inválidas
        foodItem = this.foodItemServices.createFoodItem(foodItem);
        FoodItem createdFoodItem = new FoodItem(foodItem.getId(), null,null,null,null,foodItem.getImages());
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", foodItemConfig.getLocationPrefix()+"/"+createdFoodItem.getId())
                .body(new FoodItemResponse(createdFoodItem));
    }

    @Override
    @GetMapping
    public ResponseEntity<FoodItemListResponse> getAllFoodItems(Optional<Integer> _limit, Optional<String> category) {
        validadeAllFoodItemCategoryFilter(_limit, category);
        return new ResponseEntity<>(new FoodItemListResponse(this.foodItemServices.getAllFoodItems(_limit.orElse(10), category.orElse(null))), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<FoodItemResponse> getFoodItemById(@PathVariable("id") Integer id) {
        Optional<FoodItem> foodItem = this.foodItemServices.getFoodItem(id);
        return new ResponseEntity<>(new FoodItemResponse(foodItem.get()), HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<FoodItemResponse> partialUpdateFoodItemById(@PathVariable Integer id, @RequestBody FoodItem foodItem){
        FoodItem  updatedFoodItem = this.foodItemServices.partialUpdateFoodItemById(id, foodItem);
        return new ResponseEntity<>(new FoodItemResponse(updatedFoodItem), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<FoodItemResponse> deleteFoodItemById(Integer id) {
        this.foodItemServices.deleteFoodItemById(id);
        return new ResponseEntity<>(new FoodItemResponse(null),HttpStatus.OK);
    }

    @Override
    @GetMapping("/image/{id}")
    public ResponseEntity<FoodItemImageDataResponse> getImageData(Integer id) {
        FoodItemImage foodItemImage = this.foodItemServices.getImageData(id);
        return new ResponseEntity<>(new FoodItemImageDataResponse(
                foodItemImage.get_data(), foodItemImage.getFileName()),
                HttpStatus.OK);
    }

    @Override
    @PutMapping("/image/{id}")
    public ResponseEntity<FoodItemImageDataResponse> updateImageById(Integer id, FoodItemImage foodItemImage) {
        validateImage(foodItemImage);
        foodItemImage = this.foodItemServices.updateImageById(id, foodItemImage);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Location",    imageConfig.getLocationPrefix()+"/"+ id)
                .body(new FoodItemImageDataResponse());
    }


    private void validateImages(FoodItem foodItem) {
        for (FoodItemImage foodItemImage : foodItem.getImages()) {
            String fileExtention = foodItemImage.validateImage(foodItemImage._data, imageConfig.getExtensions(), imageConfig.getMaxSize());
            foodItemImage.setFileExtension(fileExtention);
        }
    }

    private void validateImage(FoodItemImage foodItemImage){
        FoodItem foodItem = new FoodItem();
        foodItem.setImages(List.of(foodItemImage));
        validateImages(foodItem);
        invalidateImages(foodItem);
        if (foodItemImage.get_data() == null) {
            throw new FoodItemException(foodItemImage.getFileExtension(), 404);
        }
    }

    private void invalidateImages(FoodItem foodItem) {
        for (FoodItemImage foodItemImage : foodItem.getImages()) {
            if (!imageConfig.getExtensions().keySet().toString().contains(foodItemImage.getFileExtension())) {
                foodItemImage._data = null;
            }
        }
    }
    private void validadeAllFoodItemCategoryFilter(Optional<Integer> _limit, Optional<String> category) {
        validateLimitFilter(_limit);
        validadeCategoryFilter(category);;
    }

    private boolean validateLimitFilter(Optional<Integer> _limit) {
        if (_limit.isPresent() && (_limit.get() <= 0 || _limit.get() > 50)) {
            throw new IllegalArgumentException("Limite deve ser maior que 0 e menor ou igual a 50");
        }
        return true;
    }

    private boolean validadeCategoryFilter(Optional<String> category) {
        if (category.isPresent()) {
            try {
                FoodItemCategory foodItemCategory = FoodItemCategory.valueOf(category.get().toUpperCase());
                return true;
            } catch (IllegalArgumentException e) {
                throw new FoodItemException("Categoria " + category.get() + " inválida", 404);
            }
        }
        else{
            return true;
        }
    }


}
