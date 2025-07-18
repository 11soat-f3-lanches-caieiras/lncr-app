package br.com.tp.lncr.external.integrations.payment;

import br.com.tp.lncr.core.commons.dtos.payment.PaymentDTO;
import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;

public interface PaymentIntegration {

    void cancelPaymentChargeByCustomerOrderId(Integer customerOrderId);

    void createPayment(Integer customerOrderId, Double totalCost);

    PaymentMercadopagoQrDTO getPaymentByCustomerOrderId(Integer id);

    PaymentDTO getPaymentByOrderId(Integer customerOrderId);
}
