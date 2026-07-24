package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.utils.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "AuditLogs")
public class AuditLog extends BaseEntity {

    @Column(nullable = false)
    private String action;
    @Column(nullable = false)
    private String oldValue;
    @Column(nullable = false)
    private String newValue;
    @Column(nullable = false)
    private String entityType;
    @Column(nullable = false)
    private Long entityId;
    @Column(nullable = false)
    private LocalDateTime loggedAt;
    @Column(nullable = false)
    private long userId;

    public AuditLog() {
        super();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuditLog auditLog)) return false;
        return Objects.equals(getId(), auditLog.getId()) && Objects.equals(getAction(), auditLog.getAction()) && Objects.equals(getOldValue(), auditLog.getOldValue()) && Objects.equals(getNewValue(), auditLog.getNewValue()) && Objects.equals(getEntityType(), auditLog.getEntityType()) && Objects.equals(getEntityId(), auditLog.getEntityId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAction(), getOldValue(), getNewValue(), getEntityType(), getEntityId());
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "action='" + action + '\'' +
                ", entityId=" + entityId +
                ", entityType='" + entityType + '\'' +
                ", loggedAt=" + loggedAt +
                ", loggerUserId=" + userId +
                ", newValue='" + newValue + '\'' +
                ", oldValue='" + oldValue + '\'' +
                '}';
    }
}