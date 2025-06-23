package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetadata;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodItemImageDataResponse {

    private ResponseMetadata _response;
    private String _data;
    private String fileName;

    public FoodItemImageDataResponse(ResponseMetadata _response, String _data, String fileName) {
        this._response = _response;
        this._data = _data;
    }

    public FoodItemImageDataResponse(String _data, String fileName) {
        this._data = _data;
        this.fileName = fileName;
        this._response = new ResponseMetadata();
    }

    public FoodItemImageDataResponse() {
        this._response = new ResponseMetadata();
    }

    public ResponseMetadata get_response() {
        return _response;
    }

    public void set_response(ResponseMetadata _response) {
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
