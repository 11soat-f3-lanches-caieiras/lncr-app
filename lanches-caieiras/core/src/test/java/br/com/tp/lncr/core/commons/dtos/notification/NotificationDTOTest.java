package br.com.tp.lncr.core.commons.dtos.notification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class NotificationDTOTest {
    @Test
    void createNotificationDTOWithAllFields() {
        LocalDateTime now = LocalDateTime.now();
        NotificationDTO dto = new NotificationDTO(1, "ORDER", 10, "Pedido recebido", now);
        Assertions.assertEquals(1, dto.getId());
        Assertions.assertEquals("ORDER", dto.getNotificationType());
        Assertions.assertEquals(10, dto.getArtefactId());
        Assertions.assertEquals("Pedido recebido", dto.getMessage());
        Assertions.assertEquals(now, dto.getCreated());
    }

    @Test
    void setAndGetFieldsIndividually() {
        NotificationDTO dto = new NotificationDTO();
        LocalDateTime now = LocalDateTime.now();
        dto.setId(2);
        dto.setNotificationType("PAYMENT");
        dto.setArtefactId(20);
        dto.setMessage("Pagamento aprovado");
        dto.setCreated(now);
        Assertions.assertEquals(2, dto.getId());
        Assertions.assertEquals("PAYMENT", dto.getNotificationType());
        Assertions.assertEquals(20, dto.getArtefactId());
        Assertions.assertEquals("Pagamento aprovado", dto.getMessage());
        Assertions.assertEquals(now, dto.getCreated());
    }

    @Test
    void allowNullFields() {
        NotificationDTO dto = new NotificationDTO(null, null, null, null, null);
        Assertions.assertNull(dto.getId());
        Assertions.assertNull(dto.getNotificationType());
        Assertions.assertNull(dto.getArtefactId());
        Assertions.assertNull(dto.getMessage());
        Assertions.assertNull(dto.getCreated());
    }
}

