package com.praveen.spring.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class City implements Serializable {
	
	private static final long serialVersionUID = 529218045596250506L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cityId", updatable = false, nullable = false)
	private Integer cityId;
	@Column(name = "CITY_NAME", unique = false, nullable = false, length = 100)
	private String cityName;
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="COUNTRY_ID", nullable=false)
	private Country country;
}
