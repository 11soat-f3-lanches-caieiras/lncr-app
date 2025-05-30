package br.com.tp.lanchescaieiras.payments.mercadopago.applications.mappers;

import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.entities.JpaPaymentEntity;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.Payment;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.PaymentStatus;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentMapperTest {

    private final PaymentMapper mapper = Mappers.getMapper(PaymentMapper.class);

    @Test
    void testJpaToDomain() {
        JpaPaymentEntity entity = new JpaPaymentEntity();
        entity.setId(1);
        entity.setOrderId(2);
        entity.setAmount(10.5);
        UUID uuid = UUID.randomUUID();
        entity.setStoreOrderId(uuid);
        entity.setQrData("qr");
        entity.setStatusId(PaymentStatus.PAID.getId());
        entity.setPaymentId("pid");

        Payment payment = mapper.jpaToDomain(entity);

        assertEquals(1, payment.getId());
        assertEquals(2, payment.getOrderId());
        assertEquals(10.5, payment.getAmount());
        assertEquals(uuid, payment.getStoreOrderId());
        assertEquals("qr", payment.getQrData());
        assertEquals(PaymentStatus.PAID.getDescription(), payment.getStatus());
        assertEquals("pid", payment.getPaymentId());
    }

    @Test
    void testDomainToJpa() {
        Payment payment = new Payment();
        payment.setId(1);
        payment.setOrderId(2);
        payment.setAmount(20.0);
        UUID uuid = UUID.randomUUID();
        payment.setStoreOrderId(uuid);
        payment.setQrData("qrdata");
        payment.setStatus(PaymentStatus.CHARGED.getDescription());
        payment.setPaymentId("pid2");

        JpaPaymentEntity entity = mapper.domainToJpa(payment);

        assertEquals(1, entity.getId());
        assertEquals(2, entity.getOrderId());
        assertEquals(20.0, entity.getAmount());
        assertEquals(uuid, entity.getStoreOrderId());
        assertEquals("qrdata", entity.getQrData());
        assertEquals(PaymentStatus.CHARGED.getId(), entity.getStatusId());
        assertEquals("pid2", entity.getPaymentId());
    }
}
