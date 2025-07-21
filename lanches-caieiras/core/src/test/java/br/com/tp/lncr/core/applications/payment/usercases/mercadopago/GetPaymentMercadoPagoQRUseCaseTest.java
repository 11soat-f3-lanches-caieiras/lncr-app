package br.com.tp.lncr.core.applications.payment.usercases.mercadopago;

import br.com.tp.lncr.core.commons.enums.PaymentStatus;
import br.com.tp.lncr.core.commons.exceptions.PaymentException;
import br.com.tp.lncr.core.commons.interfaces.payment.PaymentGateway;
import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class GetPaymentMercadoPagoQRUseCaseTest {
    private PaymentGateway paymentGateway;
    private GetPaymentMercadoPagoQRUseCase useCase;

    @BeforeEach
    void setUp() {
        paymentGateway = mock(PaymentGateway.class);
        useCase = new GetPaymentMercadoPagoQRUseCase(paymentGateway);
    }

    @Test
    void deveBuscarPagamentoPorIdComSucesso() {
        PaymentMercadopagoQR payment = mock(PaymentMercadopagoQR.class);
        when(paymentGateway.getPaymentById(1)).thenReturn(payment);
        PaymentMercadopagoQR result = useCase.getById(1);
        assertEquals(payment, result);
    }

    @Test
    void deveLancarExcecaoSePagamentoPorIdNaoEncontrado() {
        when(paymentGateway.getPaymentById(2)).thenReturn(null);
        assertThrows(PaymentException.class, () -> useCase.getById(2));
    }

    @Test
    void deveBuscarPagamentoPorCustomerOrderIdComSucesso() {
        PaymentMercadopagoQR payment = mock(PaymentMercadopagoQR.class);
        when(paymentGateway.getPaymentByCustomerOrderId(3)).thenReturn(payment);
        PaymentMercadopagoQR result = useCase.getByCustomerOrderId(3);
        assertEquals(payment, result);
    }

    @Test
    void deveLancarExcecaoSePagamentoPorCustomerOrderIdNaoEncontrado() {
        when(paymentGateway.getPaymentByCustomerOrderId(4)).thenReturn(null);
        assertThrows(PaymentException.class, () -> useCase.getByCustomerOrderId(4));
    }

    @Test
    void deveBuscarPagamentosPorStatusComSucesso() {
        List<String> statusList = Arrays.asList(PaymentStatus.CHARGED.getDescription());
        PaymentMercadopagoQR payment = mock(PaymentMercadopagoQR.class);
        when(paymentGateway.getPaymentMercadoPagoQRList(anyList())).thenReturn(Arrays.asList(payment));
        List<PaymentMercadopagoQR> result = useCase.getByStatusList(statusList);
        assertEquals(1, result.size());
        assertEquals(payment, result.get(0));
    }

    @Test
    void deveLancarExcecaoSeNenhumPagamentoEncontradoPorStatus() {
        List<String> statusList = Collections.singletonList(PaymentStatus.CANCELLED.getDescription());
        when(paymentGateway.getPaymentMercadoPagoQRList(anyList())).thenReturn(null);
        assertThrows(PaymentException.class, () -> useCase.getByStatusList(statusList));
    }
}

