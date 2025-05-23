package br.com.tp.lanchescaieiras.payments.mercadopago.applications.services;

import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.integration.CustomerOrderIntegrationImpl;
import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.integration.MercadoPagoIntegrationImpl;
import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.repositories.JpaPaymentsRepositoryImpl;
import br.com.tp.lanchescaieiras.payments.mercadopago.applications.usercases.PaymentUserCases;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.Payment;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentUserCases {

    public final JpaPaymentsRepositoryImpl jpaPaymentsRepository;
    public final MercadoPagoIntegrationImpl mercadoPagoIntegration;
    public final CustomerOrderIntegrationImpl customerOrderIntegration;

    public PaymentServiceImpl(JpaPaymentsRepositoryImpl jpaPaymentsRepository, MercadoPagoIntegrationImpl mercadoPagoIntegration,
                              CustomerOrderIntegrationImpl customerOrderIntegration) {
        this.jpaPaymentsRepository = jpaPaymentsRepository;
        this.mercadoPagoIntegration = mercadoPagoIntegration;
        this.customerOrderIntegration = customerOrderIntegration;
    }

    @Override
    public Payment createCharge(Payment payment) {
        payment.setStatus(PaymentStatus.CHARGED.getDescription());
        payment =  jpaPaymentsRepository.createCharge(payment); //Cria id do pagamento
        payment = mercadoPagoIntegration.createQRCode(payment); //Cria o QRCode
        return jpaPaymentsRepository.createCharge(payment); // Salva informações de QRCode
    }

    @Override
    public Payment getExternalReferenceInPayment(String paymentId) {
        Integer externalReferenceId = mercadoPagoIntegration.getPaymentId(paymentId);
        if (externalReferenceId == null) {
            throw new RuntimeException("Payment not found");
        }
        Payment payment = jpaPaymentsRepository.findById(externalReferenceId);
        if (payment.getStatus() == PaymentStatus.CHARGED.getDescription()) {
            payment.setStatus(PaymentStatus.PAID.getDescription());
            payment = jpaPaymentsRepository.update(payment);

        }
        customerOrderIntegration.updateCustomerOrderStatus(externalReferenceId,"Received");
        payment.setQrData(null);
        payment.setStoreOrderId(null);
        return payment;
    }
}
