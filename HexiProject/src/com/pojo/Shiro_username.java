package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Shiro_username implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiro_username.userId
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiro_username.username
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiro_username.password
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiro_username.salt
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    private String salt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiro_username.email
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */

    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiro_username.mobile
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    private Long mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiro_username.status
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiro_username.station
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    private String station;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiro_username.createTime
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiro_username.updateTime
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date updatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiro_username.user_photo
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    private byte[] userPhoto;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiro_username.userId
     *
     * @return the value of shiro_username.userId
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiro_username.userId
     *
     * @param userid the value for shiro_username.userId
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiro_username.username
     *
     * @return the value of shiro_username.username
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiro_username.username
     *
     * @param username the value for shiro_username.username
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiro_username.password
     *
     * @return the value of shiro_username.password
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiro_username.password
     *
     * @param password the value for shiro_username.password
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiro_username.salt
     *
     * @return the value of shiro_username.salt
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public String getSalt() {
        return salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiro_username.salt
     *
     * @param salt the value for shiro_username.salt
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiro_username.email
     *
     * @return the value of shiro_username.email
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiro_username.email
     *
     * @param email the value for shiro_username.email
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiro_username.mobile
     *
     * @return the value of shiro_username.mobile
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public Long getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiro_username.mobile
     *
     * @param mobile the value for shiro_username.mobile
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiro_username.status
     *
     * @return the value of shiro_username.status
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiro_username.status
     *
     * @param status the value for shiro_username.status
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiro_username.station
     *
     * @return the value of shiro_username.station
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public String getStation() {
        return station;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiro_username.station
     *
     * @param station the value for shiro_username.station
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public void setStation(String station) {
        this.station = station == null ? null : station.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiro_username.createTime
     *
     * @return the value of shiro_username.createTime
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiro_username.createTime
     *
     * @param createtime the value for shiro_username.createTime
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiro_username.updateTime
     *
     * @return the value of shiro_username.updateTime
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiro_username.updateTime
     *
     * @param updatetime the value for shiro_username.updateTime
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiro_username.user_photo
     *
     * @return the value of shiro_username.user_photo
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public byte[] getUserPhoto() {
        return userPhoto;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiro_username.user_photo
     *
     * @param userPhoto the value for shiro_username.user_photo
     *
     * @mbggenerated Tue Apr 27 10:06:13 CST 2021
     */
    public void setUserPhoto(byte[] userPhoto) {
        this.userPhoto = userPhoto;
    }

    @Override
    public String toString() {
        return "Shiro_username{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                ", status='" + status + '\'' +
                ", station='" + station + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", userPhoto=" + Arrays.toString(userPhoto) +
                '}';
    }
}