package br.com.tp.lanchescaieiras.commons.adapters.inbound.listeners;

import br.com.tp.lanchescaieiras.commons.domain.Notification;
import org.springframework.context.event.EventListener;

public interface NotificationListener {

    @EventListener
    public void handleNotification(Notification notification);


}
