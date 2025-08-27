package br.com.tp.lncr.core.adapters.notification;

import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lncr.core.commons.interfaces.notification.NotificationDatabase;
import br.com.tp.lncr.core.commons.interfaces.notification.NotificationGateway;
import br.com.tp.lncr.core.domain.notification.Notification;

import java.util.List;

public class NotificationGatewayImpl implements NotificationGateway {

    private final NotificationDatabase notificationDatabase;
    private final NotificationMapper notificationMapper;

    public NotificationGatewayImpl(NotificationDatabase notificationDatabase, NotificationMapper notificationMapper) {
        this.notificationDatabase = notificationDatabase;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public void saveNotification(Notification notification) {
        this.notificationDatabase.save(this.notificationMapper.notificationToDTO(notification));

    }

    @Override
    public List<Notification> getNotificationsByType(String notificationType) {
        List<NotificationDTO> notificationDTOList = this.notificationDatabase.findByNotificationType(notificationType);
        return notificationDTOList.stream().map(notificationMapper::notificationToDomain)
                .toList();
    }

    @Override
    public List<String> getNotificationTypesList() {
        return this.notificationDatabase.findNotificationTypeList();
    }
}
