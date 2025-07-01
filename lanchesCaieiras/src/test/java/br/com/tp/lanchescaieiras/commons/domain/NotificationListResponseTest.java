package br.com.tp.lanchescaieiras.commons.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotificationListResponseTest {

    @Test
    void testConstructorAndGetters() {
        Notification n1 = new Notification(new Object(), 1, "INFO", 10, "msg1");
        Notification n2 = new Notification(new Object(), 2, "WARN", 20, "msg2");
        List<Notification> notifications = Arrays.asList(n1, n2);

        NotificationListResponse response = new NotificationListResponse(notifications);

        assertNotNull(response.get_response());
        assertEquals(notifications, response.getNotifications());
    }

    @Test
    void testSetters() {
        NotificationListResponse response = new NotificationListResponse(null);
        ResponseMetadata meta = new ResponseMetadata("id", "ts", "msg");
        response.set_response(meta);

        assertEquals(meta, response.get_response());

        Notification n = new Notification(new Object(), 3, "ERROR", 30, "msg3");
        response.setNotifications(List.of(n));
        assertEquals(1, response.getNotifications().size());
        assertEquals(n, response.getNotifications().get(0));
    }
}
