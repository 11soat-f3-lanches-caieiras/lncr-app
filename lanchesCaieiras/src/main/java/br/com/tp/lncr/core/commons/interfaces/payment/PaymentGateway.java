package br.com.tp.lncr.core.commons.interfaces.payment;

import java.util.List;

public interface PaymentGateway<T> {

    T createPaymentOrder(T payment);

    T savePayment(T payment);

    T getPaymentById(Integer paymentId);

    T getPaymentByCustomerOrderId(Integer customerOrderId);

    void updateCustomerOrderStatus(Integer customerOrderId, String newStatus);

    List<T> getPaymentMercadoPagoQRList(List<Integer> paymentStatusIdsList);

    void sendNotification(String notificationType, Integer artefactId, String message);

    void cancelPaymentOrderByProviderId(String meliId);

    void refundPaymentOrderByProviderId(String meliId);
}
