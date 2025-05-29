package br.com.tp.lanchescaieiras.customerorder.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerOrderResponse {
    private ResponseMetada _response;
    private CustomerOrder _content;

    public CustomerOrderResponse(ResponseMetada _response, CustomerOrder customerOrder) {
        this._response = _response;
        this._content = customerOrder;
    }

    public CustomerOrderResponse() {
        this._response = new ResponseMetada();
    }

    public CustomerOrderResponse(CustomerOrder customerOrder) {
        this._content = customerOrder;
        this._response = new ResponseMetada();
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public CustomerOrder get_content() {
        return _content;
    }

    public void set_content(CustomerOrder _content) {
        this._content = _content;
    }
}
