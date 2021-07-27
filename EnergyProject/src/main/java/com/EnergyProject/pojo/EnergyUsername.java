package com.EnergyProject.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Arrays;

/**
 * <p>
 * 
 * </p>
 *
 * @author 魔法少女
 * @since 2021-07-27
 */
@TableName("EnergyUsername")
public class EnergyUsername extends Model<EnergyUsername> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userId", type = IdType.AUTO)
    private Integer userId;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("salt")
    private String salt;

    @TableField("mobile")
    private Long mobile;

    @TableField("workId")
    private String workId;

    @TableField("area")
    private String area;

    @TableField("status")
    private String status;

    @TableField("station")
    private String station;

    @TableField("userPhoto")
    private byte[] userPhoto;

    @TableField("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "GMT+8")
    private LocalDateTime createTime;

    @TableField("updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "GMT+8")
    private LocalDateTime updateTime;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public byte[] getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(byte[] userPhoto) {
        this.userPhoto = userPhoto;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "EnergyUsername{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", mobile=" + mobile +
                ", workId='" + workId + '\'' +
                ", area='" + area + '\'' +
                ", status='" + status + '\'' +
                ", station='" + station + '\'' +
                ", userPhoto=" + Arrays.toString(userPhoto) +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
