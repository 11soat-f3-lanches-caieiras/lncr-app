package br.com.tp.lncr.app.apis;

import br.com.tp.lncr.app.apis.notification.NotificationRestControllerImpl;
import br.com.tp.lncr.app.commons.model.ResponseListModel;
import br.com.tp.lncr.app.commons.model.ResponseModel;
import br.com.tp.lncr.app.configs.NotificationConfig;
import br.com.tp.lncr.app.datasources.postgres.notification.JpaNotificationRepositoryImpl;
import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lncr.core.commons.interfaces.notification.NotificationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationRestControllerImplTest {

    @Mock
    private NotificationController notificationController;

    @Mock
    private JpaNotificationRepositoryImpl jpaNotificationRepository;

    @Mock
    private NotificationConfig notificationConfig;

    @InjectMocks
    private NotificationRestControllerImpl notificationRestController;

    private NotificationDTO notificationDTO;

    @BeforeEach
    void setUp() {
        notificationDTO = new NotificationDTO();
        notificationDTO.setId(1);
        notificationDTO.setMessage("Pedido pronto para retirada");
        notificationDTO.setNotificationType("ORDER_READY");
        notificationDTO.setCreated(LocalDateTime.now());
        notificationDTO.setArtefactId(1);
    }

    @Test
    void deveCriarNotificationComSucesso() {
        doNothing().when(notificationController).createNotification(notificationDTO);

        ResponseEntity<ResponseModel<NotificationDTO>> response = notificationRestController.createNotification(notificationDTO);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().get_content());
        verify(notificationController).createNotification(notificationDTO);
    }

    @Test
    void deveRetornarNotificationsPorTipo() {
        List<NotificationDTO> notifications = Collections.singletonList(notificationDTO);
        when(notificationController.getNotificationByType("ORDER_READY")).thenReturn(notifications);

        ResponseEntity<ResponseListModel<NotificationDTO>> response = notificationRestController.getNotificationByType("ORDER_READY");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(notifications, response.getBody().get_content());
        verify(notificationController).getNotificationByType("ORDER_READY");
    }

    @Test
    void deveRetornarListaTiposNotification() {
        List<String> notificationTypes = Arrays.asList("ORDER_READY", "ORDER_CONFIRMED", "PAYMENT_RECEIVED");
        when(notificationController.getNotificationTypeList()).thenReturn(notificationTypes);

        ResponseEntity<ResponseListModel<String>> response = notificationRestController.getNotificationByType();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(notificationTypes, response.getBody().get_content());
        verify(notificationController).getNotificationTypeList();
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverNotificationsPorTipo() {
        List<NotificationDTO> emptyNotifications = List.of();
        when(notificationController.getNotificationByType("NONEXISTENT_TYPE")).thenReturn(emptyNotifications);

        ResponseEntity<ResponseListModel<NotificationDTO>> response = notificationRestController.getNotificationByType("NONEXISTENT_TYPE");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().get_content().isEmpty());
        verify(notificationController).getNotificationByType("NONEXISTENT_TYPE");
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverTiposNotification() {
        List<String> emptyTypes = List.of();
        when(notificationController.getNotificationTypeList()).thenReturn(emptyTypes);

        ResponseEntity<ResponseListModel<String>> response = notificationRestController.getNotificationByType();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().get_content().isEmpty());
        verify(notificationController).getNotificationTypeList();
    }

    @Test
    void deveCriarNotificationComMensagemNula() {
        NotificationDTO notificationWithNullMessage = new NotificationDTO();
        notificationWithNullMessage.setNotificationType("ORDER_READY");
        notificationWithNullMessage.setArtefactId(1);
        doNothing().when(notificationController).createNotification(notificationWithNullMessage);

        ResponseEntity<ResponseModel<NotificationDTO>> response = notificationRestController.createNotification(notificationWithNullMessage);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().get_content());
        verify(notificationController).createNotification(notificationWithNullMessage);
    }

    @Test
    void deveRetornarMultiplasNotificationsPorTipo() {
        NotificationDTO notification2 = new NotificationDTO();
        notification2.setId(2);
        notification2.setMessage("Outro pedido pronto");
        notification2.setNotificationType("ORDER_READY");

        List<NotificationDTO> notifications = Arrays.asList(notificationDTO, notification2);
        when(notificationController.getNotificationByType("ORDER_READY")).thenReturn(notifications);

        ResponseEntity<ResponseListModel<NotificationDTO>> response = notificationRestController.getNotificationByType("ORDER_READY");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().get_content().size());
        assertEquals(notifications, response.getBody().get_content());
        verify(notificationController).getNotificationByType("ORDER_READY");
    }
}
