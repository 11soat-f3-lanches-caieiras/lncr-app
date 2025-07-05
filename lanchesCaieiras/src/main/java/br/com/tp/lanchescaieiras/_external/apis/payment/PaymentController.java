package br.com.tp.lanchescaieiras._external.apis.payment;

import br.com.tp.lanchescaieiras._core.domain.payment.Payment;
import br.com.tp.lanchescaieiras._core.domain.payment.PaymentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface PaymentController {

    ResponseEntity<PaymentResponse> createCharge(@RequestBody Payment payment);

    ResponseEntity<PaymentResponse> paymentRecived(@RequestParam(name = "data.id") String dataId,
                                                   @RequestParam(name = "type") String type);

    ResponseEntity<PaymentResponse> getPaymentByCustomerOrderId(@PathVariable(name = "customerOrderId") Integer customerOrderId);

}
