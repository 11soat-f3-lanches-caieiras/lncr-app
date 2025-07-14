package br.com.tp.lanchescaieiras._core.applications.notification;

import br.com.tp.lanchescaieiras._core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.notification.NotificationGateway;
import br.com.tp.lanchescaieiras._core.domain.notification.Notification;

public class CreateNotificationUseCase {

    private final NotificationGateway notificationGateway;


    public CreateNotificationUseCase(NotificationGateway notificationGateway) {
        this.notificationGateway = notificationGateway;
    }

    public void execute(NotificationDTO notificationDTO) {
        Notification notification = new Notification(notificationDTO);
        this.notificationGateway.save(notification);
    }
}
