package br.com.tp.lncr.app.apis;

import br.com.tp.lncr.app.apis.payment.PaymentMercadoPagoQrRestRestControllerImpl;
import br.com.tp.lncr.app.commons.model.ResponseListModel;
import br.com.tp.lncr.app.commons.model.ResponseModel;
import br.com.tp.lncr.app.configs.MercadoPagoConfig;
import br.com.tp.lncr.app.dataproxy.PaymentMercadoPagoQrDataProxy;
import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lncr.core.commons.interfaces.payment.PaymentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentMercadoPagoQrRestRestControllerImplTest {

    @Mock
    private PaymentController<PaymentMercadopagoQrDTO> paymentMercadoPagoQrController;

    @Mock
    private PaymentMercadoPagoQrDataProxy paymentMercadoPagoQrDataProxy;

    @Mock
    private MercadoPagoConfig mercadoPagoConfig;

    @InjectMocks
    private PaymentMercadoPagoQrRestRestControllerImpl paymentRestController;

    private PaymentMercadopagoQrDTO paymentDTO;

    @BeforeEach
    void setUp() {
        paymentDTO = new PaymentMercadopagoQrDTO();
        paymentDTO.setId(1);
        paymentDTO.setOrderId(1);
        paymentDTO.setAmount(25.99);
        paymentDTO.setStatus("PENDING");
        paymentDTO.set_created(LocalDateTime.now());
        paymentDTO.setExternalPaymentId("ORDER_001");
    }

    @Test
    void deveCriarPaymentChargeComSucesso() {
        when(mercadoPagoConfig.getLocationPrefix()).thenReturn("/payments/mercadoPago");
        when(paymentMercadoPagoQrController.createPaymentCharge(paymentMercadoPagoQrDataProxy, paymentDTO)).thenReturn(paymentDTO);

        ResponseEntity<ResponseModel<PaymentMercadopagoQrDTO>> response = paymentRestController.createPaymentCharge(paymentDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().get_content());
        assertNotNull(response.getHeaders().getLocation());
        assertEquals("/payments/mercadoPago/1", response.getHeaders().getLocation().toString());
        verify(paymentMercadoPagoQrController).createPaymentCharge(paymentMercadoPagoQrDataProxy, paymentDTO);
    }

    @Test
    void deveRetornarPaymentPorId() {
        when(paymentMercadoPagoQrController.getPaymentById(paymentMercadoPagoQrDataProxy, 1)).thenReturn(paymentDTO);

        ResponseEntity<ResponseModel<PaymentMercadopagoQrDTO>> response = paymentRestController.getPaymentById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(paymentDTO, response.getBody().get_content());
        verify(paymentMercadoPagoQrController).getPaymentById(paymentMercadoPagoQrDataProxy, 1);
    }

    @Test
    void deveRetornarPaymentPorCustomerOrderId() {
        when(paymentMercadoPagoQrController.getPaymentByCustomerOrderId(paymentMercadoPagoQrDataProxy, 1)).thenReturn(paymentDTO);

        ResponseEntity<ResponseModel<PaymentMercadopagoQrDTO>> response = paymentRestController.getPaymentByCustomerOrderId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(paymentDTO, response.getBody().get_content());
        verify(paymentMercadoPagoQrController).getPaymentByCustomerOrderId(paymentMercadoPagoQrDataProxy, 1);
    }

    @Test
    void deveCancelarPaymentPorCustomerOrderId() {
        PaymentMercadopagoQrDTO cancelledPayment = new PaymentMercadopagoQrDTO();
        cancelledPayment.setId(1);
        cancelledPayment.setStatus("CANCELLED");
        when(paymentMercadoPagoQrController.cancelPaymentByOrderId(paymentMercadoPagoQrDataProxy, 1)).thenReturn(cancelledPayment);

        ResponseEntity<ResponseModel<PaymentMercadopagoQrDTO>> response = paymentRestController.cancelPaymentByCustomerOrderId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(cancelledPayment, response.getBody().get_content());
        assertEquals("CANCELLED", response.getBody().get_content().getStatus());
        verify(paymentMercadoPagoQrController).cancelPaymentByOrderId(paymentMercadoPagoQrDataProxy, 1);
    }

    @Test
    void deveProcessarPaymentReceivedComSucesso() {
        Map<String, Object> webhookBody = new HashMap<>();
        webhookBody.put("action", "payment.updated");
        webhookBody.put("data", Map.of("id", "12345"));

        PaymentMercadopagoQrDTO processedPayment = new PaymentMercadopagoQrDTO();
        processedPayment.setId(1);
        processedPayment.setStatus("APPROVED");
        when(paymentMercadoPagoQrController.processPaymentReceived(paymentMercadoPagoQrDataProxy, "ORDER_001", "12345", webhookBody)).thenReturn(processedPayment);

        ResponseEntity<ResponseModel<PaymentMercadopagoQrDTO>> response = paymentRestController.processPaymentReceived("ORDER_001", "12345", "order", webhookBody);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(processedPayment, response.getBody().get_content());
        assertEquals("APPROVED", response.getBody().get_content().getStatus());
        verify(paymentMercadoPagoQrController).processPaymentReceived(paymentMercadoPagoQrDataProxy, "ORDER_001", "12345", webhookBody);
    }

    @Test
    void deveRetornarPaymentsPorStatusList() {
        List<String> statusList = Arrays.asList("PENDING", "APPROVED");
        List<PaymentMercadopagoQrDTO> payments = Collections.singletonList(paymentDTO);
        when(paymentMercadoPagoQrController.getPaymentByStatusList(paymentMercadoPagoQrDataProxy, statusList)).thenReturn(payments);

        ResponseEntity<ResponseListModel<PaymentMercadopagoQrDTO>> response = paymentRestController.getPaymentByStatusList(statusList);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(payments, response.getBody().get_content());
        verify(paymentMercadoPagoQrController).getPaymentByStatusList(paymentMercadoPagoQrDataProxy, statusList);
    }

    @Test
    void deveProcessarWebhookComTipoCustomizado() {
        Map<String, Object> webhookBody = new HashMap<>();
        webhookBody.put("action", "payment.created");
        webhookBody.put("data", Map.of("id", "67890"));

        PaymentMercadopagoQrDTO processedPayment = new PaymentMercadopagoQrDTO();
        processedPayment.setId(1);
        processedPayment.setStatus("PENDING");
        when(paymentMercadoPagoQrController.processPaymentReceived(paymentMercadoPagoQrDataProxy, "ORDER_002", "67890", webhookBody)).thenReturn(processedPayment);

        ResponseEntity<ResponseModel<PaymentMercadopagoQrDTO>> response = paymentRestController.processPaymentReceived("ORDER_002", "67890", "payment", webhookBody);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(processedPayment, response.getBody().get_content());
        verify(paymentMercadoPagoQrController).processPaymentReceived(paymentMercadoPagoQrDataProxy, "ORDER_002", "67890", webhookBody);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverPaymentsComStatus() {
        List<String> statusList = Collections.singletonList("NONEXISTENT");
        List<PaymentMercadopagoQrDTO> emptyPayments = Collections.emptyList();
        when(paymentMercadoPagoQrController.getPaymentByStatusList(paymentMercadoPagoQrDataProxy, statusList)).thenReturn(emptyPayments);

        ResponseEntity<ResponseListModel<PaymentMercadopagoQrDTO>> response = paymentRestController.getPaymentByStatusList(statusList);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().get_content().isEmpty());
        verify(paymentMercadoPagoQrController).getPaymentByStatusList(paymentMercadoPagoQrDataProxy, statusList);
    }

    @Test
    void deveCriarPaymentChargeComValorZero() {
        when(mercadoPagoConfig.getLocationPrefix()).thenReturn("/payments/mercadoPago");

        PaymentMercadopagoQrDTO zeroValuePayment = new PaymentMercadopagoQrDTO();
        zeroValuePayment.setId(2);
        zeroValuePayment.setOrderId(2);
        zeroValuePayment.setAmount(0.00);
        zeroValuePayment.setStatus("PENDING");

        when(paymentMercadoPagoQrController.createPaymentCharge(paymentMercadoPagoQrDataProxy, zeroValuePayment)).thenReturn(zeroValuePayment);

        ResponseEntity<ResponseModel<PaymentMercadopagoQrDTO>> response = paymentRestController.createPaymentCharge(zeroValuePayment);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getHeaders().getLocation());
        assertEquals("/payments/mercadoPago/2", response.getHeaders().getLocation().toString());
        verify(paymentMercadoPagoQrController).createPaymentCharge(paymentMercadoPagoQrDataProxy, zeroValuePayment);
    }

    @Test
    void deveProcessarWebhookComBodyVazio() {
        Map<String, Object> emptyBody = new HashMap<>();
        PaymentMercadopagoQrDTO processedPayment = new PaymentMercadopagoQrDTO();
        processedPayment.setId(1);

        when(paymentMercadoPagoQrController.processPaymentReceived(paymentMercadoPagoQrDataProxy, "ORDER_003", "99999", emptyBody)).thenReturn(processedPayment);

        ResponseEntity<ResponseModel<PaymentMercadopagoQrDTO>> response = paymentRestController.processPaymentReceived("ORDER_003", "99999", "order", emptyBody);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(processedPayment, response.getBody().get_content());
        verify(paymentMercadoPagoQrController).processPaymentReceived(paymentMercadoPagoQrDataProxy, "ORDER_003", "99999", emptyBody);
    }
}
