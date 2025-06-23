package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetadata;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodItemListResponse {

    ResponseMetadata responseMetadata;
    List<FoodItem> _content;

    public FoodItemListResponse() {
    }

    public FoodItemListResponse(ResponseMetadata responseMetadata, List<FoodItem> _content) {
        this.responseMetadata = responseMetadata;
        this._content = _content;
    }

    public FoodItemListResponse(List<FoodItem> _content) {
        this._content = _content;
        this.responseMetadata = new ResponseMetadata();
    }

    public ResponseMetadata getResponseMetada() {
        return responseMetadata;
    }

    public void setResponseMetada(ResponseMetadata responseMetadata) {
        this.responseMetadata = responseMetadata;
    }

    public List<FoodItem> get_content() {
        return _content;
    }

    public void set_content(List<FoodItem> _content) {
        this._content = _content;
    }
}
