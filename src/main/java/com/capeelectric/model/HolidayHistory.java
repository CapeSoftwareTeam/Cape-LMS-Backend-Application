package com.capeelectric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="holidays_history_table")
public class HolidayHistory {

	@Id
	@Column(name="HISTORY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer historyId;
	
	@Column(name="DATE")
	private String date;
	 
	@Column(name="DAY")
	private String day;
	
	@Column(name="YEAR")
	private Integer year;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="WORK_LOCATION")
	private String workLocation;

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}
}
