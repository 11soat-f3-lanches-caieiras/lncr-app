package br.com.tp.lanchescaieiras.fooditem.adapters;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class FoodItemPresenter {

   public ResponseEntity<Response<FoodItemDTO>> created(FoodItemDTO foodItemDTO, FoodItemConfig foodItemConfig) {
       String foodItemLocation = foodItemConfig.getLocationPrefix() + "/" + foodItemDTO.getId();
       String foodItemImagePrefixLocation = foodItemConfig.getImage().getLocationPrefix();
       foodItemDTO = formatFoodItemDTO(foodItemDTO,foodItemImagePrefixLocation);
       return responseFoodItemCreated(foodItemDTO,foodItemLocation);
    }

    public ResponseEntity<ResponseList<FoodItemDTO>> getAll(List<FoodItemDTO> foodItemListDTO, FoodItemConfig foodItemConfig) {
        return responseListOK(formatFoodItemDTOList(foodItemListDTO,foodItemConfig.getImage().getLocationPrefix()));
    }

    public ResponseEntity<Response<FoodItemDTO>> getById(FoodItemDTO foodItemDTO, FoodItemConfig foodItemConfig) {
      return responseFoodItemOK(formatFoodItemDTO(foodItemDTO,foodItemConfig.getImage().getLocationPrefix()));
    }

    public ResponseEntity<Response<FoodItemDTO>> patialUpdateById(FoodItemDTO foodItemDTO, FoodItemConfig foodItemConfig) {
      return responseFoodItemOK(formatFoodItemDTO(foodItemDTO,foodItemConfig.getImage().getLocationPrefix()));
    }

    private List<FoodItemDTO> formatFoodItemDTOList(List<FoodItemDTO> foodItemListDTO, String foodItemLocation){
        foodItemListDTO.forEach(foodItemDto -> {
           formatFoodItemDTO(foodItemDto,foodItemLocation);
        });
        return foodItemListDTO;
    }

    private FoodItemDTO formatFoodItemDTO(FoodItemDTO foodItemDTO, String imgageLocationPrefix){
        if (foodItemDTO.getImages() != null) {
            foodItemDTO.getImages().forEach(img -> {
                if (img.getId() != null && img.getImageError() == null) {
                    img.setFoodItemId(foodItemDTO.getId());
                    Integer id = img.getId();
                    String location = imgageLocationPrefix + "/" + id;
                    img.setId(id);
                    img.setLocation(location);
                }
                img.setFoodItemId(null);
                img.set_data(null);
                img.setFileName(null);
                img.setFileExtension(null);
            });
        }
        return foodItemDTO;
    }


    public <T> ResponseEntity<Response<T>> foodItemPresenterResponse(T body, HttpStatus httpStatus, HttpHeaders httpHeaders){
        return ResponseEntity.status(httpStatus)
                .headers(httpHeaders)
                .body(new Response<>(body));
    }

    public <T> ResponseEntity<Response<T>> responseFoodItemOK(T body) {
        return foodItemPresenterResponse(body,HttpStatus.OK,null);
    }

    public <T> ResponseEntity<Response<T>> responseFoodItemCreated(T body, String location){
       HttpHeaders headers = new HttpHeaders();
       headers.add("Location",location);
       return foodItemPresenterResponse(body,HttpStatus.CREATED, headers);
    }

    public <T> ResponseEntity<ResponseList<T>> responseListOK(List<T> body) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseList<>(body));
    }




}
