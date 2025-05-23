package br.com.tp.lanchescaieiras.kitchenorder.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KitchenOrderResponse {
    private ResponseMetada _response;
    private KitchenOrder kitchenOrder;

    public KitchenOrderResponse(ResponseMetada _response, KitchenOrder kitchenOrder) {
        this._response = _response;
        this.kitchenOrder = kitchenOrder;
    }

    public KitchenOrderResponse() {
        this._response = new ResponseMetada();
    }

    public KitchenOrderResponse(KitchenOrder kitchenOrder) {
        this.kitchenOrder = kitchenOrder;
        this._response = new ResponseMetada();
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public KitchenOrder getKitchenOrder() {
        return kitchenOrder;
    }

    public void setKitchenOrder(KitchenOrder kitchenOrder) {
        this.kitchenOrder = kitchenOrder;
    }
}
