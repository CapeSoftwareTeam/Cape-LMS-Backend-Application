package com.capeelectric.model;

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
   private Integer registerid;
   
   @Column(name="EMP_ID")
   private String empid;
    
   @Column(name="NAME")
   private String name;
   
   @Column(name="DOB")
   private Integer dob;
   
   @Column(name="GENDER")
   private String gender;
   
   @Column(name="MARITAL_STATUS")
   private String maritalstatus;
   
   @Column(name="MOBILE_NUMBER")
   private Integer mobilenumber;
   
   @Column(name="ALTERNATE_NUMBER")
   private Integer alternatenumber;
   
   @Column(name="EMAIL_ID")
   private String emailid;
   
   @Column(name="PASSWORD ")
   private String password;
   
   @Column(name="DATE_OF_JOINING")
   private Integer dateofjoining;
   
   @Column(name="DEPARTMENT ")
   private String department;
   
   @Column(name="DESIGNATION")
   private String designation;
   
   @Column(name="TOTAL_EXPERIENCE")
   private Integer totalexperience;
   
   @Column(name="OFFICE_LOCATION")
   private String officelocation;
   
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

public Integer getDob() {
	return dob;
}

public void setDob(Integer dob) {
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

public Integer getMobilenumber() {
	return mobilenumber;
}

public void setMobilenumber(Integer mobilenumber) {
	this.mobilenumber = mobilenumber;
}

public Integer getAlternatenumber() {
	return alternatenumber;
}

public void setAlternatenumber(Integer alternatenumber) {
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

public Integer getDateofjoining() {
	return dateofjoining;
}

public void setDateofjoining(Integer dateofjoining) {
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

   

}
