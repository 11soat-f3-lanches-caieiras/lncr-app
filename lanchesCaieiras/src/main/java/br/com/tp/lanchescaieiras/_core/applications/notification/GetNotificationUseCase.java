package br.com.tp.lanchescaieiras._core.applications.notification;

import br.com.tp.lanchescaieiras._core.commons.exceptions.NotificationException;
import br.com.tp.lanchescaieiras._core.commons.interfaces.notification.NotificationGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.Logger;
import br.com.tp.lanchescaieiras._core.domain.notification.Notification;

import java.util.List;

public class GetNotificationUseCase {

    private final NotificationGateway notificationGateway;

    public GetNotificationUseCase(NotificationGateway notificationGateway) {
        this.notificationGateway = notificationGateway;
    }

    public List<Notification> getByType(String notificationType) {
        Logger.info("Iniciando busca de notificações pelo tipo: " + notificationType);
        List<Notification> notifications = notificationGateway.getNotificationsByType(notificationType);
        if (notifications == null || notifications.isEmpty()) {
            throw new NotificationException("Não encontrada notificações para o tipo: " + notificationType,404);
        }
        Logger.info("Notificações encontradas com sucesso, tipo: " + notificationType);
        return notifications;
    }

    public List<String> getTypeList() {
        Logger.info("Buscando tipos de notificações");
        return notificationGateway.getNotificationTypesList();
    }
}
