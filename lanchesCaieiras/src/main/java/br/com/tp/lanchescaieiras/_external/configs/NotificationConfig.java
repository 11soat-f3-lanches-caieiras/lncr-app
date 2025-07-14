package br.com.tp.lanchescaieiras._external.configs;

import br.com.tp.lanchescaieiras._core.adapters.notification.NotificationControllerImpl;
import br.com.tp.lanchescaieiras._core.commons.interfaces.notification.NotificationDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class NotificationConfig {
    @Bean
    public NotificationControllerImpl notificationControllerImpl(NotificationDatabase notificationDatabase) {
        return new NotificationControllerImpl(notificationDatabase);
    }
}
