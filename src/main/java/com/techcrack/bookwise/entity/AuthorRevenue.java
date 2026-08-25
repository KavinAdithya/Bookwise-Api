package com.techcrack.bookwise.entity;

import com.techcrack.bookwise.constans.enums.IncomeType;
import com.techcrack.bookwise.utils.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "AuthorRevenues")
public class AuthorRevenue extends BaseEntity {

    private Long authorId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IncomeType sourceType;

    @Column(nullable = false)
    private Long sourceId;

    private double amount;

    @Column(nullable = false)
    private LocalDateTime incomeDate;

    @Column(nullable = false)
    private boolean amountDisbursed;

    public AuthorRevenue() {
        super();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public IncomeType getSourceType() {
        return sourceType;
    }

    public void setSourceType(IncomeType sourceType) {
        this.sourceType = sourceType;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public LocalDateTime getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDateTime incomeDate) {
        this.incomeDate = incomeDate;
    }

    public boolean isAmountDisbursed() {
        return amountDisbursed;
    }

    public void setAmountDisbursed(boolean amountDisbursed) {
        this.amountDisbursed = amountDisbursed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorRevenue that)) return false;
        return Double.compare(getAmount(), that.getAmount()) == 0 && Objects.equals(authorId, that.authorId) && sourceType == that.sourceType && Objects.equals(sourceId, that.sourceId) && Objects.equals(incomeDate, that.incomeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, sourceType, sourceId, getAmount(), incomeDate);
    }

    @Override
    public String toString() {
        return "AuthorRevenue{" +
                "authorId=" + authorId +
                ", sourceType='" + sourceType + '\'' +
                ", sourceId=" + sourceId +
                ", amount=" + amount +
                ", incomeDate=" + incomeDate +
                '}';
    }
}