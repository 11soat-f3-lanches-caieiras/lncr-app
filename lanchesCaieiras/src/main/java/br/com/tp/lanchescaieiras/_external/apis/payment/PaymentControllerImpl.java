package br.com.tp.lanchescaieiras._external.apis.payment;

import br.com.tp.lanchescaieiras._core.applications.payment.services.PaymentServiceImpl;
import br.com.tp.lanchescaieiras._core.domain.payment.Payment;
import br.com.tp.lanchescaieiras._core.domain.payment.PaymentResponse;
import br.com.tp.lanchescaieiras._external.configs.MercadoPagoConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments/mercadoPago")
public class PaymentControllerImpl implements PaymentController {

    public final PaymentServiceImpl paymentService;
    public final MercadoPagoConfig mercadoPagoConfig;


    public PaymentControllerImpl(PaymentServiceImpl paymentService, MercadoPagoConfig mercadoPagoConfig) {
        this.paymentService = paymentService;
        this.mercadoPagoConfig = mercadoPagoConfig;
    }

    @Override
    @PostMapping("/charge")
    public ResponseEntity<PaymentResponse> createCharge(@RequestBody Payment payment) {
        payment = paymentService.createCharge(payment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", mercadoPagoConfig.getLocationPrefix() + "/" + payment.getId())
                .body(new PaymentResponse(payment));
    }

    @Override
    @PatchMapping("/callback")
    public ResponseEntity<PaymentResponse> paymentRecived(@RequestParam(name = "data.id", required = true) String dataId,
                                                          @RequestParam(name = "type", required = true) String type) {
        return new ResponseEntity<PaymentResponse>(new PaymentResponse(paymentService.updatePaymentByPaymentId(dataId)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/customerOrder/{id}")
    public ResponseEntity<PaymentResponse> getPaymentByCustomerOrderId(@PathVariable(name = "id") Integer customerOrderId) {
        return new ResponseEntity<PaymentResponse>(new PaymentResponse(paymentService.findByCustomerOrderId(customerOrderId)), HttpStatus.OK);
    }
}
