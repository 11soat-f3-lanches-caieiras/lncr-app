package br.com.tp.lanchescaieiras.payments.mercadopago.adapter.inbound.controllers;

import br.com.tp.lanchescaieiras.payments.mercadopago.domain.Payment;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.PaymentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface PaymentController {

    public ResponseEntity<PaymentResponse> createCharge(@RequestBody Payment payment);

    ResponseEntity<PaymentResponse> paymentRecived(@RequestParam(name = "data.id", required = true) String dataId,
                                                   @RequestParam(name = "type", required = true) String type);

}
