package br.com.tp.lanchescaieiras.commons.applications.mappers;

import br.com.tp.lanchescaieiras.commons.adapters.outbound.entities.JpaNotificationEntity;
import br.com.tp.lanchescaieiras.commons.domain.Notification;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotificationMapperTest {

    private final NotificationMapper mapper = new NotificationMapperImpl();

    @Test
    void testJpaToDomain() {
        JpaNotificationEntity entity = new JpaNotificationEntity();
        entity.setId(1);
        entity.setNotificationType("INFO");
        entity.setArtefactId(100);
        entity.setMessage("Mensagem");
        LocalDateTime now = LocalDateTime.now();
        entity.setCreatedAt(now);

        Notification notification = mapper.jpaToDomain(entity);

        assertEquals(1, notification.getId());
        assertEquals("INFO", notification.getNotificationType());
        assertEquals(100, notification.getArtefactId());
        assertEquals("Mensagem", notification.getMessage());
        assertEquals(now, notification.getCreatedAt());
        assertNotNull(notification.getSource());
    }

    @Test
    void testDomainToJpa() {
        Notification notification = new Notification(new Object(), 2, "WARN", 200, "Outra mensagem");
        JpaNotificationEntity entity = mapper.domainToJpa(notification);

        assertEquals(2, entity.getId());
        assertEquals("WARN", entity.getNotificationType());
        assertEquals(200, entity.getArtefactId());
        assertEquals("Outra mensagem", entity.getMessage());
    }
}

// Implementação manual para testes unitários
class NotificationMapperImpl implements NotificationMapper {
    @Override
    public Notification jpaToDomain(JpaNotificationEntity jpaNotificationEntity) {
        Notification notification = new Notification(
                new Object(),
                jpaNotificationEntity.getId(),
                jpaNotificationEntity.getNotificationType(),
                jpaNotificationEntity.getArtefactId(),
                jpaNotificationEntity.getMessage()
        );
        notification.setCreatedAt(jpaNotificationEntity.getCreatedAt());
        return notification;
    }

    @Override
    public JpaNotificationEntity domainToJpa(Notification notification) {
        JpaNotificationEntity entity = new JpaNotificationEntity();
        entity.setId(notification.getId());
        entity.setNotificationType(notification.getNotificationType());
        entity.setArtefactId(notification.getArtefactId());
        entity.setMessage(notification.getMessage());
        entity.setCreatedAt(notification.getCreatedAt());
        return entity;
    }
}
