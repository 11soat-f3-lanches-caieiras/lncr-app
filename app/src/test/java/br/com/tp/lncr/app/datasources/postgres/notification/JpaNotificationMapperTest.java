package br.com.tp.lncr.app.datasources.postgres.notification;

import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JpaNotificationMapperTest {

    private final JpaNotificationMapper mapper = new JpaNotificationMapper();

    @Test
    void jpaNotificationToDTOConvertsCorrectly() {
        LocalDateTime created = LocalDateTime.now();
        JpaNotificationEntity entity = new JpaNotificationEntity();
        entity.setId(1);
        entity.setNotificationType("ORDER");
        entity.setArtefactId(123);
        entity.setMessage("Test message");
        entity.setCreated(created);

        NotificationDTO dto = mapper.jpaNotificationToDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getNotificationType(), dto.getNotificationType());
        assertEquals(entity.getArtefactId(), dto.getArtefactId());
        assertEquals(entity.getMessage(), dto.getMessage());
        assertEquals(entity.getCreated(), dto.getCreated());
    }

    @Test
    void jpaNotificationToDTOWithNullEntityReturnsNull() {
        NotificationDTO dto = mapper.jpaNotificationToDTO(null);

        assertNull(dto);
    }

    @Test
    void notificationDtoToJpaConvertsCorrectly() {
        LocalDateTime created = LocalDateTime.now();
        NotificationDTO dto = new NotificationDTO(1, "PAYMENT", 456, "Payment message", created);

        JpaNotificationEntity entity = mapper.notificationDtoToJpa(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getNotificationType(), entity.getNotificationType());
        assertEquals(dto.getArtefactId(), entity.getArtefactId());
        assertEquals(dto.getMessage(), entity.getMessage());
        assertEquals(dto.getCreated(), entity.getCreated());
    }

    @Test
    void notificationDtoToJpaWithNullDtoReturnsNull() {
        JpaNotificationEntity entity = mapper.notificationDtoToJpa(null);

        assertNull(entity);
    }

    @Test
    void roundTripConversionMaintainsData() {
        LocalDateTime created = LocalDateTime.now();
        JpaNotificationEntity originalEntity = new JpaNotificationEntity();
        originalEntity.setId(1);
        originalEntity.setNotificationType("KITCHEN");
        originalEntity.setArtefactId(789);
        originalEntity.setMessage("Kitchen message");
        originalEntity.setCreated(created);

        NotificationDTO dto = mapper.jpaNotificationToDTO(originalEntity);
        JpaNotificationEntity convertedEntity = mapper.notificationDtoToJpa(dto);

        assertEquals(originalEntity.getId(), convertedEntity.getId());
        assertEquals(originalEntity.getNotificationType(), convertedEntity.getNotificationType());
        assertEquals(originalEntity.getArtefactId(), convertedEntity.getArtefactId());
        assertEquals(originalEntity.getMessage(), convertedEntity.getMessage());
        assertEquals(originalEntity.getCreated(), convertedEntity.getCreated());
    }

    @Test
    void mapperHandlesEmptyFields() {
        JpaNotificationEntity entity = new JpaNotificationEntity();
        entity.setId(null);
        entity.setNotificationType("");
        entity.setArtefactId(null);
        entity.setMessage("");
        entity.setCreated(null);

        NotificationDTO dto = mapper.jpaNotificationToDTO(entity);

        assertNotNull(dto);
        assertNull(dto.getId());
        assertEquals("", dto.getNotificationType());
        assertNull(dto.getArtefactId());
        assertEquals("", dto.getMessage());
        assertNull(dto.getCreated());
    }
}
