package br.com.tp.lncr.core.commons.interfaces.notification;

import br.com.tp.lncr.core.domain.notification.Notification;

import java.util.List;

public interface NotificationGateway {
    void saveNotification(Notification notification);

    List<Notification> getNotificationsByType(String notificationType);

    List<String> getNotificationTypesList();
}
