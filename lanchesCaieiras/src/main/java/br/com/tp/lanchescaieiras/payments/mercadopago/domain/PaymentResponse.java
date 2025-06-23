package br.com.tp.lanchescaieiras.payments.mercadopago.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetadata;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {
    private ResponseMetadata _response;
    private Payment payment;

    public PaymentResponse(ResponseMetadata _response, Payment payment) {
        this._response = _response;
        this.payment = payment;
    }

    public PaymentResponse() {
    }

    public PaymentResponse(Payment payment) {
        this.payment = payment;
        this._response = new ResponseMetadata();
    }

    public ResponseMetadata get_response() {
        return _response;
    }

    public void set_response(ResponseMetadata _response) {
        this._response = _response;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
