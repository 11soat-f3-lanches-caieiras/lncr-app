package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodItemListResponse {

    ResponseMetada responseMetada;
    List<FoodItem> foodItems;

    public FoodItemListResponse() {
    }

    public FoodItemListResponse(ResponseMetada responseMetada, List<FoodItem> foodItems) {
        this.responseMetada = responseMetada;
        this.foodItems = foodItems;
    }

    public FoodItemListResponse(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
        this.responseMetada = new ResponseMetada();
    }

    public ResponseMetada getResponseMetada() {
        return responseMetada;
    }

    public void setResponseMetada(ResponseMetada responseMetada) {
        this.responseMetada = responseMetada;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}
