package br.com.tp.lanchescaieiras.commons.adapters.inbound.listeners;

import br.com.tp.lanchescaieiras.commons.applications.applications.NotificationsServicesImpl;
import br.com.tp.lanchescaieiras.commons.domain.Notification;
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
