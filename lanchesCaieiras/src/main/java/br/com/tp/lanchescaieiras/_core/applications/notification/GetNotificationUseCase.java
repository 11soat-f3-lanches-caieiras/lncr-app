package br.com.tp.lanchescaieiras._core.applications.notification;

import br.com.tp.lanchescaieiras._core.commons.interfaces.notification.NotificationGateway;
import br.com.tp.lanchescaieiras._core.domain.exceptions.NotificationException;
import br.com.tp.lanchescaieiras._core.domain.notification.Notification;

import java.util.List;

public class GetNotificationUseCase {

    private final NotificationGateway notificationGateway;

    public GetNotificationUseCase(NotificationGateway notificationGateway) {
        this.notificationGateway = notificationGateway;
    }

    public List<Notification> getByType(String notificationType) {
        List<Notification> notifications = notificationGateway.findByNotificationType(notificationType);
        if (notifications == null || notifications.isEmpty()) {
            throw new NotificationException("Não encontrada notificações para o tipo: " + notificationType,404);
        }
        return notifications;
    }
}
