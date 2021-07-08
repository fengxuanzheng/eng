package com.EnergyProject.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ProAmount {
    private Integer node;
    private Integer tValue;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "GMT+8")
    private LocalDateTime tTime;

    public ProAmount(Integer node, Integer tValue, LocalDateTime tTime) {
        this.node = node;
        this.tValue = tValue;
        this.tTime = tTime;
    }

    public ProAmount() {
    }

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }

    public Integer gettValue() {
        return tValue;
    }

    public void settValue(Integer tValue) {
        this.tValue = tValue;
    }

    public LocalDateTime gettTime() {
        return tTime;
    }

    public void settTime(LocalDateTime tTime) {
        this.tTime = tTime;
    }

    @Override
    public String toString() {
        return "ProAmount{" +
                "node=" + node +
                ", tValue=" + tValue +
                ", tTime=" + tTime +
                '}';
    }
}
