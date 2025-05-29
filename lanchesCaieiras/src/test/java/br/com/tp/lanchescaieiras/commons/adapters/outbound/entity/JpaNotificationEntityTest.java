package br.com.tp.lanchescaieiras.commons.adapters.outbound.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JpaNotificationEntityTest {

    @Test
    void testConstructorAndGetters() {
        JpaNotificationEntity entity = new JpaNotificationEntity("INFO", 123, "Mensagem");
        assertEquals("INFO", entity.getNotificationType());
        assertEquals(123, entity.getArtefactId());
        assertEquals("Mensagem", entity.getMessage());
        assertNotNull(entity.getCreatedAt());
    }

    @Test
    void testSetters() {
        JpaNotificationEntity entity = new JpaNotificationEntity();
        entity.setId(10);
        entity.setNotificationType("WARN");
        entity.setArtefactId(456);
        entity.setMessage("Outra mensagem");
        LocalDateTime now = LocalDateTime.now();
        entity.setCreatedAt(now);

        assertEquals(10, entity.getId());
        assertEquals("WARN", entity.getNotificationType());
        assertEquals(456, entity.getArtefactId());
        assertEquals("Outra mensagem", entity.getMessage());
        assertEquals(now, entity.getCreatedAt());
    }

    @Test
    void testPrePersistSetsCreatedAt() {
        JpaNotificationEntity entity = new JpaNotificationEntity();
        entity.setCreatedAt(null);
        entity.prePersist();
        assertNotNull(entity.getCreatedAt());
    }
}
