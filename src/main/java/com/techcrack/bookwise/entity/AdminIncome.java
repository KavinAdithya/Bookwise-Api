package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.utils.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "AdminIncomes")
public class AdminIncome extends BaseEntity {
    @Column(nullable = false)
    private String sourceType;
    @Column(nullable = false)
    private Long sourceId;
    private double amount;
    @Column(nullable = false)
    private LocalDateTime incomeDate;

    public AdminIncome() {
        super();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDateTime incomeDate) {
        this.incomeDate = incomeDate;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminIncome that)) return false;
        return Double.compare(getAmount(), that.getAmount()) == 0 && Objects.equals(getId(), that.getId()) && Objects.equals(getSourceType(), that.getSourceType()) && Objects.equals(getSourceId(), that.getSourceId()) && Objects.equals(getIncomeDate(), that.getIncomeDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSourceType(), getSourceId(), getAmount(), getIncomeDate());
    }

    @Override
    public String toString() {
        return "AdminIncome{" +
                " amount=" + amount +
                ", incomeDate=" + incomeDate +
                ", sourceId=" + sourceId +
                ", sourceType='" + sourceType + '\'' +
                '}';
    }
}