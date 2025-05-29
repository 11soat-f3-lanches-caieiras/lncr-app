package br.com.tp.lanchescaieiras.kitchenorder.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class KitchenOrderListResponse {
    private ResponseMetada _response;
    private List<KitchenOrder> _content;


    public KitchenOrderListResponse(ResponseMetada _response, List<KitchenOrder> _content) {
        this._response = _response;
        this._content = _content;
    }

    public KitchenOrderListResponse() {
    }

    public KitchenOrderListResponse(List<KitchenOrder> _content) {
        this._content = _content;
        this._response = new ResponseMetada();
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public List<KitchenOrder> get_content() {
        return _content;
    }

    public void set_content(List<KitchenOrder> _content) {
        this._content = _content;
    }
}
