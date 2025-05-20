package br.com.tp.lanchescaieiras.customerorder.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerOrderResponse {
    private ResponseMetada _response;
    private CustomerOrder customerOrder;

    public CustomerOrderResponse(ResponseMetada _response, CustomerOrder customerOrder) {
        this._response = _response;
        this.customerOrder = customerOrder;
    }

    public CustomerOrderResponse() {
        this._response = new ResponseMetada();
    }

    public CustomerOrderResponse(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
        this._response = new ResponseMetada();
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}
