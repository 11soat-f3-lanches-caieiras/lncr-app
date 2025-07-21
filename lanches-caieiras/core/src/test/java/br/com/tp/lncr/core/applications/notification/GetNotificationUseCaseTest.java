package br.com.tp.lncr.core.applications.notification;

import br.com.tp.lncr.core.commons.exceptions.NotificationException;
import br.com.tp.lncr.core.commons.interfaces.notification.NotificationGateway;
import br.com.tp.lncr.core.domain.notification.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetNotificationUseCaseTest {
    private NotificationGateway notificationGateway;
    private GetNotificationUseCase useCase;

    @BeforeEach
    void setUp() {
        notificationGateway = mock(NotificationGateway.class);
        useCase = new GetNotificationUseCase(notificationGateway);
    }

    @Test
    void deveRetornarNotificacoesPorTipo() {
        Notification notification = mock(Notification.class);
        when(notificationGateway.getNotificationsByType("INFO")).thenReturn(Collections.singletonList(notification));
        List<Notification> result = useCase.getByType("INFO");
        assertEquals(1, result.size());
        assertEquals(notification, result.get(0));
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarNotificacoes() {
        when(notificationGateway.getNotificationsByType("ERRO")).thenReturn(Collections.emptyList());
        NotificationException ex = assertThrows(NotificationException.class, () -> useCase.getByType("ERRO"));
        assertEquals(404, ex.getCode());
    }

    @Test
    void deveRetornarListaDeTipos() {
        when(notificationGateway.getNotificationTypesList()).thenReturn(Arrays.asList("INFO", "ERRO"));
        List<String> tipos = useCase.getTypeList();
        assertEquals(2, tipos.size());
        assertTrue(tipos.contains("INFO"));
        assertTrue(tipos.contains("ERRO"));
    }
}

