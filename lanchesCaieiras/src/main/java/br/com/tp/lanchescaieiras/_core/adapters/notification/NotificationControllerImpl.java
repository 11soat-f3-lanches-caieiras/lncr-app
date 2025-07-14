package br.com.tp.lanchescaieiras._core.adapters.notification;

import br.com.tp.lanchescaieiras._core.applications.notification.CreateNotificationUseCase;
import br.com.tp.lanchescaieiras._core.applications.notification.GetNotificationUseCase;
import br.com.tp.lanchescaieiras._core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.notification.NotificationController;
import br.com.tp.lanchescaieiras._core.commons.interfaces.notification.NotificationDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.notification.NotificationGateway;
import br.com.tp.lanchescaieiras._core.domain.notification.Notification;

import java.util.List;

public class NotificationControllerImpl implements NotificationController {

    private final NotificationGateway notificationGateway;
    private final NotificationMapper notificationMapper;

    public NotificationControllerImpl(NotificationDatabase notificationDatabase) {
        this.notificationMapper = new NotificationMapper();
        this.notificationGateway = new NotificationGatewayImpl(notificationDatabase,this.notificationMapper);
    }

    @Override
    public void createNotification(NotificationDTO notificationDTO) {
        new CreateNotificationUseCase(notificationGateway).execute(notificationDTO);
    }

    @Override
    public List<NotificationDTO> getNotificationByType(String notificationType) {
        List<Notification> notificationsList = new GetNotificationUseCase(notificationGateway).getByType(notificationType);
        return new NotificationPresenter(notificationMapper).getByType(notificationsList);
    }
}
