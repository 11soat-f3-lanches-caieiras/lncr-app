package br.com.tp.lanchescaieiras._external.integrations.payment.mercadopago;

import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;


public interface MercadoPagoIntegration {

    PaymentMercadopagoQrDTO createQRCode(PaymentMercadopagoQrDTO payment);

    Integer getPaymentId(String paymentId);

}
