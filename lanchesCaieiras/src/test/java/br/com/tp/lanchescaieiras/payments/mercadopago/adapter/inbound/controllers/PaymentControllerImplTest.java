package br.com.tp.lanchescaieiras.payments.mercadopago.adapter.inbound.controllers;

import br.com.tp.lanchescaieiras.payments.mercadopago.applications.services.PaymentServiceImpl;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.Payment;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.PaymentResponse;
import br.com.tp.lanchescaieiras.payments.mercadopago.infraestructure.config.MercadoPagoConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PaymentControllerImplTest {

    private PaymentServiceImpl paymentService;
    private MercadoPagoConfig mercadoPagoConfig;
    private PaymentControllerImpl controller;

    @BeforeEach
    void setUp() {
        paymentService = mock(PaymentServiceImpl.class);
        mercadoPagoConfig = mock(MercadoPagoConfig.class);
        controller = new PaymentControllerImpl(paymentService, mercadoPagoConfig);
    }

    @Test
    void testCreateCharge() {
        Payment payment = new Payment();
        payment.setId(123);
        when(paymentService.createCharge(any())).thenReturn(payment);
        when(mercadoPagoConfig.getLocationPrefix()).thenReturn("/payments/mercadoPago");

        ResponseEntity<PaymentResponse> response = controller.createCharge(payment);

        assertEquals(201, response.getStatusCodeValue());
        assertTrue(response.getHeaders().containsKey("Location"));
        assertNotNull(response.getBody());
        assertEquals(123, response.getBody().getPayment().getId());
    }

    @Test
    void testPaymentRecived() {
        Payment payment = new Payment();
        when(paymentService.updatePaymentByPaymentId(anyString())).thenReturn(payment);

        ResponseEntity<PaymentResponse> response = controller.paymentRecived("pid", "type");

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetPaymentByCustomerOrderId() {
        Payment payment = new Payment();
        when(paymentService.findByCustomerOrderId(anyInt())).thenReturn(payment);

        ResponseEntity<PaymentResponse> response = controller.getPaymentByCustomerOrderId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}
