package br.com.tp.lanchescaieiras.customerorder.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetadata;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerOrderListResponse {
    private ResponseMetadata _response;
    private List<CustomerOrder> _content;


    public CustomerOrderListResponse(ResponseMetadata _response, List<CustomerOrder> _content) {
        this._response = _response;
        this._content = _content;
    }

    public CustomerOrderListResponse() {
    }

    public CustomerOrderListResponse(List<CustomerOrder> _content) {
        this._content = _content;
        this._response = new ResponseMetadata();
    }

    public ResponseMetadata get_response() {
        return _response;
    }

    public void set_response(ResponseMetadata _response) {
        this._response = _response;
    }

    public List<CustomerOrder> get_content() {
        return _content;
    }

    public void set_content(List<CustomerOrder> _content) {
        this._content = _content;
    }
}
