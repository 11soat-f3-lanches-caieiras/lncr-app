package br.com.tp.lanchescaieiras.kitchenorder.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class KitchenOrderListResponse {
    private ResponseMetada _response;
    private List<KitchenOrder> kitchenOrder;


    public KitchenOrderListResponse(ResponseMetada _response, List<KitchenOrder> kitchenOrder) {
        this._response = _response;
        this.kitchenOrder = kitchenOrder;
    }

    public KitchenOrderListResponse() {
    }

    public KitchenOrderListResponse(List<KitchenOrder> kitchenOrder) {
        this.kitchenOrder = kitchenOrder;
        this._response = new ResponseMetada();
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public List<KitchenOrder> getKitchenOrder() {
        return kitchenOrder;
    }

    public void setKitchenOrder(List<KitchenOrder> kitchenOrder) {
        this.kitchenOrder = kitchenOrder;
    }
}
