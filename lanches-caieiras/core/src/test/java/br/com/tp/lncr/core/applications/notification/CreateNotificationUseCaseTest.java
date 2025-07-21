package br.com.tp.lncr.core.applications.notification;

import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lncr.core.commons.interfaces.notification.NotificationGateway;
import br.com.tp.lncr.core.domain.notification.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CreateNotificationUseCaseTest {
    private NotificationGateway notificationGateway;
    private CreateNotificationUseCase useCase;

    @BeforeEach
    void setUp() {
        notificationGateway = mock(NotificationGateway.class);
        useCase = new CreateNotificationUseCase(notificationGateway);
    }

    @Test
    void deveCriarNotificacaoComSucesso() {
        NotificationDTO dto = mock(NotificationDTO.class);
        when(dto.getMessage()).thenReturn("Mensagem de teste");

        useCase.execute(dto);

        verify(notificationGateway).saveNotification(any(Notification.class));
    }
}
