package br.com.tp.lncr.app.apis.notification;

import br.com.tp.lncr.app.commons.model.ResponseListModel;
import br.com.tp.lncr.app.commons.model.ResponseModel;
import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface NotificationRestController {

    ResponseEntity<ResponseModel<NotificationDTO>> createNotification(@RequestBody NotificationDTO notificationDTO);

    ResponseEntity<ResponseListModel<NotificationDTO>> getNotificationByType(@PathVariable(name = "notificationType") String notificationType);

    ResponseEntity<ResponseListModel<String>> getNotificationByType();
}
