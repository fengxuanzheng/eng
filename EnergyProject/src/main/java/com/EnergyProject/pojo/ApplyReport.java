package com.EnergyProject.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 魔法少女
 * @since 2021-07-14
 */
@TableName("ApplyReport")
public class ApplyReport extends Model<ApplyReport> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("status")
    private String status;

    @TableField("username")
    private String username;

    @TableField("usernameId")
    private Integer usernameId;

    @TableField("usernameDistrict")
    private String usernameDistrict;

    @TableField("equipmentName")
    private String equipmentName;

    @TableField("workContainer")
    private String workContainer;

    @TableField("workWaring")
    private String workWaring;

    @TableField("safety")
    private String safety;

    @TableField("energyType")
    private String energyType;

    @TableField("energySource")
    private String energySource;

    @TableField("energyManagement")
    private String energyManagement;

    @TableField("workLocation")
    private String workLocation;

    @TableField("workPerson")
    private String workPerson;

    @TableField("safetyPerson")
    private String safetyPerson;

    @TableField("phone")
    private Long phone;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "GMT+8")
    @TableField("workTimeStart")
    private LocalDateTime workTimeStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "GMT+8")
    @TableField("workTimeEnd")
    private LocalDateTime workTimeEnd;

    @TableField("applyTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "GMT+8")
    private LocalDateTime applyTime;

    @TableField(value = "checkTime",fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "GMT+8")
    private LocalDateTime checkTime;

    @TableField("rejectText")
    private String rejectText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getWorkContainer() {
        return workContainer;
    }

    public void setWorkContainer(String workContainer) {
        this.workContainer = workContainer;
    }

    public String getWorkWaring() {
        return workWaring;
    }

    public void setWorkWaring(String workWaring) {
        this.workWaring = workWaring;
    }

    public String getSafety() {
        return safety;
    }

    public void setSafety(String safety) {
        this.safety = safety;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public String getEnergySource() {
        return energySource;
    }

    public void setEnergySource(String energySource) {
        this.energySource = energySource;
    }

    public String getEnergyManagement() {
        return energyManagement;
    }

    public void setEnergyManagement(String energyManagement) {
        this.energyManagement = energyManagement;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getWorkPerson() {
        return workPerson;
    }

    public void setWorkPerson(String workPerson) {
        this.workPerson = workPerson;
    }

    public String getSafetyPerson() {
        return safetyPerson;
    }

    public void setSafetyPerson(String safetyPerson) {
        this.safetyPerson = safetyPerson;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public LocalDateTime getWorkTimeStart() {
        return workTimeStart;
    }

    public void setWorkTimeStart(LocalDateTime workTimeStart) {
        this.workTimeStart = workTimeStart;
    }

    public LocalDateTime getWorkTimeEnd() {
        return workTimeEnd;
    }

    public void setWorkTimeEnd(LocalDateTime workTimeEnd) {
        this.workTimeEnd = workTimeEnd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUsernameId() {
        return usernameId;
    }

    public void setUsernameId(Integer usernameId) {
        this.usernameId = usernameId;
    }

    public String getUsernameDistrict() {
        return usernameDistrict;
    }

    public void setUsernameDistrict(String usernameDistrict) {
        this.usernameDistrict = usernameDistrict;
    }

    public LocalDateTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalDateTime applyTime) {
        this.applyTime = applyTime;
    }

    public LocalDateTime getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDateTime checkTime) {
        this.checkTime = checkTime;
    }

    public String getRejectText() {
        return rejectText;
    }

    public void setRejectText(String rejectText) {
        this.rejectText = rejectText;
    }

    @Override
    public Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ApplyReport{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                ", usernameId=" + usernameId +
                ", usernameDistrict='" + usernameDistrict + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", workContainer='" + workContainer + '\'' +
                ", workWaring='" + workWaring + '\'' +
                ", safety='" + safety + '\'' +
                ", energyType='" + energyType + '\'' +
                ", energySource='" + energySource + '\'' +
                ", energyManagement='" + energyManagement + '\'' +
                ", workLocation='" + workLocation + '\'' +
                ", workPerson='" + workPerson + '\'' +
                ", safetyPerson='" + safetyPerson + '\'' +
                ", phone=" + phone +
                ", workTimeStart=" + workTimeStart +
                ", workTimeEnd=" + workTimeEnd +
                ", applyTime=" + applyTime +
                ", checkTime=" + checkTime +
                ", rejectText='" + rejectText + '\'' +
                '}';
    }
}
