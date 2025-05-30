package br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.entities;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JpaPaymentEntityTest {

    @Test
    void testConstructorAndGettersSetters() {
        UUID uuid = UUID.randomUUID();
        JpaPaymentEntity entity = new JpaPaymentEntity(1, 2, 10.0, uuid, "qr", 1, "pid");

        assertEquals(1, entity.getId());
        assertEquals(2, entity.getOrderId());
        assertEquals(10.0, entity.getAmount());
        assertEquals(uuid, entity.getStoreOrderId());
        assertEquals("qr", entity.getQrData());
        assertEquals(1, entity.getStatusId());
        assertEquals("pid", entity.getPaymentId());

        entity.setId(3);
        entity.setOrderId(4);
        entity.setAmount(20.0);
        UUID uuid2 = UUID.randomUUID();
        entity.setStoreOrderId(uuid2);
        entity.setQrData("qr2");
        entity.setStatusId(2);
        entity.setPaymentId("pid2");

        assertEquals(3, entity.getId());
        assertEquals(4, entity.getOrderId());
        assertEquals(20.0, entity.getAmount());
        assertEquals(uuid2, entity.getStoreOrderId());
        assertEquals("qr2", entity.getQrData());
        assertEquals(2, entity.getStatusId());
        assertEquals("pid2", entity.getPaymentId());
    }

    @Test
    void testDefaultConstructor() {
        JpaPaymentEntity entity = new JpaPaymentEntity();
        assertNull(entity.getId());
        assertNull(entity.getOrderId());
        assertNull(entity.getAmount());
        assertNull(entity.getStoreOrderId());
        assertNull(entity.getQrData());
        assertNull(entity.getStatusId());
        assertNull(entity.getPaymentId());
    }
}
