package br.com.tp.lanchescaieiras._core.commons.interfaces.notification;

import br.com.tp.lanchescaieiras._core.domain.notification.Notification;

import java.util.List;

public interface NotificationGateway {
    void save(Notification notification);

    List<Notification> findByNotificationType(String notificationType);
}
