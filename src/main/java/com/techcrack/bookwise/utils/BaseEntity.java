package com.techcrack.bookwise.utils;

import com.techcrack.bookwise.constans.ApplicationData;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

/**
 * It acts as a base entity for every in this application
 * It Provides some basic fields such as
 * <ul>
 *     <li>1. CreatedBy</li>
 *     <li>2. UpdateBy</li>
 *     <li>3. CreatedAt</li>
 *     <li>4. UpdateAt</li>
 *     <li>5. IsActive</li>
 * </ul>
 */
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected boolean isActive;
    protected LocalDateTime updatedAt;
    protected Long updatedBy;
    protected LocalDateTime createdAt;
    protected long createdBy;

    public BaseEntity() {
    }

    /***
     * For New Object Creation Only use this
     */
    public void initialize(long createdBy) {
        this.createdAt = ApplicationData.SYSTEM_DATE;
        this.createdBy = createdBy;
        this.isActive = true;
    }

    /***
     * For Update Only Invoke this method
     */
    public void initializeUpdate(long updatedBy) {
        this.updatedAt = ApplicationData.SYSTEM_DATE;
        this.updatedBy = updatedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", updatedAt=" + updatedAt +
                ", updatedBy=" + updatedBy +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                '}';
    }
}
