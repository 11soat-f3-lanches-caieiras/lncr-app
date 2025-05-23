package br.com.tp.lanchescaieiras.payments.mercadopago.applications.usercases;

import br.com.tp.lanchescaieiras.payments.mercadopago.domain.Payment;

public interface PaymentUserCases {

    public Payment createCharge(Payment payment);

    public Payment getExternalReferenceInPayment(String paymentId);

}
