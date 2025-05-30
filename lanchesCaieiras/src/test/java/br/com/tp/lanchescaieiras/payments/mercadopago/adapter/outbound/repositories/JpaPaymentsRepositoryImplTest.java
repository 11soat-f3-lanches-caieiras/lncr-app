package br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.repositories;

import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.entities.JpaPaymentEntity;
import br.com.tp.lanchescaieiras.payments.mercadopago.applications.mappers.PaymentMapper;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JpaPaymentsRepositoryImplTest {

    private JpaPaymentRepository jpaPaymentRepository;
    private PaymentMapper paymentMapper;
    private JpaPaymentsRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        jpaPaymentRepository = mock(JpaPaymentRepository.class);
        paymentMapper = mock(PaymentMapper.class);
        repository = new JpaPaymentsRepositoryImpl(jpaPaymentRepository, paymentMapper);
    }

    @Test
    void testCreateCharge() {
        Payment payment = new Payment();
        JpaPaymentEntity entity = new JpaPaymentEntity();
        when(paymentMapper.domainToJpa(payment)).thenReturn(entity);
        when(jpaPaymentRepository.save(entity)).thenReturn(entity);
        when(paymentMapper.jpaToDomain(entity)).thenReturn(payment);

        Payment result = repository.createCharge(payment);
        assertEquals(payment, result);
    }

    @Test
    void testFindByIdFound() {
        JpaPaymentEntity entity = new JpaPaymentEntity();
        Payment payment = new Payment();
        when(jpaPaymentRepository.findById(1)).thenReturn(Optional.of(entity));
        when(paymentMapper.jpaToDomain(entity)).thenReturn(payment);

        Payment result = repository.findById(1);
        assertEquals(payment, result);
    }

    @Test
    void testFindByIdNotFound() {
        when(jpaPaymentRepository.findById(1)).thenReturn(Optional.empty());
        Payment result = repository.findById(1);
        assertNull(result);
    }

    @Test
    void testFindByCustomerOrderIdFound() {
        JpaPaymentEntity entity = new JpaPaymentEntity();
        Payment payment = new Payment();
        when(jpaPaymentRepository.findByCustomerOrOrderId(2)).thenReturn(Optional.of(entity));
        when(paymentMapper.jpaToDomain(entity)).thenReturn(payment);

        Payment result = repository.findByCustomerOrderId(2);
        assertEquals(payment, result);
    }

    @Test
    void testFindByCustomerOrderIdNotFound() {
        when(jpaPaymentRepository.findByCustomerOrOrderId(2)).thenReturn(Optional.empty());
        Payment result = repository.findByCustomerOrderId(2);
        assertNull(result);
    }

    @Test
    void testUpdate() {
        Payment payment = new Payment();
        JpaPaymentEntity entity = new JpaPaymentEntity();
        when(paymentMapper.domainToJpa(payment)).thenReturn(entity);
        when(jpaPaymentRepository.save(entity)).thenReturn(entity);
        when(paymentMapper.jpaToDomain(entity)).thenReturn(payment);

        Payment result = repository.update(payment);
        assertEquals(payment, result);
    }
}
