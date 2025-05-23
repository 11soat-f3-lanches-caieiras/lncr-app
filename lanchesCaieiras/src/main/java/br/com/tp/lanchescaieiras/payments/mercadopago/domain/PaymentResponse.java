package br.com.tp.lanchescaieiras.payments.mercadopago.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {
    private ResponseMetada _response;
    private Payment payment;

    public PaymentResponse(ResponseMetada _response, Payment payment) {
        this._response = _response;
        this.payment = payment;
    }

    public PaymentResponse() {
    }

    public PaymentResponse(Payment payment) {
        this.payment = payment;
        this._response = new ResponseMetada();
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
