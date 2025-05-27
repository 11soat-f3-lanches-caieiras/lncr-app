package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodItemListResponse {

    ResponseMetada responseMetada;
    List<FoodItem> _content;

    public FoodItemListResponse() {
    }

    public FoodItemListResponse(ResponseMetada responseMetada, List<FoodItem> _content) {
        this.responseMetada = responseMetada;
        this._content = _content;
    }

    public FoodItemListResponse(List<FoodItem> _content) {
        this._content = _content;
        this.responseMetada = new ResponseMetada();
    }

    public ResponseMetada getResponseMetada() {
        return responseMetada;
    }

    public void setResponseMetada(ResponseMetada responseMetada) {
        this.responseMetada = responseMetada;
    }

    public List<FoodItem> get_content() {
        return _content;
    }

    public void set_content(List<FoodItem> _content) {
        this._content = _content;
    }
}
