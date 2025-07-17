package br.com.tp.lanchescaieiras._core.applications.notification;

import br.com.tp.lanchescaieiras._core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.notification.NotificationGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.Logger;
import br.com.tp.lanchescaieiras._core.domain.notification.Notification;

public class CreateNotificationUseCase {

    private final NotificationGateway notificationGateway;


    public CreateNotificationUseCase(NotificationGateway notificationGateway) {
        this.notificationGateway = notificationGateway;
    }

    public void execute(NotificationDTO notificationDTO) {
        Logger.info("Iniciando criação de notificação: " + notificationDTO.getMessage());
        Notification notification = new Notification(notificationDTO);
        this.notificationGateway.saveNotification(notification);
        Logger.info("Notificação criada com sucesso: " + notification.getMessage());
    }
}
