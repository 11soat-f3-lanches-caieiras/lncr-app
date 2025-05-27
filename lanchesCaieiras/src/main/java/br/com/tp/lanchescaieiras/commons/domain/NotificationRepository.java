package br.com.tp.lanchescaieiras.commons.domain;

import java.util.List;

public interface NotificationRepository {

    void saveNotification(Notification notification);

    List<Notification> findByNotificationType(String artefactType);
}
