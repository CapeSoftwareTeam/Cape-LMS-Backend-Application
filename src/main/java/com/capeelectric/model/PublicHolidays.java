package com.capeelectric.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="public_holidays")
public class PublicHolidays {

	@Id
	@Column(name="PL_ID")
	private Integer pl_Id;
	
	@Column(name="DATE")
	private Date date;
	
	@Column(name="DAY")
	private String day;
	
	@Column(name="WORK_LOCATION")
	private String work_Location;

	public Integer getPl_Id() {
		return pl_Id;
	}

	public void setPl_Id(Integer pl_Id) {
		this.pl_Id = pl_Id;
	}

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

	public String getWork_Location() {
		return work_Location;
	}

	public void setWork_Location(String work_Location) {
		this.work_Location = work_Location;
	}

	
	
	
	
	
}
