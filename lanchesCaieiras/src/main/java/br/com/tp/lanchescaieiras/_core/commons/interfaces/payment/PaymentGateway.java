package br.com.tp.lanchescaieiras._core.commons.interfaces.payment;

import java.util.List;

public interface PaymentGateway<T> {

    T createCharge(T payment);

    T save(T payment);

    T getPaymentById(Integer paymentId);

    T getPaymentByCustomerOrderId(Integer customerOrderId);

    void updateCustomerOrderStatus(Integer customerOrderId, String newStatus);

    List<T> getPaymentMercadoPagoQRList(List<Integer> paymentStatusIdsList);

    void sendNotification(String notificationType, Integer artefactId, String message);

    void cancelPaymentOrder(String meliId);

    void refundPaymentOrder(String meliId);
}
