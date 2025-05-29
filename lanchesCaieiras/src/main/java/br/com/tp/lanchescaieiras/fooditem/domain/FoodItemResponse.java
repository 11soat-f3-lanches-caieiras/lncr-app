package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodItemResponse {
    private ResponseMetada _response;
    private FoodItem _content;

    public FoodItemResponse(ResponseMetada _response, FoodItem _content) {
        this._response = _response;
        this._content = _content;
    }

    public FoodItemResponse(FoodItem _content) {
        this._content = _content;
        this._response = new ResponseMetada();
    }

    public FoodItemResponse() {
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public FoodItem get_content() {
        return _content;
    }

    public void set_content(FoodItem _content) {
        this._content = _content;
    }


}

