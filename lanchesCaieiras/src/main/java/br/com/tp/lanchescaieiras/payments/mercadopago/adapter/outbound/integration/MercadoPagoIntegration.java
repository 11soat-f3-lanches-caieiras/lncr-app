package br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.integration;

import br.com.tp.lanchescaieiras.payments.mercadopago.domain.Payment;


public interface MercadoPagoIntegration {

    Payment createQRCode(Payment payment);

    Integer getPaymentId(String paymentId);

}
