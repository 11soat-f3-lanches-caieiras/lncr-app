package br.com.tp.lncr.core.commons.interfaces.payment;

import br.com.tp.lncr.core.domain.payment.Payment;
import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;

import java.util.List;

public interface PaymentGateway<T extends Payment> {

    PaymentMercadopagoQR createPaymentOrder(T payment);

    PaymentMercadopagoQR savePayment(T payment);

    PaymentMercadopagoQR getPaymentById(Integer paymentId);

    PaymentMercadopagoQR getPaymentByCustomerOrderId(Integer customerOrderId);

    void updateCustomerOrderStatus(Integer customerOrderId, String newStatus);

    List<PaymentMercadopagoQR> getPaymentMercadoPagoQRList(List<Integer> paymentStatusIdsList);

    void sendNotification(String notificationType, Integer artefactId, String message);

    void cancelPaymentOrderByProviderId(String meliId);

    void refundPaymentOrderByProviderId(String meliId);
}
