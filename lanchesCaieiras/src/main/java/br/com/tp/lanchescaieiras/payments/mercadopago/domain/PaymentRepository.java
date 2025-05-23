package br.com.tp.lanchescaieiras.payments.mercadopago.domain;

public interface PaymentRepository {

    public Payment createCharge(Payment payment);

    Payment findById(Integer externalReferenceId);

    Payment update(Payment payment);
}
