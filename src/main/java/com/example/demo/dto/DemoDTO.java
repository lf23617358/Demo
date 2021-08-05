package com.example.demo.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.demo.annotation.DateNotOverNow;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DemoDTO {
    @NotEmpty(message = "Transaction ID can't empty")
    @JsonProperty("transaction_id")
    private String transactionId;
    @Max(value = 10, message = "Point Amount not be greater than 10")
    @Min(value = 0, message = "Point Amount not be less than 0")
    @JsonProperty("point_amount")
    private int pointAmount;
    @NotNull(message = "Business Time can't null")
    @DateNotOverNow
    @JsonProperty("business_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime businessTime;

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

}
