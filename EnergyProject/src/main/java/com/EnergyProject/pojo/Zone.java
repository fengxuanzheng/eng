package com.EnergyProject.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.ObjectInputFilter;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Zone implements Serializable {

    private Integer eid;
    private Integer tValue;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "GMT+8")
    private LocalDateTime tTime;


    public Zone(Integer eid, Integer tValue, LocalDateTime tTime) {
        this.eid = eid;
        this.tValue = tValue;
        this.tTime = tTime;
    }

    public Zone() {
    }

    @Override
    public String toString() {
        return "Zone{" +
                "eid=" + eid +
                ", tValue=" + tValue +
                ", tTime=" + tTime +
                '}';
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
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


}
