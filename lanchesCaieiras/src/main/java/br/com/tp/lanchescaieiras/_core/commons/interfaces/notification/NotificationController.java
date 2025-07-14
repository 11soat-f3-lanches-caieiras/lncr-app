package br.com.tp.lanchescaieiras._core.commons.interfaces.notification;

import br.com.tp.lanchescaieiras._core.commons.dtos.notification.NotificationDTO;

import java.util.List;

public interface NotificationController {

    void createNotification(NotificationDTO notificationDTO);

    List<NotificationDTO> getNotificationByType(String notificationType);
}
