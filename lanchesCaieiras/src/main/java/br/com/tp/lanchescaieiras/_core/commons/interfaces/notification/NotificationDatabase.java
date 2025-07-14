package br.com.tp.lanchescaieiras._core.commons.interfaces.notification;

import br.com.tp.lanchescaieiras._core.commons.dtos.notification.NotificationDTO;

import java.util.List;

public interface NotificationDatabase {

    List<NotificationDTO> findByNotificationType(String artefactType);

    void save(NotificationDTO notificationDTO);
}
