package br.com.tp.lncr.core.applications.payment.usercases.mercadopago;

import br.com.tp.lncr.core.commons.enums.PaymentStatus;
import br.com.tp.lncr.core.commons.exceptions.PaymentException;
import br.com.tp.lncr.core.commons.interfaces.payment.PaymentGateway;
import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdatePaymentMercadoPagoQRUseCaseTest {
    private PaymentGateway paymentGateway;
    private UpdatePaymentMercadoPagoQRUseCase useCase;

    @BeforeEach
    void setUp() {
        paymentGateway = mock(PaymentGateway.class);
        useCase = new UpdatePaymentMercadoPagoQRUseCase(paymentGateway);
    }

    @Test
    void deveCancelarPagamentoCharged() {
        PaymentMercadopagoQR payment = mock(PaymentMercadopagoQR.class);
        when(paymentGateway.getPaymentByCustomerOrderId(1)).thenReturn(payment);
        when(payment.getStatus()).thenReturn(PaymentStatus.CHARGED.getDescription());
        when(paymentGateway.savePayment(payment)).thenReturn(payment);
        when(payment.getMeliId()).thenReturn("abc");
        when(payment.getOrderId()).thenReturn(1);
        PaymentMercadopagoQR result = useCase.cancelByCustomerOrderId(1);
        assertEquals(payment, result);
        verify(paymentGateway).cancelPaymentOrderByProviderId("abc");
        verify(paymentGateway).sendNotification(eq("PAYMENT_MERCADOPAGO_QR_CANCELLED"), eq(1), anyString());
    }

    @Test
    void deveCancelarPagamentoPaid() {
        PaymentMercadopagoQR payment = mock(PaymentMercadopagoQR.class);
        when(paymentGateway.getPaymentByCustomerOrderId(2)).thenReturn(payment);
        when(payment.getStatus()).thenReturn(PaymentStatus.PAID.getDescription());
        when(paymentGateway.savePayment(payment)).thenReturn(payment);
        when(payment.getMeliId()).thenReturn("def");
        when(payment.getOrderId()).thenReturn(2);
        PaymentMercadopagoQR result = useCase.cancelByCustomerOrderId(2);
        assertEquals(payment, result);
        verify(paymentGateway).refundPaymentOrderByProviderId("def");
        verify(paymentGateway).sendNotification(eq("PAYMENT_MERCADOPAGO_QR_REFUND"), eq(2), anyString());
    }

    @Test
    void deveLancarExcecaoSePagamentoNaoEncontrado() {
        when(paymentGateway.getPaymentByCustomerOrderId(3)).thenReturn(null);
        assertThrows(PaymentException.class, () -> useCase.cancelByCustomerOrderId(3));
    }

    @Test
    void deveProcessarPagamentoRecebidoComSucesso() {
        PaymentMercadopagoQR payment = mock(PaymentMercadopagoQR.class);
        when(paymentGateway.getPaymentByCustomerOrderId(10)).thenReturn(payment);
        when(payment.getMeliId()).thenReturn("xyz");
        when(payment.getStatus()).thenReturn(PaymentStatus.CHARGED.getDescription());
        when(payment.getOrderId()).thenReturn(10);
        when(paymentGateway.savePayment(payment)).thenReturn(payment);
        Map<String, Object> data = new HashMap<>();
        data.put("status", "processed");
        data.put("status_detail", "accredited");
        Map<String, Object> body = new HashMap<>();
        body.put("data", data);
        PaymentMercadopagoQR result = useCase.processPaymentReceived("10", "xyz", body);
        assertEquals(payment, result);
        verify(paymentGateway).updateCustomerOrderStatus(10, "Received");
        verify(paymentGateway).sendNotification(eq("PAYMENT_MERCADOPAGO_QR_PAID"), eq(10), anyString());
    }

    @Test
    void deveLancarExcecaoSeStatusInvalidoAoProcessarPagamento() {
        PaymentMercadopagoQR payment = mock(PaymentMercadopagoQR.class);
        when(paymentGateway.getPaymentByCustomerOrderId(11)).thenReturn(payment);
        when(payment.getMeliId()).thenReturn("xyz");
        when(payment.getStatus()).thenReturn(PaymentStatus.PAID.getDescription());
        Map<String, Object> data = new HashMap<>();
        data.put("status", "processed");
        data.put("status_detail", "accredited");
        Map<String, Object> body = new HashMap<>();
        body.put("data", data);
        assertThrows(PaymentException.class, () -> useCase.processPaymentReceived("11", "xyz", body));
    }

    @Test
    void deveLancarExcecaoSePagamentoNaoEncontradoAoProcessar() {
        when(paymentGateway.getPaymentByCustomerOrderId(12)).thenReturn(null);
        Map<String, Object> data = new HashMap<>();
        data.put("status", "processed");
        data.put("status_detail", "accredited");
        Map<String, Object> body = new HashMap<>();
        body.put("data", data);
        assertThrows(PaymentException.class, () -> useCase.processPaymentReceived("12", "xyz", body));
    }

    @Test
    void deveLancarExcecaoSeBodyInvalidoAoProcessar() {
        Map<String, Object> body = new HashMap<>();
        assertThrows(PaymentException.class, () -> useCase.processPaymentReceived("13", "xyz", body));
    }
}

