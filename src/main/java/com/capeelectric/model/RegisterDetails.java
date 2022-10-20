package com.capeelectric.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="register_table")

public class RegisterDetails {
   @Id
   @Column(name=" REGISTER_ID")
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer registerid;
   
   @Column(name="EMP_ID")
   private String empid;
    
   @Column(name="NAME")
   private String name;
   
   @Column(name="DOB")
   private Date dob;
   
   @Column(name="GENDER")
   private String gender;
   
   @Column(name="MARITAL_STATUS")
   private String maritalstatus;
   
   @Column(name="MOBILE_NUMBER")
   private String mobilenumber;
   
   @Column(name="ALTERNATE_NUMBER")
   private String alternatenumber;
   
   @Column(name="EMAIL_ID")
   private String emailid;
   
   @Column(name="PASSWORD ")
   private String password;
   
   @Column(name="DATE_OF_JOINING")
   private Date dateofjoining;
   
   @Column(name="DEPARTMENT")
   private String department;
   
   @Column(name="DESIGNATION")
   private String designation;
   
   @Column(name="TOTAL_EXPERIENCE")
   private Integer totalexperience;
   
   @Column(name="OFFICE_LOCATION")
   private String officelocation;
   
   @Column(name="CREATED_DATE")
   private LocalDateTime createddate;
   
   @Column(name="CREATED_BY")
   private String createdby;
   
   @Column(name="UPDATED_DATE")
   private LocalDateTime updateddate;
   
   @Column(name="UPDATED_BY")
   private String updatedby;
   
   @Column(name="STATUS")
   private String status;

public Integer getRegisterid() {
	return registerid;
}

public void setRegisterid(Integer registerid) {
	this.registerid = registerid;
}

public String getEmpid() {
	return empid;
}

public void setEmpid(String empid) {
	this.empid = empid;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Date getDob() {
	return dob;
}

public void setDob(Date dob) {
	this.dob = dob;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getMaritalstatus() {
	return maritalstatus;
}

public void setMaritalstatus(String maritalstatus) {
	this.maritalstatus = maritalstatus;
}



public String getMobilenumber() {
	return mobilenumber;
}

public void setMobilenumber(String mobilenumber) {
	this.mobilenumber = mobilenumber;
}

public String getAlternatenumber() {
	return alternatenumber;
}

public void setAlternatenumber(String alternatenumber) {
	this.alternatenumber = alternatenumber;
}

public String getEmailid() {
	return emailid;
}

public void setEmailid(String emailid) {
	this.emailid = emailid;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

 

public Date getDateofjoining() {
	return dateofjoining;
}

public void setDateofjoining(Date dateofjoining) {
	this.dateofjoining = dateofjoining;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public String getDesignation() {
	return designation;
}

public void setDesignation(String designation) {
	this.designation = designation;
}

public Integer getTotalexperience() {
	return totalexperience;
}

public void setTotalexperience(Integer totalexperience) {
	this.totalexperience = totalexperience;
}

public String getOfficelocation() {
	return officelocation;
}

public void setOfficelocation(String officelocation) {
	this.officelocation = officelocation;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}



public String getCreatedby() {
	return createdby;
}

public void setCreatedby(String createdby) {
	this.createdby = createdby;
}



public LocalDateTime getCreateddate(LocalDateTime createdDate) {
	return createddate;
}

public void setCreateddate(LocalDateTime localDateTime) {
	this.createddate = localDateTime;
}



public LocalDateTime getUpdateddate() {
	return updateddate;
}

public void setUpdateddate(LocalDateTime updateddate) {
	this.updateddate = updateddate;
}

public LocalDateTime getCreateddate() {
	return createddate;
}

public String getUpdatedby() {
	return updatedby;
}

public void setUpdatedby(String updatedby) {
	this.updatedby = updatedby;
}



   

}
