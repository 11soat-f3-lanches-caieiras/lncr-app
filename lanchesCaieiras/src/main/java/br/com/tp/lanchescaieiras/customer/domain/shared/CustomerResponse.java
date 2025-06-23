package br.com.tp.lanchescaieiras.customer.domain.shared;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetadata;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {
    private ResponseMetadata _response;
    private Customer _content;

    public CustomerResponse() {
    }

    public CustomerResponse(ResponseMetadata _response, Customer _content) {
        this._response = _response;
        this._content = _content;
    }

    public CustomerResponse(Customer _content) {
        this._content = _content;
        this._response = new ResponseMetadata();
    }

    public ResponseMetadata get_response() {
        return _response;
    }

    public void set_response(ResponseMetadata _response) {
        this._response = _response;
    }

    public Customer get_content() {
        return _content;
    }

    public void set_content(Customer _content) {
        this._content = _content;
    }
}
