package br.com.tp.lncr.external.datasources.postgres.notification;

import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;
import org.springframework.stereotype.Component;

@Component
public class JpaNotificationMapper {
    public NotificationDTO jpaNotificationToDTO(JpaNotificationEntity entity) {
        if (entity == null) return null;
        return new NotificationDTO(
            entity.getId(),
            entity.getNotificationType(),
            entity.getArtefactId(),
            entity.getMessage(),
            entity.getCreated()
        );
    }

    public JpaNotificationEntity notificationDtoToJpa(NotificationDTO dto) {
        if (dto == null) return null;
        JpaNotificationEntity entity = new JpaNotificationEntity();
        entity.setId(dto.getId());
        entity.setNotificationType(dto.getNotificationType());
        entity.setArtefactId(dto.getArtefactId());
        entity.setMessage(dto.getMessage());
        entity.setCreated(dto.getCreated());
        return entity;
    }
}

