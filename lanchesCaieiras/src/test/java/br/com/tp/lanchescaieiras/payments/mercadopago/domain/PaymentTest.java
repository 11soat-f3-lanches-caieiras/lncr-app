package br.com.tp.lanchescaieiras.payments.mercadopago.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testConstructorAndGettersSetters() {
        UUID uuid = UUID.randomUUID();
        Payment payment = new Payment(1, 2, 10.5, uuid, "qr", "Paid", "pid");

        assertEquals(1, payment.getId());
        assertEquals(2, payment.getOrderId());
        assertEquals(10.5, payment.getAmount());
        assertEquals(uuid, payment.getStoreOrderId());
        assertEquals("qr", payment.getQrData());
        assertEquals("Paid", payment.getStatus());
        assertEquals("pid", payment.getPaymentId());

        payment.setId(2);
        payment.setOrderId(3);
        payment.setAmount(20.0);
        UUID uuid2 = UUID.randomUUID();
        payment.setStoreOrderId(uuid2);
        payment.setQrData("qr2");
        payment.setStatus("Charged");
        payment.setPaymentId("pid2");

        assertEquals(2, payment.getId());
        assertEquals(3, payment.getOrderId());
        assertEquals(20.0, payment.getAmount());
        assertEquals(uuid2, payment.getStoreOrderId());
        assertEquals("qr2", payment.getQrData());
        assertEquals("Charged", payment.getStatus());
        assertEquals("pid2", payment.getPaymentId());
    }
}
