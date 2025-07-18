package br.com.tp.lncr.external.integrations.payment.mercadopago;

import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;

public interface MercadoPagoIntegration {

    void cancelOrder(String meliId);

    PaymentMercadopagoQrDTO createOrder(PaymentMercadopagoQrDTO paymentMercadopagoQrDTO);

    String getAccessToken();

    void refundOrder(String meliId);
}
