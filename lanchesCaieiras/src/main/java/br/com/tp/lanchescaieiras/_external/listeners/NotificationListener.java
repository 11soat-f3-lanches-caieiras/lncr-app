package br.com.tp.lanchescaieiras._external.listeners;

import br.com.tp.lanchescaieiras._core.domain.notification.Notification;
import org.springframework.context.event.EventListener;

public interface NotificationListener {

    @EventListener
    void handleNotification(Notification notification);


}
