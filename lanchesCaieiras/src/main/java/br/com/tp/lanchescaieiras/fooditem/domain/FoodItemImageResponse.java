package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;

public class FoodItemImageResponse {
    private ResponseMetada _response;
    private FoodItemImage foodItemImage;

    public FoodItemImageResponse(ResponseMetada _response, FoodItemImage foodItemImage) {
        this._response = _response;
        this.foodItemImage = foodItemImage;
    }

    public FoodItemImageResponse(FoodItemImage foodItemImage) {
        this.foodItemImage = foodItemImage;
        this._response = new ResponseMetada();
    }

    public FoodItemImageResponse() {
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public FoodItemImage getImage() {
        return foodItemImage;
    }

    public void setImage(FoodItemImage foodItemImage) {
        this.foodItemImage = foodItemImage;
    }
}
