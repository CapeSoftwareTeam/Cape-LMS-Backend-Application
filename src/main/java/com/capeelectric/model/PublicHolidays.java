package com.capeelectric.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="public_holidays")
public class PublicHolidays {

	
	@Id
	@Column(name="PL_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer publicLeaveId;
	
	@Column(name="DATE")
	private Date date;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="DAY")
	private String day;
	
	@Column(name="YEAR")
	private Integer year;
	
	@Column(name="WORK_LOCATION")
	private String workLocation;



	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getPublicLeaveId() {
		return publicLeaveId;
	}

	public void setPublicLeaveId(Integer publicLeaveId) {
		this.publicLeaveId = publicLeaveId;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
