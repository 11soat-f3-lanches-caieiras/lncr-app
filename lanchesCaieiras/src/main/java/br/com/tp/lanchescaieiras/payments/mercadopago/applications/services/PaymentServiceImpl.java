package br.com.tp.lanchescaieiras.payments.mercadopago.applications.services;

import br.com.tp.lanchescaieiras.commons.adapters.outbounds.integrations.CustomerOrderIntegrationImpl;
import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.integration.MercadoPagoIntegrationImpl;
import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.repositories.JpaPaymentsRepositoryImpl;
import br.com.tp.lanchescaieiras.payments.mercadopago.applications.usercases.PaymentUserCases;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.Payment;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.PaymentStatus;
import br.com.tp.lanchescaieiras.payments.mercadopago.infraestructure.exceptions.PaymentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentUserCases {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
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
        log.info("Iniciando nova cobrança");
        payment.setStatus(PaymentStatus.CHARGED.getDescription());
        payment =  jpaPaymentsRepository.createCharge(payment); //Cria id do pagamento
        payment = mercadoPagoIntegration.createQRCode(payment); //Cria o QRCode
        return jpaPaymentsRepository.createCharge(payment); // Salva informações de QRCode
    }

    @Override
    public Payment updatePaymentByPaymentId(String paymentId) {
        log.info("Validando recebimento de pagamento");
        Integer externalReferenceId = mercadoPagoIntegration.getPaymentId(paymentId);
        if (externalReferenceId == null) {
            throw new PaymentException("Pagamento " + paymentId +"não encontrado", 404);
        }
        Payment payment = jpaPaymentsRepository.findById(externalReferenceId);
        if (payment == null) {
            throw new PaymentException("Não encontrado pedido para este pagemento",404);
        }


        if (payment.getStatus() == PaymentStatus.CHARGED.getDescription()) {
            payment.setStatus(PaymentStatus.PAID.getDescription());
            payment.setPaymentId(paymentId);
            payment = jpaPaymentsRepository.update(payment);
            log.info("Atualizando pedido do cliente para Received");
            customerOrderIntegration.updateCustomerOrderStatus(payment.getOrderId(), "Received");
            log.info("Pagamento efetuado!");
        }

        payment.setQrData(null);
        payment.setStoreOrderId(null);
        return payment;
    }

    @Override
    public Payment findByCustomerOrderId(Integer customerOrderId) {
        Payment payment = jpaPaymentsRepository.findByCustomerOrderId(customerOrderId);
        if (payment == null) {
            throw new PaymentException("Não encontrado pagamento para pedido: " + customerOrderId,404);
        }
        return payment;
    }
}
