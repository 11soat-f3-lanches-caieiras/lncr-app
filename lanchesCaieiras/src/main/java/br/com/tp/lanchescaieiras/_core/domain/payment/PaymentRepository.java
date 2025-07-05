package br.com.tp.lanchescaieiras._core.domain.payment;

public interface PaymentRepository {

    Payment createCharge(Payment payment);

    Payment findById(Integer externalReferenceId);

    Payment findByCustomerOrderId(Integer customerOrderId);

    Payment update(Payment payment);
}
