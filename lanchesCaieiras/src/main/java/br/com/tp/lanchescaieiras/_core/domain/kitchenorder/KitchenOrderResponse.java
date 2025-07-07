package br.com.tp.lanchescaieiras._core.domain.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.model.ResponseMetadata;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KitchenOrderResponse {
    private ResponseMetadata _response;
    private KitchenOrder _content;

    public KitchenOrderResponse(ResponseMetadata _response, KitchenOrder _content) {
        this._response = _response;
        this._content = _content;
    }

    public KitchenOrderResponse() {
        this._response = new ResponseMetadata();
    }

    public KitchenOrderResponse(KitchenOrder _content) {
        this._content = _content;
        this._response = new ResponseMetadata();
    }

    public ResponseMetadata get_response() {
        return _response;
    }

    public void set_response(ResponseMetadata _response) {
        this._response = _response;
    }

    public KitchenOrder get_content() {
        return _content;
    }

    public void set_content(KitchenOrder _content) {
        this._content = _content;
    }
}
