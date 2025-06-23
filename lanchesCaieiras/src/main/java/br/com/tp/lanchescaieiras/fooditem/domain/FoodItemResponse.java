package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetadata;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodItemResponse {
    private ResponseMetadata _response;
    private FoodItem _content;

    public FoodItemResponse(ResponseMetadata _response, FoodItem _content) {
        this._response = _response;
        this._content = _content;
    }

    public FoodItemResponse(FoodItem _content) {
        this._content = _content;
        this._response = new ResponseMetadata();
    }

    public FoodItemResponse() {
    }

    public ResponseMetadata get_response() {
        return _response;
    }

    public void set_response(ResponseMetadata _response) {
        this._response = _response;
    }

    public FoodItem get_content() {
        return _content;
    }

    public void set_content(FoodItem _content) {
        this._content = _content;
    }


}

