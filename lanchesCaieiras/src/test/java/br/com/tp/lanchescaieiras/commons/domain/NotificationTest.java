package br.com.tp.lanchescaieiras.commons.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotificationTest {

    @Test
    void testConstructorAndGetters() {
        Object source = new Object();
        Notification notification = new Notification(source, 1, "INFO", 100, "Mensagem de teste");
        notification.setCreatedAt(LocalDateTime.now());

        assertEquals(1, notification.getId());
        assertEquals("INFO", notification.getNotificationType());
        assertEquals(100, notification.getArtefactId());
        assertEquals("Mensagem de teste", notification.getMessage());
        assertNotNull(notification.getCreatedAt());
        assertEquals(source, notification.getSource());
    }

    @Test
    void testSetters() {
        Notification notification = new Notification(new Object(), 1, "INFO", 100, "Mensagem");
        notification.setId(2);
        notification.setNotificationType("WARN");
        notification.setArtefactId(200);
        notification.setMessage("Nova mensagem");
        LocalDateTime now = LocalDateTime.now();
        notification.setCreatedAt(now);

        assertEquals(2, notification.getId());
        assertEquals("WARN", notification.getNotificationType());
        assertEquals(200, notification.getArtefactId());
        assertEquals("Nova mensagem", notification.getMessage());
        assertEquals(now, notification.getCreatedAt());
    }
}
