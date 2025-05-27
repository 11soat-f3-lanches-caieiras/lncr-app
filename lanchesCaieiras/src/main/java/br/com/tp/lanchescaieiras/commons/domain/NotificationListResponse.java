package br.com.tp.lanchescaieiras.commons.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"_response", "notifications"})
public class NotificationListResponse {
    private ResponseMetada _response;
    private List<Notification> notifications;

    public NotificationListResponse(List<Notification> notifications) {
        this.notifications = notifications;
        this._response = new ResponseMetada();
    }

    public ResponseMetada get_response() {
        return _response;
    }

    public void set_response(ResponseMetada _response) {
        this._response = _response;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
