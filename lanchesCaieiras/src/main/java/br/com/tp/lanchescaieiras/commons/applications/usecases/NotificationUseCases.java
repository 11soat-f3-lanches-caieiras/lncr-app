package br.com.tp.lanchescaieiras.commons.applications.usecases;

import br.com.tp.lanchescaieiras.commons.domain.Notification;

import java.util.List;

public interface NotificationUseCases {

    public void saveNotification(Notification notification);

    public List<Notification> findByNotificationType(String artefactType);
}
