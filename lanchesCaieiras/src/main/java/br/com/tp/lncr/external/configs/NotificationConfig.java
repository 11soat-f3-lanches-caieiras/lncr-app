package br.com.tp.lncr.external.configs;

import br.com.tp.lncr.core.adapters.notification.NotificationControllerImpl;
import br.com.tp.lncr.core.commons.interfaces.notification.NotificationController;
import br.com.tp.lncr.core.commons.interfaces.notification.NotificationDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class NotificationConfig {
    @Bean
    public NotificationController notificationController(NotificationDatabase notificationDatabase) {
        return new NotificationControllerImpl(notificationDatabase);
    }
}
