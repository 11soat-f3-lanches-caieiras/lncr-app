package br.com.tp.lanchescaieiras.kitchenorder.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KitchenOrderResponse {
    private ResponseMetada _response;
    private KitchenOrder _content;

    public KitchenOrderResponse(ResponseMetada _response, KitchenOrder _content) {
        this._response = _response;
        this._content = _content;
    }

    public KitchenOrderResponse() {
        this._response = new ResponseMetada();
    }

    public KitchenOrderResponse(KitchenOrder _content) {
        this._content = _content;
        this._response = new ResponseMetada();
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public KitchenOrder get_content() {
        return _content;
    }

    public void set_content(KitchenOrder _content) {
        this._content = _content;
    }
}
