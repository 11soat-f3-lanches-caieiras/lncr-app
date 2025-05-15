package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;

public class FoodItemResponse {
    public ResponseMetada _response;
    public FoodItem foodItem;

    public FoodItemResponse(ResponseMetada _response, FoodItem foodItem) {
        this._response = _response;
        this.foodItem = foodItem;
    }

    public FoodItemResponse() {
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }
}
