package br.com.tp.lanchescaieiras.customer.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerListResponse {
    private ResponseMetada _response;
    private List<Customer> customers;

    public CustomerListResponse(ResponseMetada _response, List<Customer> customers) {
        this._response = _response;
        this.customers = customers;
    }

    public CustomerListResponse() {
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}
