package com.capeelectric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city_table")
public class city {

	
	@Id
	@Column(name="CITY_ID")
	private Integer cityid;
	
	@Column(name="STATE_ID")
	private Integer stateid;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CODE")
	private Integer code;
}
