package br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.repositories;

import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.entities.JpaPaymentEntity;
import br.com.tp.lanchescaieiras.payments.mercadopago.applications.mappers.PaymentMapper;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.Payment;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.PaymentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaPaymentsRepositoryImpl implements PaymentRepository {

    public final JpaPaymentRepository jpaPaymentRepository;
    public final PaymentMapper paymentMapper;

    public JpaPaymentsRepositoryImpl(JpaPaymentRepository jpaPaymentRepository, PaymentMapper paymentMapper) {
        this.jpaPaymentRepository = jpaPaymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public Payment createCharge(Payment payment) {
        JpaPaymentEntity jpaPaymentEntity = paymentMapper.domainToJpa(payment);
        return paymentMapper.jpaToDomain(jpaPaymentRepository.save(jpaPaymentEntity));
    }

    @Override
    public Payment findById(Integer externalReferenceId) {
        JpaPaymentEntity jpaPaymentEntity = jpaPaymentRepository.findById(externalReferenceId).orElse(null);
        return paymentMapper.jpaToDomain(jpaPaymentEntity);
    }

    @Override
    public Payment update(Payment payment) {
        JpaPaymentEntity jpaPaymentEntity = paymentMapper.domainToJpa(payment);
        return paymentMapper.jpaToDomain(jpaPaymentRepository.save(jpaPaymentEntity));
    }
}
