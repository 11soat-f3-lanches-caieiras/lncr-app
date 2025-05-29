package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodItemImageDataResponse {

    private ResponseMetada _response;
    private String _data;
    private String fileName;

    public FoodItemImageDataResponse(ResponseMetada _response, String _data, String fileName) {
        this._response = _response;
        this._data = _data;
    }

    public FoodItemImageDataResponse(String _data, String fileName) {
        this._data = _data;
        this.fileName = fileName;
        this._response = new ResponseMetada();
    }

    public FoodItemImageDataResponse() {
        this._response = new ResponseMetada();
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public String get_data() {
        return _data;
    }

    public void set_data(String _data) {
        this._data = _data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
