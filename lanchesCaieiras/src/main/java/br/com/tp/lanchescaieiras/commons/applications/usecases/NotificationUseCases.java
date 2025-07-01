package br.com.tp.lanchescaieiras.commons.applications.usecases;

import br.com.tp.lanchescaieiras.commons.domain.Notification;

import java.util.List;

public interface NotificationUseCases {

    void saveNotification(Notification notification);

    List<Notification> findByNotificationType(String artefactType);
}
