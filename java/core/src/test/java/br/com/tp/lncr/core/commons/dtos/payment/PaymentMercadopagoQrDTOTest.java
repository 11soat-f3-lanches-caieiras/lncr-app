package br.com.tp.lncr.core.commons.dtos.payment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PaymentMercadopagoQrDTOTest {
    @Test
    void createPaymentMercadopagoQrDTOWithAllFields() {
        LocalDateTime now = LocalDateTime.now();
        PaymentMercadopagoQrDTO dto = new PaymentMercadopagoQrDTO(1, 2, "PAID", 100.0, "MP", "QR", now, now, "extId", "qrdata", "meliid");
        Assertions.assertEquals(1, dto.getId());
        Assertions.assertEquals(2, dto.getOrderId());
        Assertions.assertEquals("PAID", dto.getStatus());
        Assertions.assertEquals(100.0, dto.getAmount());
        Assertions.assertEquals("MP", dto.getPaymentProvider());
        Assertions.assertEquals("QR", dto.getPaymentMethod());
        Assertions.assertEquals(now, dto.get_created());
        Assertions.assertEquals(now, dto.get_updated());
        Assertions.assertEquals("extId", dto.getExternalPaymentId());
        Assertions.assertEquals("qrdata", dto.getQrData());
        Assertions.assertEquals("meliid", dto.getMeliId());
    }

    @Test
    void setAndGetFieldsIndividually() {
        PaymentMercadopagoQrDTO dto = new PaymentMercadopagoQrDTO();
        dto.setId(3);
        dto.setOrderId(4);
        dto.setStatus("PENDING");
        dto.setAmount(200.0);
        dto.setPaymentProvider("MP");
        dto.setPaymentMethod("QR");
        LocalDateTime now = LocalDateTime.now();
        dto.set_created(now);
        dto.set_updated(now);
        dto.setExternalPaymentId("ext2");
        dto.setQrData("qr2");
        dto.setMeliId("meli2");
        Assertions.assertEquals(3, dto.getId());
        Assertions.assertEquals(4, dto.getOrderId());
        Assertions.assertEquals("PENDING", dto.getStatus());
        Assertions.assertEquals(200.0, dto.getAmount());
        Assertions.assertEquals("MP", dto.getPaymentProvider());
        Assertions.assertEquals("QR", dto.getPaymentMethod());
        Assertions.assertEquals(now, dto.get_created());
        Assertions.assertEquals(now, dto.get_updated());
        Assertions.assertEquals("ext2", dto.getExternalPaymentId());
        Assertions.assertEquals("qr2", dto.getQrData());
        Assertions.assertEquals("meli2", dto.getMeliId());
    }

    @Test
    void allowNullFields() {
        PaymentMercadopagoQrDTO dto = new PaymentMercadopagoQrDTO(null, null, null, null, null, null, null, null, null, null, null);
        Assertions.assertNull(dto.getId());
        Assertions.assertNull(dto.getOrderId());
        Assertions.assertNull(dto.getStatus());
        Assertions.assertNull(dto.getAmount());
        Assertions.assertNull(dto.getPaymentProvider());
        Assertions.assertNull(dto.getPaymentMethod());
        Assertions.assertNull(dto.get_created());
        Assertions.assertNull(dto.get_updated());
        Assertions.assertNull(dto.getExternalPaymentId());
        Assertions.assertNull(dto.getQrData());
        Assertions.assertNull(dto.getMeliId());
    }
}

