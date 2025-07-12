package br.com.tp.lanchescaieiras._external.apis.payment;

import br.com.tp.lanchescaieiras._core.adapters.payment.mercadopago.PaymentMercadoPagoQrControllerImpl;
import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lanchescaieiras._core.commons.utils.ResponseEntityModelUtil;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseListModel;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseModel;
import br.com.tp.lanchescaieiras._external.configs.MercadoPagoConfig;
import br.com.tp.lanchescaieiras._external.dataproxy.PaymentMercadoPagoQrDataProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments/mercadoPago")
public class PaymentMercadoPagoQrRestRestControllerImpl implements PaymentRestController<PaymentMercadopagoQrDTO> {


    public final PaymentMercadoPagoQrControllerImpl paymentMercadoPagoQrController;
    public final PaymentMercadoPagoQrDataProxy paymentMercadoPagoQrDataProxy;
    public final MercadoPagoConfig mercadoPagoConfig;


    public PaymentMercadoPagoQrRestRestControllerImpl(PaymentMercadoPagoQrControllerImpl paymentMercadoPagoQrController, PaymentMercadoPagoQrDataProxy paymentMercadoPagoQrDataProxy, MercadoPagoConfig mercadoPagoConfig) {
        this.paymentMercadoPagoQrController = paymentMercadoPagoQrController;
        this.paymentMercadoPagoQrDataProxy = paymentMercadoPagoQrDataProxy;
        this.mercadoPagoConfig = mercadoPagoConfig;
    }

    @Override
    @PostMapping("/charge")
    public ResponseEntity<ResponseModel<PaymentMercadopagoQrDTO>> createPaymentCharge(@RequestBody PaymentMercadopagoQrDTO paymentMercadopagoQrDTO) {
        paymentMercadopagoQrDTO = this.paymentMercadoPagoQrController.createPaymentCharge(this.paymentMercadoPagoQrDataProxy,paymentMercadopagoQrDTO);
        return ResponseEntityModelUtil.created(null,mercadoPagoConfig.locationPrefix +"/" + paymentMercadopagoQrDTO.getId());
    }

    @Override
    @GetMapping("/{paymentId}")
    public ResponseEntity<ResponseModel<PaymentMercadopagoQrDTO>> getPaymentById(@PathVariable(name = "paymentId") Integer paymentId){
        PaymentMercadopagoQrDTO paymentMercadopagoQrDTO = this.paymentMercadoPagoQrController.getPaymentById(this.paymentMercadoPagoQrDataProxy,paymentId);
        return ResponseEntityModelUtil.OK(paymentMercadopagoQrDTO);
    }

    @Override
    @GetMapping("/customerOrder/{customerOrderId}")
    public ResponseEntity<ResponseModel<PaymentMercadopagoQrDTO>> getPaymentByCustomerOrderId(@PathVariable(name = "customerOrderId")Integer customerOrderId) {
        PaymentMercadopagoQrDTO paymentMercadopagoQrDTO = this.paymentMercadoPagoQrController.getPaymentByCustomerOrderId(this.paymentMercadoPagoQrDataProxy,customerOrderId);
        return ResponseEntityModelUtil.OK(paymentMercadopagoQrDTO);
    }

    @Override
    @PatchMapping("/cancelPaymentByCustomerOrderId/{customerOrderId}")
    public ResponseEntity<ResponseModel<PaymentMercadopagoQrDTO>> cancelPaymentByCustomerOrderId(@PathVariable(name = "customerOrderId") Integer customerOrderId) {
        PaymentMercadopagoQrDTO paymentMercadopagoQrDTO = this.paymentMercadoPagoQrController.cancelPaymentByOrderId(this.paymentMercadoPagoQrDataProxy,customerOrderId);
        return ResponseEntityModelUtil.OK(paymentMercadopagoQrDTO);
    }

    @Override
    @PatchMapping("/paymentReceived")
    public ResponseEntity<ResponseModel<PaymentMercadopagoQrDTO>> processPaymentReceived(@RequestParam(name = "id") String id,
                                                                                         @RequestParam(name = "type", defaultValue = "payment") String type) {
        PaymentMercadopagoQrDTO paymentMercadopagoQrDTO = this.paymentMercadoPagoQrController.processPaymentReceived(this.paymentMercadoPagoQrDataProxy,id);
        return ResponseEntityModelUtil.OK(paymentMercadopagoQrDTO);
    }

    @Override
    @GetMapping("paymentStatusList/{paymentStatusList}")
    public ResponseEntity<ResponseListModel<PaymentMercadopagoQrDTO>> getPaymentByStatusList(@PathVariable(name = "paymentStatusList") List<String> paymentStatusList) {
        List<PaymentMercadopagoQrDTO> paymentMercadopagoQrDTO = this.paymentMercadoPagoQrController.getPaymentByStatusList(this.paymentMercadoPagoQrDataProxy,paymentStatusList);
        return ResponseEntityModelUtil.listOK(paymentMercadopagoQrDTO);
    }

}
