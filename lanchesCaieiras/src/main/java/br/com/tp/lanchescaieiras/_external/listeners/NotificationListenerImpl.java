package br.com.tp.lanchescaieiras._external.listeners;

import br.com.tp.lanchescaieiras._core.applications.notification.applications.NotificationsServicesImpl;
import br.com.tp.lanchescaieiras._core.domain.notification.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationListenerImpl implements NotificationListener {

    private final NotificationsServicesImpl notificationsServices;

    public NotificationListenerImpl(NotificationsServicesImpl notificationsServices) {
        this.notificationsServices = notificationsServices;
    }

    @Override
    public void handleNotification(Notification notification) {
        notificationsServices.saveNotification(notification);
    }

}
