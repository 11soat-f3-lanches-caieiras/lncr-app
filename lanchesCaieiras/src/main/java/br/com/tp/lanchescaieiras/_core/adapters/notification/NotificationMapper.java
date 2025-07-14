package br.com.tp.lanchescaieiras._core.adapters.notification;

import br.com.tp.lanchescaieiras._core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lanchescaieiras._core.domain.notification.Notification;

public class NotificationMapper {
    public NotificationDTO notificationToDTO(Notification notification) {
        if (notification == null) return null;
        return new NotificationDTO(
            notification.getId(),
            notification.getNotificationType(),
            notification.getArtefactId(),
            notification.getMessage(),
            notification.get_created()
        );
    }

    public Notification notificationToDomain(NotificationDTO dto) {
        if (dto == null) return null;
        return new Notification(dto);
    }
}
