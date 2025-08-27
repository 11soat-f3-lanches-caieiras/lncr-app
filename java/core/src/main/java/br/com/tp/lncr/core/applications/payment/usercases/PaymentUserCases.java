package br.com.tp.lncr.core.applications.payment.usercases;


import br.com.tp.lncr.core.domain.payment.Payment;

public interface PaymentUserCases {

    Payment createCharge(Payment payment);

    Payment getExternalReferenceInPayment(String paymentId);

    Payment updatePaymentByPaymentId(String paymentId);

    Payment findByCustomerOrderId(Integer customerOrderId);
}
