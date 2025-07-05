package br.com.tp.lanchescaieiras._core.applications.payment.usercases;


import br.com.tp.lanchescaieiras._core.domain.payment.Payment;

public interface PaymentUserCases {

    Payment createCharge(Payment payment);

    Payment getExternalReferenceInPayment(String paymentId);

    Payment updatePaymentByPaymentId(String paymentId);

    Payment findByCustomerOrderId(Integer customerOrderId);
}
