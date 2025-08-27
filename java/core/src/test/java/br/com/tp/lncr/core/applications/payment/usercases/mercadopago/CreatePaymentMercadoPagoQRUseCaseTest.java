package br.com.tp.lncr.core.applications.payment.usercases.mercadopago;

import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lncr.core.commons.exceptions.PaymentException;
import br.com.tp.lncr.core.commons.interfaces.payment.PaymentGateway;
import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreatePaymentMercadoPagoQRUseCaseTest {
    private PaymentGateway paymentGateway;
    private CreatePaymentMercadoPagoQRUseCase useCase;

    @BeforeEach
    void setUp() {
        paymentGateway = mock(PaymentGateway.class);
        useCase = new CreatePaymentMercadoPagoQRUseCase(paymentGateway);
    }

    @Test
    void deveCriarCobrancaComSucesso() {
        PaymentMercadopagoQrDTO dto = mock(PaymentMercadopagoQrDTO.class);
        when(dto.getOrderId()).thenReturn(1);
        when(paymentGateway.getPaymentByCustomerOrderId(1)).thenReturn(null);
        PaymentMercadopagoQR payment = mock(PaymentMercadopagoQR.class);
        when(payment.getOrderId()).thenReturn(1);
        when(paymentGateway.createPaymentOrder(any())).thenReturn(payment);
        PaymentMercadopagoQR result = useCase.createCharge(dto);
        assertEquals(payment, result);
        verify(paymentGateway).sendNotification(eq("PAYMENT_MERCADOPAGO_QR_CHECKOUT"), eq(1), anyString());
    }

    @Test
    void deveLancarExcecaoSeCobrancaJaExiste() {
        PaymentMercadopagoQrDTO dto = mock(PaymentMercadopagoQrDTO.class);
        when(dto.getOrderId()).thenReturn(2);
        when(paymentGateway.getPaymentByCustomerOrderId(2)).thenReturn(mock(PaymentMercadopagoQR.class));
        assertThrows(PaymentException.class, () -> useCase.createCharge(dto));
    }
}

