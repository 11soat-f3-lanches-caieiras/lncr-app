package br.com.tp.lanchescaieiras.customerorder.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerOrderListResponse {
    private ResponseMetada _response;
    private List<CustomerOrder> customerOrder;


    public CustomerOrderListResponse(ResponseMetada _response, List<CustomerOrder> customerOrder) {
        this._response = _response;
        this.customerOrder = customerOrder;
    }

    public CustomerOrderListResponse() {
    }

    public CustomerOrderListResponse(List<CustomerOrder> customerOrder) {
        this.customerOrder = customerOrder;
        this._response = new ResponseMetada();
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public List<CustomerOrder> getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(List<CustomerOrder> customerOrder) {
        this.customerOrder = customerOrder;
    }
}
