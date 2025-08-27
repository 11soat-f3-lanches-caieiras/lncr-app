package br.com.tp.lncr.core.domain.notification;

import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class NotificationTest {
    @Test
    void testConstructorAndGetters() {
        Notification notification = new Notification(1, "ORDER", 10, "Pedido criado", LocalDateTime.now());
        Assertions.assertEquals(1, notification.getId());
        Assertions.assertEquals("ORDER", notification.getNotificationType());
        Assertions.assertEquals(10, notification.getArtefactId());
        Assertions.assertEquals("Pedido criado", notification.getMessage());
        Assertions.assertNotNull(notification.get_created());
    }

    @Test
    void testSetters() {
        Notification notification = new Notification(2, "PAYMENT", 20, "Pagamento aprovado", LocalDateTime.now());
        notification.setId(3);
        notification.setNotificationType("KITCHEN");
        notification.setArtefactId(30);
        notification.setMessage("Pedido pronto");
        notification.set_created(LocalDateTime.now());
        Assertions.assertEquals(3, notification.getId());
        Assertions.assertEquals("KITCHEN", notification.getNotificationType());
        Assertions.assertEquals(30, notification.getArtefactId());
        Assertions.assertEquals("Pedido pronto", notification.getMessage());
        Assertions.assertNotNull(notification.get_created());
    }

    @Test
    void testDTOConstructor() {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(4);
        dto.setNotificationType("ORDER");
        dto.setArtefactId(40);
        dto.setMessage("Novo pedido");
        dto.setCreated(LocalDateTime.now());
        Notification notification = new Notification(dto);
        Assertions.assertEquals(4, notification.getId());
        Assertions.assertEquals("ORDER", notification.getNotificationType());
        Assertions.assertEquals(40, notification.getArtefactId());
        Assertions.assertEquals("Novo pedido", notification.getMessage());
        Assertions.assertNotNull(notification.get_created());
    }
}

