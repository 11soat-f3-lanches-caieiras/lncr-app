package br.com.tp.lncr.app.datasources.postgres.notification;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JpaNotificationEntityTest {

    @Test
    void constructorWithParametersCreatesEntityCorrectly() {
        String notificationType = "ORDER";
        Integer artefactId = 123;
        String message = "Test message";

        JpaNotificationEntity entity = new JpaNotificationEntity(notificationType, artefactId, message);

        assertEquals(notificationType, entity.getNotificationType());
        assertEquals(artefactId, entity.getArtefactId());
        assertEquals(message, entity.getMessage());
        assertNotNull(entity.getCreated());
    }

    @Test
    void defaultConstructorCreatesEmptyEntity() {
        JpaNotificationEntity entity = new JpaNotificationEntity();

        assertNull(entity.getId());
        assertNull(entity.getNotificationType());
        assertNull(entity.getArtefactId());
        assertNull(entity.getMessage());
        assertNull(entity.getCreated());
    }

    @Test
    void settersAndGettersWorkCorrectly() {
        JpaNotificationEntity entity = new JpaNotificationEntity();
        Integer id = 1;
        String notificationType = "PAYMENT";
        Integer artefactId = 456;
        String message = "Payment processed";
        LocalDateTime created = LocalDateTime.now();

        entity.setId(id);
        entity.setNotificationType(notificationType);
        entity.setArtefactId(artefactId);
        entity.setMessage(message);
        entity.setCreated(created);

        assertEquals(id, entity.getId());
        assertEquals(notificationType, entity.getNotificationType());
        assertEquals(artefactId, entity.getArtefactId());
        assertEquals(message, entity.getMessage());
        assertEquals(created, entity.getCreated());
    }

    @Test
    void prePersistSetsCreatedDateTime() {
        JpaNotificationEntity entity = new JpaNotificationEntity();

        entity.prePersist();

        assertNotNull(entity.getCreated());
    }

    @Test
    void constructorWithParametersSetsCreatedDateTime() {
        LocalDateTime before = LocalDateTime.now();

        JpaNotificationEntity entity = new JpaNotificationEntity("ORDER", 123, "Test");

        LocalDateTime after = LocalDateTime.now();
        assertNotNull(entity.getCreated());
        assertTrue(entity.getCreated().isAfter(before.minusSeconds(1)));
        assertTrue(entity.getCreated().isBefore(after.plusSeconds(1)));
    }

    @Test
    void entityHandlesNullValues() {
        JpaNotificationEntity entity = new JpaNotificationEntity(null, null, null);

        assertNull(entity.getNotificationType());
        assertNull(entity.getArtefactId());
        assertNull(entity.getMessage());
        assertNotNull(entity.getCreated());
    }
}
