package com.example.demo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class DemoPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String transactionId;
    @Column(name = "point_amount")
    @Max(10)
    @Min(0)
    private int pointAmount;
    @Column(name = "business_time", nullable = false)
    private LocalDateTime businessTime;
    @Column(name = "process_cost")
    private int procesCost;
    @Column(name = "created_by", nullable = false)
    private String createBy;
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(int pointAmount) {
        this.pointAmount = pointAmount;
    }

    public LocalDateTime getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(LocalDateTime businessTime) {
        this.businessTime = businessTime;
    }

    public int getProcesCost() {
        return procesCost;
    }

    public void setProcesCost(int procesCost) {
        this.procesCost = procesCost;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

}
