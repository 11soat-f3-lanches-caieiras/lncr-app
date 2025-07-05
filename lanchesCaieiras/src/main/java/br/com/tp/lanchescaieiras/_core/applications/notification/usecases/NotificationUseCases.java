package br.com.tp.lanchescaieiras._core.applications.notification.usecases;

import br.com.tp.lanchescaieiras._core.domain.notification.Notification;

import java.util.List;

public interface NotificationUseCases {

    void saveNotification(Notification notification);

    List<Notification> findByNotificationType(String artefactType);
}
