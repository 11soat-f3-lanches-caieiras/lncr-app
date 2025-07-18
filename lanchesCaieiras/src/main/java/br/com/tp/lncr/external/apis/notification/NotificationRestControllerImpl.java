package br.com.tp.lncr.external.apis.notification;

import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lncr.core.commons.interfaces.notification.NotificationController;
import br.com.tp.lncr.external.commons.model.ResponseListModel;
import br.com.tp.lncr.external.commons.model.ResponseModel;
import br.com.tp.lncr.external.commons.utils.ResponseEntityModelUtil;
import br.com.tp.lncr.external.configs.NotificationConfig;
import br.com.tp.lncr.external.datasources.postgres.notification.JpaNotificationRepositoryImpl;
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
