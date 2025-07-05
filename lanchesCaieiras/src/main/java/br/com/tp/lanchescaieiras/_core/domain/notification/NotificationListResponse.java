package br.com.tp.lanchescaieiras._core.domain.notification;

import br.com.tp.lanchescaieiras._core.commons.domain.ResponseMetadata;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"_response", "notifications"})
public class NotificationListResponse {
    private ResponseMetadata _response;
    private List<Notification> notifications;

    public NotificationListResponse(List<Notification> notifications) {
        this.notifications = notifications;
        this._response = new ResponseMetadata();
    }

    public ResponseMetadata get_response() {
        return _response;
    }

    public void set_response(ResponseMetadata _response) {
        this._response = _response;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
