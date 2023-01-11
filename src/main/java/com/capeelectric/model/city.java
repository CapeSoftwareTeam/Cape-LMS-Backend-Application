package com.capeelectric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city_table")
public class city {

	
	@Id
	@Column(name=" CITY_NAME")
	private Integer cityname;
	@Column(name=" CITY_ID")
	private String cityid;
	
	public Integer getCityname() {
		return cityname;
	}
	public void setCityname(Integer cityname) {
		this.cityname = cityname;
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	
	
}
