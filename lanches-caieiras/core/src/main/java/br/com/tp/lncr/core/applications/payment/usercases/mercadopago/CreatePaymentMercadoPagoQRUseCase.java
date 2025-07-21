package br.com.tp.lncr.core.applications.payment.usercases.mercadopago;

import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lncr.core.commons.enums.PaymentStatus;
import br.com.tp.lncr.core.commons.exceptions.PaymentException;
import br.com.tp.lncr.core.commons.interfaces.payment.PaymentGateway;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;

public class CreatePaymentMercadoPagoQRUseCase {

    private final PaymentGateway paymentGateway;

    public CreatePaymentMercadoPagoQRUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public PaymentMercadopagoQR createCharge(PaymentMercadopagoQrDTO paymentMercadopagoQrDTO) {
        Logger.info("Iniciando criação de cobrança Mercado Pago QR para o pedido id: " + paymentMercadopagoQrDTO.getOrderId());
        if (this.paymentGateway.getPaymentByCustomerOrderId(paymentMercadopagoQrDTO.getOrderId()) == null) {
            paymentMercadopagoQrDTO.setStatus(PaymentStatus.CHARGED.getDescription());
            PaymentMercadopagoQR paymentMercadopagoQR = new PaymentMercadopagoQR(paymentMercadopagoQrDTO);
            paymentMercadopagoQR = this.paymentGateway.createPaymentOrder(paymentMercadopagoQR);
            this.paymentGateway.sendNotification("PAYMENT_MERCADOPAGO_QR_CHECKOUT", paymentMercadopagoQR.getOrderId(), "Nova cobrança criada com id: " + paymentMercadopagoQR.getOrderId() + " Aguardando pagamento");
            Logger.info("Cobrança Mercado Pago QR criada com sucesso, id: " + paymentMercadopagoQR.getOrderId());
            return paymentMercadopagoQR;
        }
        throw new PaymentException("Já existe uma cobrança para o pedido id "+ paymentMercadopagoQrDTO.getOrderId(),409);
    }
}
