package br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.integration;

import br.com.tp.lanchescaieiras.payments.mercadopago.domain.Payment;


public interface MercadoPagoIntegration {

    public Payment createQRCode(Payment payment);

    public Integer getPaymentId(String paymentId);

}
