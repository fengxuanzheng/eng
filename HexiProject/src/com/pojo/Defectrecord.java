package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

public class Defectrecord implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.id
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.VIN
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    //@NotBlank
    //@Length(min = 17,max = 17)
    @Pattern(regexp = "^[A-Za-z0-9]{17}$",message = "vin码必须是普通字符或数字不能含有中文或特殊字符,长度为17位")
    private String vin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.car_type
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    @NotBlank
    private String carType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.defect_status
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    @NotBlank
    private String defectStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.station
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    @NotBlank
    private String station;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.timestamp
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date timestamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.type
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */

    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.defect_part
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */

    private String defectPart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.specific_part
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */

    private String specificPart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.defect_mode
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */

    private String defectMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.remark
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */

    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.defect_name
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */

    private String defectName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.status
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */

    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.username
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    @NotBlank
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column defectrecord.comments
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    private String comments;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.id
     *
     * @return the value of defectrecord.id
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.id
     *
     * @param id the value for defectrecord.id
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.VIN
     *
     * @return the value of defectrecord.VIN
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public String getVin() {
        return vin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.VIN
     *
     * @param vin the value for defectrecord.VIN
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.car_type
     *
     * @return the value of defectrecord.car_type
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public String getCarType() {
        return carType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.car_type
     *
     * @param carType the value for defectrecord.car_type
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.defect_status
     *
     * @return the value of defectrecord.defect_status
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public String getDefectStatus() {
        return defectStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.defect_status
     *
     * @param defectStatus the value for defectrecord.defect_status
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setDefectStatus(String defectStatus) {
        this.defectStatus = defectStatus == null ? null : defectStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.station
     *
     * @return the value of defectrecord.station
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public String getStation() {
        return station;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.station
     *
     * @param station the value for defectrecord.station
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setStation(String station) {
        this.station = station == null ? null : station.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.timestamp
     *
     * @return the value of defectrecord.timestamp
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.timestamp
     *
     * @param timestamp the value for defectrecord.timestamp
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.type
     *
     * @return the value of defectrecord.type
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.type
     *
     * @param type the value for defectrecord.type
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.defect_part
     *
     * @return the value of defectrecord.defect_part
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public String getDefectPart() {
        return defectPart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.defect_part
     *
     * @param defectPart the value for defectrecord.defect_part
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setDefectPart(String defectPart) {
        this.defectPart = defectPart == null ? null : defectPart.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.specific_part
     *
     * @return the value of defectrecord.specific_part
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public String getSpecificPart() {
        return specificPart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.specific_part
     *
     * @param specificPart the value for defectrecord.specific_part
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setSpecificPart(String specificPart) {
        this.specificPart = specificPart == null ? null : specificPart.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.defect_mode
     *
     * @return the value of defectrecord.defect_mode
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public String getDefectMode() {
        return defectMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.defect_mode
     *
     * @param defectMode the value for defectrecord.defect_mode
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setDefectMode(String defectMode) {
        this.defectMode = defectMode == null ? null : defectMode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.remark
     *
     * @return the value of defectrecord.remark
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.remark
     *
     * @param remark the value for defectrecord.remark
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.defect_name
     *
     * @return the value of defectrecord.defect_name
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public String getDefectName() {
        return defectName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.defect_name
     *
     * @param defectName the value for defectrecord.defect_name
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setDefectName(String defectName) {
        this.defectName = defectName == null ? null : defectName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.status
     *
     * @return the value of defectrecord.status
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.status
     *
     * @param status the value for defectrecord.status
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.username
     *
     * @return the value of defectrecord.username
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.username
     *
     * @param username the value for defectrecord.username
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column defectrecord.comments
     *
     * @return the value of defectrecord.comments
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public String getComments() {
        return comments;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column defectrecord.comments
     *
     * @param comments the value for defectrecord.comments
     *
     * @mbggenerated Fri May 07 14:32:04 CST 2021
     */
    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }
}