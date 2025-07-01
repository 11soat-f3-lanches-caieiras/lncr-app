package br.com.tp.lanchescaieiras.payments.mercadopago.applications.usercases;

import br.com.tp.lanchescaieiras.payments.mercadopago.domain.Payment;

public interface PaymentUserCases {

    Payment createCharge(Payment payment);

    Payment updatePaymentByPaymentId(String paymentId);

    Payment findByCustomerOrderId(Integer customerOrderId);

}
