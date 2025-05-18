package br.com.tp.lanchescaieiras.customer.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {
    private ResponseMetada _response;
    private Customer _content;

    public CustomerResponse() {
    }

    public CustomerResponse(ResponseMetada _response, Customer _content) {
        this._response = _response;
        this._content = _content;
    }

    public CustomerResponse(Customer _content) {
        this._content = _content;
        this._response = new ResponseMetada();
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public Customer get_content() {
        return _content;
    }

    public void set_content(Customer _content) {
        this._content = _content;
    }
}
