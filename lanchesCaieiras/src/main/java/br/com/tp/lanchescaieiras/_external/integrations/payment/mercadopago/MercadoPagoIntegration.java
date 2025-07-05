package br.com.tp.lanchescaieiras._external.integrations.payment.mercadopago;

import br.com.tp.lanchescaieiras._core.domain.payment.Payment;


public interface MercadoPagoIntegration {

    Payment createQRCode(Payment payment);

    Integer getPaymentId(String paymentId);

}
