package br.com.tp.lanchescaieiras._core.commons.interfaces.notification;

import br.com.tp.lanchescaieiras._core.commons.dtos.notification.NotificationDTO;

import java.util.List;

public interface NotificationController {

    List<String> getNotificationTypeList();

    List<NotificationDTO> getNotificationByType(String notificationType);

    void createNotification(NotificationDTO notificationDTO);
}
