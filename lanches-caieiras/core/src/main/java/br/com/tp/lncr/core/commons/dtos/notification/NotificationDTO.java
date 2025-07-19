package br.com.tp.lncr.core.commons.dtos.notification;

import java.time.LocalDateTime;

public class NotificationDTO {
    private Integer id;
    private String notificationType;
    private Integer artefactId;
    private String message;
    private LocalDateTime created;

    public NotificationDTO() {}

    public NotificationDTO(Integer id, String notificationType, Integer artefactId, String message, LocalDateTime created) {
        this.id = id;
        this.notificationType = notificationType;
        this.artefactId = artefactId;
        this.message = message;
        this.created = created;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}

