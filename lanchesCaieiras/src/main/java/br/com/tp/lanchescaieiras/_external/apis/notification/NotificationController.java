package br.com.tp.lanchescaieiras._external.apis.notification;

import br.com.tp.lanchescaieiras._core.domain.notification.NotificationListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface NotificationController {

    @GetMapping("/{notificationType}")
    ResponseEntity<NotificationListResponse> getByNotificationType(@PathVariable(name = "notificationType") String notificationType);
}
