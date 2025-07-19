package br.com.tp.lncr.core.adapters.payment.mercadopago;

import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lncr.core.commons.interfaces.payment.PaymentDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class PaymentMercadoPagoQrControllerImplTest {
    private PaymentMercadopagoQRMapper mapper;
    private PaymentMercadoPagoQrControllerImpl controller;
    private PaymentDatabase paymentDatabase;
    private PaymentMercadopagoQrDTO dto;

    @BeforeEach
    void setUp() {
        mapper = mock(PaymentMercadopagoQRMapper.class);
        controller = new PaymentMercadoPagoQrControllerImpl(mapper);
        paymentDatabase = mock(PaymentDatabase.class);
        dto = mock(PaymentMercadopagoQrDTO.class);
    }

    @Test
    void testCreatePaymentCharge() {
        PaymentMercadopagoQrDTO input = mock(PaymentMercadopagoQrDTO.class);
        PaymentMercadopagoQrDTO output = controller.createPaymentCharge(paymentDatabase, input);
        assertNotNull(output);
    }

    @Test
    void testGetPaymentById() {
        PaymentMercadopagoQrDTO output = controller.getPaymentById(paymentDatabase, 1);
        assertNotNull(output);
    }

    @Test
    void testGetPaymentByCustomerOrderId() {
        PaymentMercadopagoQrDTO output = controller.getPaymentByCustomerOrderId(paymentDatabase, 2);
        assertNotNull(output);
    }

    @Test
    void testCancelPaymentByOrderId() {
        PaymentMercadopagoQrDTO output = controller.cancelPaymentByOrderId(paymentDatabase, 3);
        assertNotNull(output);
    }

    @Test
    void testGetPaymentByStatusList() {
        List<String> statusList = List.of("PAID", "CANCELLED");
        List<PaymentMercadopagoQrDTO> output = controller.getPaymentByStatusList(paymentDatabase, statusList);
        assertNotNull(output);
    }

    @Test
    void testProcessPaymentReceived() {
        Map<String, Object> body = Map.of("key", "value");
        PaymentMercadopagoQrDTO output = controller.processPaymentReceived(paymentDatabase, "ext", "data", body);
        assertNotNull(output);
    }
}

