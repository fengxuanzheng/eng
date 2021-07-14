package com.EnergyProject.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableField("workTime")
    private LocalDateTime workTime;


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

    public LocalDateTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(LocalDateTime workTime) {
        this.workTime = workTime;
    }

    @Override
    public Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ApplyReport{" +
        "id=" + id +
        ", status=" + status +
        ", equipmentName=" + equipmentName +
        ", workContainer=" + workContainer +
        ", workWaring=" + workWaring +
        ", safety=" + safety +
        ", energyType=" + energyType +
        ", energySource=" + energySource +
        ", energyManagement=" + energyManagement +
        ", workLocation=" + workLocation +
        ", workPerson=" + workPerson +
        ", safetyPerson=" + safetyPerson +
        ", phone=" + phone +
        ", workTime=" + workTime +
        "}";
    }
}
