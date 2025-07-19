package br.com.tp.lncr.core.domain.notification;

import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;

import java.time.LocalDateTime;

public class Notification {
    private Integer id;
    private String notificationType;
    private Integer artefactId;
    private String message;
    private LocalDateTime _created;

    public Notification(Integer id, String notificationType, Integer artefactId, String message, LocalDateTime _created) {
        this.id = id;
        this.notificationType = notificationType;
        this.artefactId = artefactId;
        this.message = message;
        this._created = _created;
    }

    public Notification(NotificationDTO dto) {
        this.id = dto.getId();
        this.notificationType = dto.getNotificationType();
        this.artefactId = dto.getArtefactId();
        this.message = dto.getMessage();
        this._created = dto.getCreated();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Integer getArtefactId() {
        return artefactId;
    }

    public void setArtefactId(Integer artefactId) {
        this.artefactId = artefactId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime get_created() {
        return _created;
    }

    public void set_created(LocalDateTime _created) {
        this._created = _created;
    }

}
