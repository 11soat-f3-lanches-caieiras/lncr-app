package br.com.tp.lncr.core.adapters.payment.mercadopago;

import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lncr.core.commons.interfaces.payment.PaymentDatabase;
import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentGatewayImplTest {
    private PaymentDatabase<PaymentMercadopagoQrDTO> paymentDatabase;
    private PaymentMercadopagoQRMapper mapper;
    private PaymentGatewayImpl gateway;

    @BeforeEach
    void setUp() {
        paymentDatabase = mock(PaymentDatabase.class);
        mapper = mock(PaymentMercadopagoQRMapper.class);
        gateway = new PaymentGatewayImpl(paymentDatabase, mapper);
    }

    @Test
    void testCreatePaymentOrder() {
        PaymentMercadopagoQR payment = mock(PaymentMercadopagoQR.class);
        PaymentMercadopagoQrDTO dto = mock(PaymentMercadopagoQrDTO.class);
        when(mapper.paymentMercadopagoQrToDTO(payment)).thenReturn(dto);
        when(paymentDatabase.createPaymentCharge(dto)).thenReturn(dto);
        when(mapper.paymentMercadopagoQrToDomain(dto)).thenReturn(payment);
        assertEquals(payment, gateway.createPaymentOrder(payment));
    }

    @Test
    void testSavePayment() {
        PaymentMercadopagoQR payment = mock(PaymentMercadopagoQR.class);
        PaymentMercadopagoQrDTO dto = mock(PaymentMercadopagoQrDTO.class);
        when(mapper.paymentMercadopagoQrToDTO(payment)).thenReturn(dto);
        when(paymentDatabase.save(dto)).thenReturn(dto);
        when(mapper.paymentMercadopagoQrToDomain(dto)).thenReturn(payment);
        assertEquals(payment, gateway.savePayment(payment));
    }

    @Test
    void testGetPaymentById() {
        PaymentMercadopagoQrDTO dto = mock(PaymentMercadopagoQrDTO.class);
        PaymentMercadopagoQR payment = mock(PaymentMercadopagoQR.class);
        when(paymentDatabase.findPaymentById(1)).thenReturn(dto);
        when(mapper.paymentMercadopagoQrToDomain(dto)).thenReturn(payment);
        assertEquals(payment, gateway.getPaymentById(1));
    }

    @Test
    void testGetPaymentByCustomerOrderId() {
        PaymentMercadopagoQrDTO dto = mock(PaymentMercadopagoQrDTO.class);
        PaymentMercadopagoQR payment = mock(PaymentMercadopagoQR.class);
        when(paymentDatabase.findPaymentByCustomerOrderId(2)).thenReturn(dto);
        when(mapper.paymentMercadopagoQrToDomain(dto)).thenReturn(payment);
        assertEquals(payment, gateway.getPaymentByCustomerOrderId(2));
    }

    @Test
    void testUpdateCustomerOrderStatus() {
        gateway.updateCustomerOrderStatus(1, "PAID");
        verify(paymentDatabase).updateCustomerOrderStatus(1, "PAID");
    }

    @Test
    void testGetPaymentMercadoPagoQRList() {
        List<Integer> statusList = List.of(1,2);
        List<PaymentMercadopagoQrDTO> dtoList = List.of(mock(PaymentMercadopagoQrDTO.class));
        List<PaymentMercadopagoQR> domainList = List.of(mock(PaymentMercadopagoQR.class));
        when(paymentDatabase.findByStatusList(statusList)).thenReturn(dtoList);
        when(mapper.paymentMercadopagoQrToDomain(any())).thenReturn(domainList.get(0));
        assertEquals(domainList, gateway.getPaymentMercadoPagoQRList(statusList));
    }

    @Test
    void testSendNotification() {
        gateway.sendNotification("type", 1, "msg");
        verify(paymentDatabase).sendNotification("type", 1, "msg");
    }

    @Test
    void testCancelPaymentOrderByProviderId() {
        gateway.cancelPaymentOrderByProviderId("id");
        verify(paymentDatabase).cancelPaymentOrder("id");
    }

    @Test
    void testRefundPaymentOrderByProviderId() {
        gateway.refundPaymentOrderByProviderId("id");
        verify(paymentDatabase).refundPaymentOrder("id");
    }
}