package br.com.tp.lanchescaieiras._core.domain.customerorder;

import br.com.tp.lanchescaieiras._core.commons.model.ResponseMetadata;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerOrderResponse {
    private ResponseMetadata _response;
    private CustomerOrder _content;

    public CustomerOrderResponse(ResponseMetadata _response, CustomerOrder customerOrder) {
        this._response = _response;
        this._content = customerOrder;
    }

    public CustomerOrderResponse() {
        this._response = new ResponseMetadata();
    }

    public CustomerOrderResponse(CustomerOrder customerOrder) {
        this._content = customerOrder;
        this._response = new ResponseMetadata();
    }

    public ResponseMetadata get_response() {
        return _response;
    }

    public void set_response(ResponseMetadata _response) {
        this._response = _response;
    }

    public CustomerOrder get_content() {
        return _content;
    }

    public void set_content(CustomerOrder _content) {
        this._content = _content;
    }
}
