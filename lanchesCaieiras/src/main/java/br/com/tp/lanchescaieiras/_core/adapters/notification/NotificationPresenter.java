package br.com.tp.lanchescaieiras._core.adapters.notification;

import br.com.tp.lanchescaieiras._core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lanchescaieiras._core.domain.notification.Notification;

import java.util.List;

public class NotificationPresenter {

    private final NotificationMapper notificationMapper;

    public NotificationPresenter(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }


    public List<NotificationDTO> getByType(List<Notification> notificationsList) {
        return notificationsList.stream()
                .map(notificationMapper::notificationToDTO)
                .toList();
    }
}
