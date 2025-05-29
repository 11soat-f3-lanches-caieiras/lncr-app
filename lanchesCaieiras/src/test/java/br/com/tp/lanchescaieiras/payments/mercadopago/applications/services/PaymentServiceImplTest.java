package br.com.tp.lanchescaieiras.payments.mercadopago.applications.services;

import br.com.tp.lanchescaieiras.commons.adapters.outbound.integrations.CustomerOrderIntegrationImpl;
import br.com.tp.lanchescaieiras.commons.domain.Notification;
import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.integration.MercadoPagoIntegrationImpl;
import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.repositories.JpaPaymentsRepositoryImpl;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.Payment;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.PaymentStatus;
import br.com.tp.lanchescaieiras.payments.mercadopago.infraestructure.exceptions.PaymentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {

    private JpaPaymentsRepositoryImpl jpaPaymentsRepository;
    private MercadoPagoIntegrationImpl mercadoPagoIntegration;
    private CustomerOrderIntegrationImpl customerOrderIntegration;
    private ApplicationEventPublisher eventPublisher;
    private PaymentServiceImpl service;

    @BeforeEach
    void setUp() {
        jpaPaymentsRepository = mock(JpaPaymentsRepositoryImpl.class);
        mercadoPagoIntegration = mock(MercadoPagoIntegrationImpl.class);
        customerOrderIntegration = mock(CustomerOrderIntegrationImpl.class);
        eventPublisher = mock(ApplicationEventPublisher.class);
        service = new PaymentServiceImpl(jpaPaymentsRepository, mercadoPagoIntegration, customerOrderIntegration, eventPublisher);
    }

    @Test
    void testCreateCharge() {
        Payment payment = new Payment();
        payment.setOrderId(1);
        payment.setAmount(10.0);

        when(jpaPaymentsRepository.createCharge(any())).thenAnswer(inv -> inv.getArgument(0));
        when(mercadoPagoIntegration.createQRCode(any())).thenAnswer(inv -> inv.getArgument(0));

        Payment result = service.createCharge(payment);

        assertEquals(PaymentStatus.CHARGED.getDescription(), result.getStatus());
        verify(jpaPaymentsRepository, times(2)).createCharge(any());
        verify(mercadoPagoIntegration, times(1)).createQRCode(any());
        verify(eventPublisher, times(1)).publishEvent(any(Notification.class));
    }

    @Test
    void testUpdatePaymentByPaymentIdNotFoundExternalReference() {
        when(mercadoPagoIntegration.getPaymentId("pid")).thenReturn(null);
        PaymentException ex = assertThrows(PaymentException.class, () -> service.updatePaymentByPaymentId("pid"));
        assertTrue(ex.getMessage().contains("não encontrado"));
    }

    @Test
    void testUpdatePaymentByPaymentIdNotFoundPayment() {
        when(mercadoPagoIntegration.getPaymentId("pid")).thenReturn(1);
        when(jpaPaymentsRepository.findById(1)).thenReturn(null);
        PaymentException ex = assertThrows(PaymentException.class, () -> service.updatePaymentByPaymentId("pid"));
        assertTrue(ex.getMessage().contains("Não encontrado pedido"));
    }

    @Test
    void testUpdatePaymentByPaymentIdStatusCharged() {
        Payment payment = new Payment();
        payment.setOrderId(2);
        payment.setStatus(PaymentStatus.CHARGED.getDescription());
        when(mercadoPagoIntegration.getPaymentId("pid")).thenReturn(2);
        when(jpaPaymentsRepository.findById(2)).thenReturn(payment);
        when(jpaPaymentsRepository.update(any())).thenAnswer(inv -> inv.getArgument(0));

        Payment result = service.updatePaymentByPaymentId("pid");

        assertEquals(PaymentStatus.PAID.getDescription(), result.getStatus());
        assertNull(result.getQrData());
        assertNull(result.getStoreOrderId());
        verify(jpaPaymentsRepository, times(1)).update(any());
        verify(customerOrderIntegration, times(1)).updateCustomerOrderStatus(eq(2), eq("Received"));
        verify(eventPublisher, times(1)).publishEvent(any(Notification.class));
    }

    @Test
    void testFindByCustomerOrderIdFound() {
        Payment payment = new Payment();
        when(jpaPaymentsRepository.findByCustomerOrderId(5)).thenReturn(payment);

        Payment result = service.findByCustomerOrderId(5);
        assertEquals(payment, result);
    }

    @Test
    void testFindByCustomerOrderIdNotFound() {
        when(jpaPaymentsRepository.findByCustomerOrderId(5)).thenReturn(null);
        PaymentException ex = assertThrows(PaymentException.class, () -> service.findByCustomerOrderId(5));
        assertTrue(ex.getMessage().contains("Não encontrado pagamento"));
    }
}
