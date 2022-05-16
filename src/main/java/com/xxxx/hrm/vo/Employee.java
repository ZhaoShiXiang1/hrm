package com.xxxx.hrm.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Employee {
    private Integer id;

    private String name;

    private Integer sex;

    private String phone;

    private String email;

    private String education;

    private String idcard;

    private String address;

    private Integer positionId;

    private Integer deptId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createdTime;

    //添加字段，数据库不存在
    private String positionName;

    //添加字段，数据库不存在
    private String deptName;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", education='" + education + '\'' +
                ", idcard='" + idcard + '\'' +
                ", address='" + address + '\'' +
                ", positionId=" + positionId +
                ", deptId=" + deptId +
                ", createdTime=" + createdTime +
                ", positionName='" + positionName + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}