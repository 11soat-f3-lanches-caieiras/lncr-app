package br.com.tp.lncr.app.apis.notification;

import br.com.tp.lncr.app.commons.model.ResponseListModel;
import br.com.tp.lncr.app.commons.model.ResponseModel;
import br.com.tp.lncr.app.commons.utils.ResponseEntityModelUtil;
import br.com.tp.lncr.app.configs.NotificationConfig;
import br.com.tp.lncr.app.datasources.postgres.notification.JpaNotificationRepositoryImpl;
import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lncr.core.commons.interfaces.notification.NotificationController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationRestControllerImpl implements NotificationRestController {

    private final NotificationController notificationController;
    private final JpaNotificationRepositoryImpl jpaNotificationRepository;
    private final NotificationConfig notificationConfig;

    public NotificationRestControllerImpl(NotificationController notificationController,
                                          JpaNotificationRepositoryImpl jpaNotificationRepository,
                                          NotificationConfig notificationConfig) {
        this.notificationController = notificationController;
        this.jpaNotificationRepository = jpaNotificationRepository;
        this.notificationConfig = notificationConfig;
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseModel<NotificationDTO>> createNotification(NotificationDTO notificationDTO) {
        this.notificationController.createNotification(notificationDTO);
        return ResponseEntityModelUtil.Accepted(null);
    }

    @Override
    @GetMapping("/{notificationType}")
    public ResponseEntity<ResponseListModel<NotificationDTO>> getNotificationByType(@PathVariable(name = "notificationType") String notificationType) {
        List<NotificationDTO> notificationList = this.notificationController.getNotificationByType(notificationType);
        return ResponseEntityModelUtil.listOK(notificationList);
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseListModel<String>> getNotificationByType() {
        List<String> notificationTypeList = this.notificationController.getNotificationTypeList();
        return ResponseEntityModelUtil.listOK(notificationTypeList);
    }
}
