package br.com.tp.lanchescaieiras._core.commons.interfaces.payment;

import java.util.List;

public interface PaymentController<T> {
    T createPaymentCharge(PaymentDatabase paymentDatabase, T paymentDTO);

    T getPaymentById(PaymentDatabase paymentDatabase, Integer paymentId);

    T getPaymentByCustomerOrderId(PaymentDatabase paymentDatabase, Integer customerOrderId);

    T cancelPaymentByOrderId(PaymentDatabase paymentDatabase, Integer customerOrderId);

    T processPaymentReceived(PaymentDatabase paymentDatabase, String dataId);

    List<T> getPaymentByStatusList(PaymentDatabase paymentDatabase, List<String> paymentStatusList);
}
