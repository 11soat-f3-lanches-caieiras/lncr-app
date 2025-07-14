package br.com.tp.lanchescaieiras._core.commons.interfaces.payment;

import java.util.List;

public interface PaymentDatabase<T> {

    T createPaymentCharge(T paymentDTO);

    T findPaymentById(Integer paymentId);

    T findPaymentByCustomerOrderId(Integer customerOrderId);

    T save(T paymentDTO);

    Integer getPaymentId(String dataId);

    void updateCustomerOrderStatus(Integer customerOrderId, String newStatus);

    List<T> findByStatusList(List<Integer> paymentStatusList);

    void sendNotification(String notificationType, Integer artefactId, String message);

    /*

    <T> T findByCustomerOrderId(Integer customerOrderId);

    <T> T updatePayment(T paymentDTO);*/

}
