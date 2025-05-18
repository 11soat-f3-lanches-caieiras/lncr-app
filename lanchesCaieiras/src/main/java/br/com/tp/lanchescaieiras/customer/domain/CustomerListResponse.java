package br.com.tp.lanchescaieiras.customer.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerListResponse {
    private ResponseMetada _response;
    private List<Customer> _content;

    public CustomerListResponse(ResponseMetada _response, List<Customer> _content) {
        this._response = _response;
        this._content = _content;
    }

    public CustomerListResponse() {
    }

    public CustomerListResponse(List<Customer> _content) {
        this._content = _content;
        this._response = new ResponseMetada();
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public List<Customer> get_content() {
        return _content;
    }

    public void set_content(List<Customer> _content) {
        this._content = _content;
    }

}
