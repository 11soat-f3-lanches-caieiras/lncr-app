package br.com.tp.lanchescaieiras._external.integrations.payment;

import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;

public interface PaymentIntegration {

    void createPayment(Integer customerOrderId, Double totalCost);

    PaymentDTO getPaymentByOrderId(Integer customerOrderId);

    void cancelPaymentChargeByCustomerOrderId(Integer customerOrderId);

    PaymentMercadopagoQrDTO getPaymentByCustomerOrderId(Integer id);
}
