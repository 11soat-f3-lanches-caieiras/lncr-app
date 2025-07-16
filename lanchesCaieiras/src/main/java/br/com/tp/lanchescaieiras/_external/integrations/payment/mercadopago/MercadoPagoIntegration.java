package br.com.tp.lanchescaieiras._external.integrations.payment.mercadopago;

import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;

public interface MercadoPagoIntegration {

    String getAccessToken();

    PaymentMercadopagoQrDTO createOrder(PaymentMercadopagoQrDTO paymentMercadopagoQrDTO);

    void cancelOrder(String meliId);

    void refundOrder(String meliId);
}
