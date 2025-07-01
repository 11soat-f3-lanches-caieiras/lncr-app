package br.com.tp.lanchescaieiras.fooditem.external.api;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemRestController;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemControllerImpl;
import br.com.tp.lanchescaieiras.fooditem.external.FoodItemDataProxy;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemImagePostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemPostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.storage.FoodItemImageStorageImpl;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodItems")
public class FoodItemRestControllerImpl implements FoodItemRestController {

    private final FoodItemConfig foodItemConfig;
    private final FoodItemControllerImpl foodItemController;
    private final JpaFoodItemPostgresDatabaseImpl jpaFoodItemDatabase;
    private final JpaFoodItemImagePostgresDatabaseImpl jpaFoodItemImageDatabase;
    private final FoodItemImageStorageImpl foodItemImageStorage;
    private final FoodItemMapper foodItemMapper;


    public FoodItemRestControllerImpl(FoodItemConfig foodItemConfig, FoodItemControllerImpl foodItemController, JpaFoodItemPostgresDatabaseImpl jpaFoodItemDatabase, JpaFoodItemImagePostgresDatabaseImpl jpaFoodItemImageDatabase, FoodItemImageStorageImpl foodItemImageStorage, FoodItemMapper foodItemMapper) {
        this.foodItemConfig = foodItemConfig;
        this.foodItemController = foodItemController;
        this.jpaFoodItemDatabase = jpaFoodItemDatabase;
        this.jpaFoodItemImageDatabase = jpaFoodItemImageDatabase;
        this.foodItemImageStorage = foodItemImageStorage;
        this.foodItemMapper = foodItemMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<Response<FoodItemDTO>> createFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDataProxy foodItemDatabase = new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
        return this.foodItemController.create(foodItemDTO, foodItemDatabase, this.foodItemConfig, this.foodItemMapper);
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseList<FoodItemDTO>> getAllFoodItems(@RequestParam(name = "_limit", required = false) Integer _limit,
                                                                     @RequestParam(name = "category", required = false) String category,
                                                                     @RequestParam(name = "includeImages", required = false) Boolean includeImages) {
        FoodItemDataProxy foodItemDatabase = new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
        return this.foodItemController.getAll(_limit, category, includeImages, foodItemDatabase, this.foodItemConfig, this.foodItemMapper);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response<FoodItemDTO>> getFoodItemById(@PathVariable(name = "id") Integer foodItemId,
                                                                 @RequestParam(name = "includeImages", required = false) Boolean includeImages) {
        FoodItemDataProxy foodItemDatabase = new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
        return this.foodItemController.getById(foodItemId,includeImages, foodItemDatabase, this.foodItemConfig, this.foodItemMapper);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Response<FoodItemDTO>> partialUpdateFoodItemById(@PathVariable Integer id, @RequestBody FoodItemDTO foodItemDTO) {
        return null;
        /*FoodItem updatedFoodItem = this.foodItemServices.partialUpdateFoodItemById(id, foodItem);
        return new ResponseEntity<>(new FoodItemResponse(updatedFoodItem), HttpStatus.OK);*/
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<FoodItemDTO>> deleteFoodItemById(@PathVariable Integer id) {
        return null;
        /*this.foodItemServices.deleteFoodItemById(id);
        return new ResponseEntity<>(new FoodItemResponse(null), HttpStatus.OK);*/
    }

    @Override
    @GetMapping("/image/{id}")
    public ResponseEntity<Response<FoodItemImageDTO>> getImageData(@PathVariable Integer id) {
        return null;
        /*FoodItemImage foodItemImage = this.foodItemServices.getImageData(id);
        return new ResponseEntity<>(new FoodItemImageDataResponse(
                foodItemImage.get_data(), foodItemImage.getFileName()),
                HttpStatus.OK);*/
    }

    @Override
    @PostMapping("/image/{foodItemId}")
    public ResponseEntity<Response<FoodItemImageDTO>> createImage(@PathVariable Integer foodItemId, @RequestBody FoodItemImageDTO foodItemImageDTO) {
        return null;
    }

    @Override
    @PutMapping("/image/{id}")
    public ResponseEntity<Response<FoodItemImageDTO>> updateImageById(@PathVariable Integer id, @RequestBody FoodItemImageDTO foodItemImageDTO) {
       return null;
        /*validateImage(foodItemImage);
        foodItemImage = this.foodItemServices.updateImageById(id, foodItemImage);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Location", imageConfig.getLocationPrefix() + "/" + id)
                .body(new FoodItemImageDataResponse());*/
    }


    /*private void validateImages(FoodItem foodItem) {
        for (FoodItemImage foodItemImage : foodItem.getImages()) {
            String fileExtention = foodItemImage.validateImage(foodItemImage._data, imageConfig.getExtensions(), imageConfig.getMaxSize());
            foodItemImage.setFileExtension(fileExtention);
        }
    }

    private void validateImage(FoodItemImage foodItemImage) {
        FoodItem foodItem = new FoodItem();
        foodItem.setImages(List.of(foodItemImage));
        validateImages(foodItem);
        invalidateImages(foodItem);
        if (foodItemImage.get_data() == null) {
            throw new FoodItemException(foodItemImage.getFileExtension(), 404);

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
        validadeCategoryFilter(category);
        ;
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
        } else {
            return true;
        }
    }

    */
}
