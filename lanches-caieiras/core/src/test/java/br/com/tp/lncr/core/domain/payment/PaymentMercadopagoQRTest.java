package br.com.tp.lncr.core.domain.payment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class PaymentMercadopagoQRTest {
    @Test
    void testConstructorAndGetters() {
        PaymentMercadopagoQR payment = new PaymentMercadopagoQR(1, 10, "Aprovado", 50.0, "ext123", LocalDateTime.now(), LocalDateTime.now(), "meli123", "qrdata");
        Assertions.assertEquals(1, payment.getId());
        Assertions.assertEquals(10, payment.getOrderId());
        Assertions.assertEquals("Aprovado", payment.getStatus());
        Assertions.assertEquals(50.0, payment.getAmount());
        Assertions.assertEquals("ext123", payment.getExternalPaymentId());
        Assertions.assertEquals("meli123", payment.getMeliId());
        Assertions.assertEquals("qrdata", payment.getQrData());
        Assertions.assertEquals("mercadopago", payment.getPaymentProvider());
        Assertions.assertEquals("qrcode", payment.getPaymentMethod());
    }

    @Test
    void testSetters() {
        PaymentMercadopagoQR payment = new PaymentMercadopagoQR(2, 20, "CHARGED", 100.0, "ext456", LocalDateTime.now(), LocalDateTime.now(), "meli456", "qrdata2");
        payment.setId(3);
        payment.setOrderId(30);
        payment.setStatus("Cancelled");
        payment.setAmount(200.0);
        payment.setExternalPaymentId("ext789");
        payment.setMeliId("meli789");
        payment.setQrData("qrdata3");
        Assertions.assertEquals(3, payment.getId());
        Assertions.assertEquals(30, payment.getOrderId());
        Assertions.assertEquals("Cancelled", payment.getStatus());
        Assertions.assertEquals(200.0, payment.getAmount());
        Assertions.assertEquals("ext789", payment.getExternalPaymentId());
        Assertions.assertEquals("meli789", payment.getMeliId());
        Assertions.assertEquals("qrdata3", payment.getQrData());
    }
}

