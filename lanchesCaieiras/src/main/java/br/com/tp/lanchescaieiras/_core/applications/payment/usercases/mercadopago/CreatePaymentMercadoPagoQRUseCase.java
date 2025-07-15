package br.com.tp.lanchescaieiras._core.applications.payment.usercases.mercadopago;

import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lanchescaieiras._core.commons.enums.PaymentStatus;
import br.com.tp.lanchescaieiras._core.commons.exceptions.PaymentException;
import br.com.tp.lanchescaieiras._core.commons.interfaces.payment.PaymentGateway;
import br.com.tp.lanchescaieiras._core.domain.payment.PaymentMercadopagoQR;

public class CreatePaymentMercadoPagoQRUseCase {

    private final PaymentGateway paymentGateway;

    public CreatePaymentMercadoPagoQRUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public PaymentMercadopagoQR createCharge(PaymentMercadopagoQrDTO paymentMercadopagoQrDTO) {
        if (this.paymentGateway.getPaymentByCustomerOrderId(paymentMercadopagoQrDTO.getOrderId()) == null) {
            paymentMercadopagoQrDTO.setStatus(PaymentStatus.CHARGED.getDescription());
            PaymentMercadopagoQR paymentMercadopagoQR = new PaymentMercadopagoQR(paymentMercadopagoQrDTO);
            paymentMercadopagoQR = (PaymentMercadopagoQR) this.paymentGateway.createCharge(paymentMercadopagoQR);
            this.paymentGateway.sendNotification("PAYMENT_MERCADOPAGO_QR_CHECKOUT", paymentMercadopagoQR.getOrderId(), "Nova cobrança criada com id: " + paymentMercadopagoQR.getOrderId() + " Aguardando pagamento");
            return paymentMercadopagoQR;
        }
        throw new PaymentException("Já existe uma cobrança para o pedido id "+ paymentMercadopagoQrDTO.getOrderId(),409);
    }
}
