package br.com.tp.lanchescaieiras._core.commons.interfaces.notification;

import br.com.tp.lanchescaieiras._core.domain.notification.Notification;

import java.util.List;

public interface NotificationRepository {

    void saveNotification(Notification notification);

    List<Notification> findByNotificationType(String artefactType);
}
