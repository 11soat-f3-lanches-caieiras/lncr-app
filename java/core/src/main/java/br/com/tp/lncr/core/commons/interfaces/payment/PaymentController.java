package br.com.tp.lncr.core.commons.interfaces.payment;

import java.util.List;
import java.util.Map;

public interface PaymentController<T> {
    T createPaymentCharge(PaymentDatabase paymentDatabase, T paymentDTO);

    T getPaymentById(PaymentDatabase paymentDatabase, Integer paymentId);

    T getPaymentByCustomerOrderId(PaymentDatabase paymentDatabase, Integer customerOrderId);

    T cancelPaymentByOrderId(PaymentDatabase paymentDatabase, Integer customerOrderId);

    List<T> getPaymentByStatusList(PaymentDatabase paymentDatabase, List<String> paymentStatusList);

    T processPaymentReceived(PaymentDatabase paymentDatabase, String externalReference, String dataId, Map<String, Object> body);
}
