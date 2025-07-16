package br.com.tp.lanchescaieiras._external.apis.notification;

import br.com.tp.lanchescaieiras._core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseListModel;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface NotificationRestController {

    ResponseEntity<ResponseModel<NotificationDTO>> createNotification(@RequestBody NotificationDTO notificationDTO);

    ResponseEntity<ResponseListModel<NotificationDTO>> getNotificationByType(@PathVariable(name = "notificationType") String notificationType);

    ResponseEntity<ResponseListModel<String>> getNotificationByType();
}
